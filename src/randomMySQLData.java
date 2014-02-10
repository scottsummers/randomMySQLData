/**
 * Created by ssummers on 12/30/13.
 */

import java.sql.*;
import java.util.Random;

public class randomMySQLData{

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        int inumRows = 0;
        /*
           Program Arguments:
           randomMySQLData "jdbc:mysql://<hostname>/<database>" "<DB USERNAME>" "<PASSWORD>"
           randomMySQLData "jdbc:mysql://172.16.75.182/adm201" "admintest" "mypass"
         */
        String DB_URL = args[0];
        String USER = args[1];
        String PASS = args[2];
        try {
            inumRows = Integer.valueOf(args[3]);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not cast the number of rows to an integer.");
            System.exit(2);
        }

        if (inumRows < 0) {
            System.out.println("Requested number of generated rows is less than zero.");
            System.exit(2);
        }

        /*
            If you want to hard code your connection string use the following.
         */
        //String DB_URL = "jdbc:mysql://172.16.75.182/adm201";
        //String USER = "admintest";
        //String PASS = "mypass";

        //System.out.println("Passed in from user: " + args[0] + " " + args[0] + " " + args[2] + " " + args[3]);

                //STEP 2: Register JDBC driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //STEP 3: Open a connection
            System.out.println("Connecting to database...");
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected successfully...");

            stmt = conn.createStatement();

            //String sql = "SELECT * from randomdata";
            //stmt.execute(sql);
            System.out.println("Inserting records...");
            for (int i = 0; i < inumRows ; i++){
                String sql = "INSERT INTO randomdata VALUES(" + randomData() + ")";
                //System.out.println("sql string: " + sql);
                stmt.executeUpdate(sql);
            }


            //System.out.println("sql string: " + sql);
            System.out.println("Inserted records into the table...");

        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end main

    private static String randomData() {

        Random rnd = new Random();
        long oct1 = 0;
        long oct2 = 0;
        long oct3 = 0;
        long oct4 = 0;
        int sentenceLength = 0;

        //Kick it off from here.
        oct1 = rnd.nextInt(254);
        oct2 = rnd.nextInt(254);
        oct3 = rnd.nextInt(254);
        oct4 = rnd.nextInt(254);
        sentenceLength = rnd.nextInt(100);
        //intgerIP = ((16777216 * oct1) + (65536 * oct2)+ (256 * oct3) + oct4);

        String myData = "NULL" + ", '" + oct1 + "." + oct2 + "." + oct3 + "." + oct4 + "', '" + getRandomMessage() + "', '" + generateRandomSentence(sentenceLength) + "'";
        return myData;
    }//End of RandomData

    private static String generateRandomSentence(int numberOfWords){
        String sentence = "";
        Random r = new Random();

        for(int i = 0; i < numberOfWords; i++){
            sentence += generateRandomWord(r.nextInt(10)) + " ";
        }

        return sentence;
    }

    private static String generateRandomWord(int wordLength) {
        Random r = new Random(); // Intialize a Random Number Generator with SysTime as the seed
        StringBuilder sb = new StringBuilder(wordLength);
        for(int i = 0; i < wordLength; i++) { // For each letter in the word
            char tmp = (char)('a' + r.nextInt('z' - 'a')); // Generate a letter between a and z
            sb.append(tmp); // Add it to the String
        }
        return sb.toString();
    }

    private static String getRandomMessage(){
        Random r = new Random();
        String message[] = {"DEBUG", "WARN", "ERROR", "INFO"};

        return message[r.nextInt(4)];

    }


}//end RandomMySQLData
