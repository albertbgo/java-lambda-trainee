package org.example;

import org.example.entity.Band;
import org.example.entity.Secret;
import org.example.entity.request.BandRequest;
import org.example.utils.BadFormattedInputException;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws BadFormattedInputException {

        Handler handler = new Handler();

        List<Band> bandList = new ArrayList<>();

       // System.out.println(handler.save("TESTUID", band, new Secret("root", "", "XX", "localhost", "3306", "XXXX")));

        BandRequest request = new BandRequest();
        request.setId(0);
        request.setUid("");
        request.setBandName("");
        request.setBandGenre("");
        request.setBandYearInit("167-01-01");

     //   handler.validateRequestInput(request, "SIMPLKEIOD");
       // bandList = handler.read("TESTUID",request,  new Secret("root", "", "XX", "localhost", "3306", "XXXX"));

       // bandList.forEach(System.out::println);
       // System.out.println( "Hello World!" );
    }
}
