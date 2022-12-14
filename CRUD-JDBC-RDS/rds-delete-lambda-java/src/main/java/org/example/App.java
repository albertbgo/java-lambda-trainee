package org.example;

import org.example.entity.Band;
import org.example.entity.Secret;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        Handler handler = new Handler();

        Band band = new Band();
        band.setBandId(null);
        band.setBandName("AC/DC");
        band.setBandGenre("HARD ROCK");
        band.setBandYearInit("1967-01-01");


        System.out.println(handler.save("TESTUID", band, new Secret("root", "", "XX", "localhost", "3306", "XXXX")));

        System.out.println( "Hello World!" );
    }
}
