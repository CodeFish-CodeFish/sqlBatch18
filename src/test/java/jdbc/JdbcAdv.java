package jdbc;

import org.junit.Assert;
import org.junit.Test;
import utils.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcAdv extends JdbcUtils {

    @Test
    public void test1() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@codefishsqlb18.cfxmtijfjb4b.us-east-2.rds.amazonaws.com:1521/ORCL",
                "student", "codefish385");
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from employees");

        ResultSetMetaData metaData = resultSet.getMetaData();


        List<Map<String, Object>> listOfMaps = new ArrayList<>();

        while (resultSet.next()) {
            Map<String, Object> rowMap = new HashMap<>(); // INSIDE OF WHILE LOOP
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                rowMap.put(metaData.getColumnName(i), resultSet.getObject(i));
            }
            listOfMaps.add(rowMap);
        }

        // VALIDATING TJ HAS 2000 SALARY
        for (Map<String, Object> eachMap : listOfMaps) {
            String first_name = String.valueOf(eachMap.get("FIRST_NAME"));
            if (first_name.equals("TJ")) {
                int salary = Integer.parseInt(eachMap.get("SALARY").toString());
                Assert.assertEquals(2000, salary);
            }
        }


    }


    //validate TJ Olson has 2000 salary
    @Test
    public void test2() throws SQLException {
        ResultSet resultSet = JdbcUtils.queryDB("select * from employees");
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        List<Map<String, Object>> listOfMaps = new ArrayList<>();

        while (resultSet.next()) {
            Map<String, Object> rowMap = new HashMap<>(); // INSIDE OF WHILE LOOP
            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                rowMap.put(resultSetMetaData.getColumnName(i), resultSet.getObject(i));
            }
            listOfMaps.add(rowMap);
        }

        // VALIDATING TJ HAS 2000 SALARY
        for (Map<String, Object> eachMap : listOfMaps) {
            String first_name = String.valueOf(eachMap.get("FIRST_NAME"));
            if (first_name.equals("TJ")) {
                int salary = Integer.parseInt(eachMap.get("SALARY").toString());
                Assert.assertEquals(2000, salary);
            }
        }

    }


}
