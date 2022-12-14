package org.example;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.db.SingleDBConnection;
import org.example.entity.Band;
import org.example.entity.Secret;
import org.example.entity.request.BandRequest;
import org.example.entity.response.BandResponse;
import org.example.utils.BadFormattedInputException;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.*;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import java.sql.SQLException;
import java.util.Base64;
import java.util.UUID;

public class Handler implements RequestHandler<BandRequest, BandResponse>{

    private static ObjectMapper objectMapper;
    static LambdaLogger logger = null;
    @Override
    public BandResponse handleRequest(BandRequest request, Context context) {
        logger = context.getLogger();
        objectMapper = new ObjectMapper();

        String uid = null == request.getUid() ? UUID.randomUUID().toString() : request.getUid();

        logger.log(uid+" :: Star creation process for band :: ");

        try {
            validateRequestInput(request, uid);
        } catch (BadFormattedInputException e) {
            throw new RuntimeException(e);
        }

        int id;

        try {
            Secret secret = getSecret();

            if(null == secret){
                throw new Exception("Secrets cannot be loaded");
            }

            id = this.save(uid, new Band(null, request.getBandName(), request.getBandGenre(), request.getBandYearInit()),
                    secret);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new BandResponse(uid, id, "Success Creation");

    }

    private void validateRequestInput(BandRequest request, String uid) throws BadFormattedInputException {
        logger.log(uid+" :: Validating input request :: ");
        if(null == request){
            throw new BadFormattedInputException("¡BAD INPUT!");
        }else {
            if (null == request.getBandName() || null == request.getBandGenre() || null == request.getBandYearInit()) {
                throw new BadFormattedInputException("All the fields are necessary for this process, not nulls allowed");
            }else{
                if (request.getBandName().isEmpty() || request.getBandGenre().isEmpty() || request.getBandYearInit().isEmpty()) {
                    throw new BadFormattedInputException("All the fields are necessary for this process, not empties allowed");
                } else {
                    if (!request.getBandYearInit().matches("\\d{4}-\\d{2}-\\d{2}")) {
                        throw new BadFormattedInputException("Incorrect Date Format (YYYY-MM-DD)");
                    }
                }
            }

        }

    }

    public static Secret getSecret() throws JsonProcessingException {

        String secretName = "priv-bd-test";
        Region region = Region.of("us-west-2");

        // Create a Secrets Manager client
        SecretsManagerClient client = SecretsManagerClient.builder()
                .region(region)
                .build();

        // In this sample we only handle the specific exceptions for the 'GetSecretValue' API.
        // See https://docs.aws.amazon.com/secretsmanager/latest/apireference/API_GetSecretValue.html
        // We rethrow the exception by default.

        String secret, decodedBinarySecret;
        GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();
        GetSecretValueResponse getSecretValueResponse = null;

        try {
            getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
        } catch (DecryptionFailureException e) {
            // Secrets Manager can't decrypt the protected secret text using the provided KMS key.
            // Deal with the exception here, and/or rethrow at your discretion.
            throw e;
        } catch (InternalServiceErrorException e) {
            // An error occurred on the server side.
            // Deal with the exception here, and/or rethrow at your discretion.
            throw e;
        } catch (InvalidParameterException e) {
            // You provided an invalid value for a parameter.
            // Deal with the exception here, and/or rethrow at your discretion.
            throw e;
        } catch (InvalidRequestException e) {
            // You provided a parameter value that is not valid for the current state of the resource.
            // Deal with the exception here, and/or rethrow at your discretion.
            throw e;
        } catch (ResourceNotFoundException e) {
            // We can't find the resource that you asked for.
            // Deal with the exception here, and/or rethrow at your discretion.
            throw e;
        }

        // Decrypts secret using the associated KMS key.
        // nding on whether the secret is a string or binary, one of these fields will be populated.Depe
        if (getSecretValueResponse.secretString() != null) {
            secret = getSecretValueResponse.secretString();
            try {
                Secret dbSecret = objectMapper.readValue(secret, Secret.class);
                logger.log("Info About Secret: " + dbSecret.toString());
                return dbSecret;
            } catch (JsonProcessingException e) {
                logger.log("ERROR :: Reading json secret value ::" + e.getMessage());
                throw e;
            }

        }
        else {
            decodedBinarySecret = new String(Base64.getDecoder().decode(getSecretValueResponse.secretBinary().asByteBuffer()).array());
            logger.log("EVENT decodedBinarySecret: ****** " + decodedBinarySecret);


            // Your code goes here.
            return null;
        }


    }


    public int save(String uid, Band band, Secret dbSecret){
        int id = 0;
        String sql = "INSERT INTO bands VALUES("+ band.getBandId() +",'"+band.getBandName()+"',"
                + "'"+band.getBandGenre()+"','"+band.getBandYearInit()+"');";
        try {
            //una variable por cada dato parametro, String menos pic esa es int
            SingleDBConnection con = SingleDBConnection.getInstance("bands_inventory", dbSecret.getUsername(), dbSecret.getPassword(), dbSecret.getHost(), dbSecret.getPort());

            if(con.open()){
                id = (int) con.updateData(sql);
            }else{
                logger.log(uid + " :: ERROR: No se realizo conexión a la base de datos.");
                //System.out.printf(uid + " :: ERROR: No se realizo conexión a la base de datos.");
            }

        } catch (ClassNotFoundException ex) {
            logger.log(uid + " :: ERROR: No hay conexión con el driver." + ex.getMessage());
            //System.out.printf(uid + " :: ERROR: No hay conexión con el driver." + ex.getMessage());
        } catch (SQLException ex) {
            logger.log(uid + " :: ERROR al realizar la sentencia SQL: "+sql+ ex.getMessage());
            //System.out.printf(uid + " :: ERROR al realizar la sentencia SQL: "+sql+ ex.getMessage());
        }
        logger.log(uid + " :: Actor exitosamente guardado.");
        //System.out.printf(uid + " :: Actor exitosamente guardado.");

        return id;
    }

}
