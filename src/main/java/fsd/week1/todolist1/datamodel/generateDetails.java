package fsd.week1.todolist1.datamodel;

import java.security.SecureRandom;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class generateDetails {
    private String name;
    private String gender;
    private int yearOfBirth;
    private int count;
    private int id;

    private String Ethnicity;

    private SecureRandom randomNum = new SecureRandom();
    private int randomNumber;
    private String file = "babyNames.csv";
    private dataBaseConnection dataBaseConnection = new dataBaseConnection();

    private String query;


    private List<String> ethnicitiesHM = new ArrayList<>();

    private List<String> gendersHM = new ArrayList<>();


    private Statement statement = null;
    private ResultSet resultSet = null;

    //keep this just in case. but at the moment it is not used at all
    public generateDetails(int id,int yearOfBirth,String gender, String ethnicity, String name, int count) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.yearOfBirth = yearOfBirth;
        this.count = count;
    }

    //keep this just so that it doesnt use the above super constructor.
    public generateDetails() {
        gendersHM.add(null);
        gendersHM.add("MALE");
        gendersHM.add("FEMALE");
    }

    //generate random details. I could have forced this all into one method using multiple if statements depending on the input
    //but to make it cleaner and clearer ive decided to seperate the methods. 1. for random names based off no criteria, 2. random names based off 1 criteria and 2. random names based off 2 criterias


    public String getRandomName() {
        //generate a random number and convert it into a string for sql statment
        genRandomNumber();
        String sRandomNum = String.valueOf(randomNumber);


        //query through the database to find that specific value
        try{

//            //connect to the database.
            dataBaseConnection.establishConnection();

            //write a query to database
            statement = dataBaseConnection.getConnection().createStatement();
            String query = "SELECT childsfirstname FROM babynames WHERE _id = " + sRandomNum;
            resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                name = resultSet.getString("childsfirstname");
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                if(resultSet != null){
                    resultSet.close();
                }

                if(statement != null){
                    statement.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        return name;
    }

    //query through database and grab a random value based off a specific type either gender or ethnicity.
    public String getRandomNameBasedOf(String input, String type) {

        //if statement to trigger relevant catergory.
        if(input.equals("gender")){
            query = "select * from babynames where gender = '" + type + "' order by random() limit 1;";
        } else if(input.equals("ethnicity")){
            query = "select * from babynames where ethnicity = '" + type + "' order by random() limit 1;";
        }

        try{
            //connect to database
            dataBaseConnection.establishConnection();
            statement = dataBaseConnection.getConnection().createStatement();

            //execute command to database with relevant query from if statements
            resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                name = resultSet.getString("childsfirstname"); //store the random name to return at the end of the statement
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally { //final instructon is to close all open connections
            try{
                if(resultSet != null){
                    resultSet.close();
                }

                if(statement != null){
                    statement.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return name;
    }


    //chose to use psql randomiser capabilities because the list splits ethnicities into groups across the table
    //making it harder to find a minimum and maximum value to get random numbers from since it will ahve to account for the gaps in between
    //its also possible to do the same with the other catergories.

    public String getRandomNameBasedOfGenderAndEthnicity(String gender, String ethnicity) {
        try{
            //query through the database to find the lowest value so that you can create a new range of random values. it would have been easier with the basic random class but I wanted to coninue using the secure random method
            dataBaseConnection.establishConnection();

            //grab the lowest number and store it
            statement = dataBaseConnection.getConnection().createStatement();

            String query = "select * from babynames where gender = '" + gender + "' AND ethnicity = '" + ethnicity + "' order by random() limit 1;";
            resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                name = resultSet.getString("childsfirstname");
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                if(resultSet != null){
                    resultSet.close();
                }

                if(statement != null){
                    statement.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void genRandomNumber(){ //Generates cryptographically strong random numbers to minimise chance of repeated numbers
       int upperBound = 19419;
       randomNumber = randomNum.nextInt(upperBound);
    }

    public List<String> loadEthnicities(){
        try{
            //query through the database to find the lowest value so that you can create a new range of random values. it would have been easier with the basic random class but I wanted to coninue using the secure random method
            dataBaseConnection.establishConnection();

            //grab the lowest number and store it
            statement = dataBaseConnection.getConnection().createStatement();

            String query = "select ethnicity from babynames";
            resultSet = statement.executeQuery(query);

            ethnicitiesHM.add(null);
            while(resultSet.next()){
                if(!ethnicitiesHM.contains(resultSet.getString("ethnicity"))){
                    ethnicitiesHM.add(resultSet.getString("ethnicity"));
                }
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                if(resultSet != null){
                    resultSet.close();
                }

                if(statement != null){
                    statement.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        return ethnicitiesHM;
    }

    public List<String> getGendersHM() {
        return gendersHM;
    }
}
