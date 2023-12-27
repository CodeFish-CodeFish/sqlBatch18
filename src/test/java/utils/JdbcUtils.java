package utils;

import org.junit.AfterClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JdbcUtils {

    static Connection connection;
    static Statement statement;
    static ResultSet resultSet;

    private static Statement establishConnection(){
        try{
            connection = DriverManager.getConnection(
                    getProp("connection_string"),
                    getProp("username"),
                    getProp("password"));
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        }
        catch (SQLException sqlException){
            throw new RuntimeException("Could not connect to DataBase.");
        }
       return statement;
    }


    public static ResultSet queryDB(String query){
        statement = establishConnection();
        try
        {
            resultSet = statement.executeQuery(query);
            return resultSet;
        }catch (SQLException sqlException){
            throw new RuntimeException("Failed running query");
        }
    }






    private static String getProp(String key){
        Properties properties = new Properties();
        try{
            properties.load(new FileInputStream(new File("src/test/resources/database.properties")));
        }catch (IOException exception){
            throw new RuntimeException("Could not find a properties file.");
        }

        return properties.getProperty(key);
    }

    @AfterClass
    public static void closeResources(){
        try {
            if (resultSet!=null){
                resultSet.close();
            }
            if (statement!=null){
                statement.close();
            }
            if (connection != null){
                connection.close();
            }
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }

    }




}
