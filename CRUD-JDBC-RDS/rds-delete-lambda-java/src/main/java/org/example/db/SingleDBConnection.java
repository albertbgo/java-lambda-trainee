/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.example.db;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author DIN
 */
//Desing Pattern Singleton
/*
 Tiene 3 reglas:
 * 1- debe tener una variable privada y estatica del mismo nombre que la clase (tener istancia de clase dentro de la clase)
 * 2.- tener un constructor privado dentro de la misma clase
 * 3.- tener un método queretorne la instancia de la clase
 */
public class SingleDBConnection {
    private static SingleDBConnection instance;

    private static String database, user, pass, port, endpoint;
    private Connection connection;
    private Statement statement;

    private SingleDBConnection() throws ClassNotFoundException{
        SingleDBConnection.database = "";
        SingleDBConnection.user = "";
        SingleDBConnection.pass = "";

        Class.forName("com.mysql.jdbc.Driver");

    }

    public static SingleDBConnection getInstance(String database, String user, String pass, String endpoint, String port) throws ClassNotFoundException{
        if(instance == null){
        instance = new SingleDBConnection();
        }
                SingleDBConnection.database = database;
                SingleDBConnection.user = user;
                SingleDBConnection.pass = pass;
                SingleDBConnection.endpoint = endpoint;
                SingleDBConnection.port = port;

                return instance;
        }

     public boolean open(){
        String url = "jdbc:mysql://"+endpoint+":"+port+"/"+ SingleDBConnection.database;
        try {
            this.connection = DriverManager.getConnection(url, user, pass);
            this.statement = this.connection.createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
         return true;
     }

     public ResultSet readData(String query) throws SQLException{
          return this.statement.executeQuery(query);
          }

     public long updateData(String sql) throws SQLException{
         PreparedStatement localStatement = connection.prepareStatement(sql,
                 Statement.RETURN_GENERATED_KEYS);

         int affectedRows = localStatement.executeUpdate();

         if (affectedRows == 0) {
             throw new SQLException("Creating user failed, no rows affected.");
         }

         try (ResultSet generatedKeys = localStatement.getGeneratedKeys()) {
             if (generatedKeys.next()) {
                 return generatedKeys.getLong(1);
             }
             else {
                 throw new SQLException("Creating user failed, no ID obtained.");
             }
         }


     }

    @Override
    protected void finalize() throws Throwable {
       this.connection.close();
       this.statement.close();
        super.finalize();
    }



}
