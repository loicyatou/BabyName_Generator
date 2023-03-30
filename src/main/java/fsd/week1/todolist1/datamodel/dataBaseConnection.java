package fsd.week1.todolist1.datamodel;

import java.sql.*;

public class dataBaseConnection {

    Connection connection = null;
    String className = "org.postgresql.Driver";

    Statement statement = null;
    ResultSet resultSet = null;

    public void establishConnection(){
        try{
            Class.forName(className);
            connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/babynames2","postgres","loiswaggy8998_");

            if(connection != null){
                System.out.println("Connection has been established");
            } else{
                System.out.println("Connection failed");
            }
        } catch (Exception e){
            System.out.println("Exception thrown");
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
