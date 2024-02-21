package com.superealboom.demo.pgsql;

import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: TODO
 * @author: tianci
 * @date: 2022/8/10 10:00
 */
public class pgsqlDemo {

    Connection connection = null;
    Statement stmt = null;

    @Before
    public void before() throws Exception {
        Class.forName("com.huawei.gauss200.jdbc.Driver");
        String url = "jdbc:postgresql://127.0.0.1:5432/test";
        String userName = "postgres";
        String password = "123456";
        connection = DriverManager.getConnection(url, userName, password);
        stmt = connection.createStatement();
    }

    @Test
    public void select(){
        try {

            String[] tableNames = {
                    "test.\"public\".\"batchTestTimeDemo\""
            };
            List<String> tableNameList = new ArrayList<>(Arrays.asList(tableNames));
            for (String tableName : tableNameList) {
                System.out.println("table:" + tableName);
                ResultSet resultSet = stmt.executeQuery("select * from " + tableName);
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int colCount = resultSetMetaData.getColumnCount();

                while (resultSet.next()) {
                    for (int i = 1; i <= colCount; i++) {
                        System.out.print(resultSet.getString(i) + "\t");
                    }
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
