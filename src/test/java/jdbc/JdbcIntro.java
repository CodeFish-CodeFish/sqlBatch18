package jdbc;

import org.junit.Test;

import java.sql.*;

public class JdbcIntro {
    @Test
    public void connectionTest() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@codefishsqlb18.cfxmtijfjb4b.us-east-2.rds.amazonaws.com:1521/ORCL",
                "student", "codefish385");
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from employees");

//        resultSet.next();
//        System.out.println(resultSet.getString(1));
//        System.out.println(resultSet.getString(2));
//        System.out.println(resultSet.getString(3));
//        System.out.println(resultSet.getString(4));
//        System.out.println(resultSet.getString(5));

        while (resultSet.next()){//checking every row until result.next() -> false
            System.out.println(resultSet.getString(1) + " "
                    + resultSet.getString(2) + " " +
                    resultSet.getString(3)); //row
        }
    }





}
