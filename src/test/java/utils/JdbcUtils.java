package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JdbcUtils {

    static Connection connection;
    static Statement statement;
    static ResultSet resultSet;

    public static Statement establishConnection(){
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






    private static String getProp(String key){
        Properties properties = new Properties();
        try{
            properties.load(new FileInputStream(new File("src/test/resources/database.properties")));
        }catch (IOException exception){
            throw new RuntimeException("Could not find a properties file.");
        }

        return properties.getProperty(key);
    }


}
