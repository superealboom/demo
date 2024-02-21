package com.superealboom.demo.gauss;

import oracle.sql.DATE;
import oracle.sql.NUMBER;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: 高斯数据库增删改查
 * @author: tianci
 * @date: 2022/4/27 09:57
 */
public class GaussDemo {

    Connection connection = null;
    Statement stmt = null;

    @Before
    public void before() throws Exception {
        Class.forName("com.huawei.gauss200.jdbc.Driver");
        String url = "jdbc:gaussdb://59.80.34.108:25308/test";
        String userName = "test";
        String password = "test@1234";
        connection = DriverManager.getConnection(url, userName, password);
        stmt = connection.createStatement();
    }

    // "t_ogg1","t_ogg2","t_ogg3","t_ogg4","t_ogg9","t_ogg10",
    // "t_cdc1","t_cdc6","t_cdc7","t_cdc8","t_cdc11","t_cdc12",
    // "aud_ogg1","aud_ogg2","aud_ogg3","aud_ogg4","aud_ogg9","aud_ogg10",
    // "aud_cdc1","aud_cdc6","aud_cdc7","aud_cdc8","aud_cdc11","aud_cdc12"

    @Test
    public void select(){
        try {

            String[] tableNames = {
                    "test_user"
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


    @Test
    public void query() {
        try {
            String[] tableNames = {
                    "aud_ogg1"
            };
            List<String> tableNameList = new ArrayList<>(Arrays.asList(tableNames));
            for (String tableName : tableNameList) {
                System.out.println("table:" + tableName);
                ResultSet resultSet = stmt.executeQuery("select * from " + tableName);
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int colCount = resultSetMetaData.getColumnCount();
                for (int i = 1; i <= colCount; i++) {
                    System.out.print(resultSetMetaData.getColumnName(i) + " ");
                    System.out.print(resultSetMetaData.getColumnTypeName(i) + "(");
                    System.out.println(resultSetMetaData.getPrecision(i) + ")");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void alter() throws SQLException {
        //查询更改表
        String query = "ALTER TABLE test.aud_ogg1 alter dname type varchar(100)";
        //执行查询
        stmt.executeUpdate(query);
    }

    @Test
    public void insert()  throws SQLException {
        String sql = "INSERT INTO test.test_user VALUES ('2', null, '18', '1', NULL, NULL)";
        stmt.executeUpdate(sql);
    }
    @Test
    public void create() throws SQLException {
        String sql2 = "create table test.aud_emp (" +
                "pos numeric(38,0)," +
                "op_type varchar(100)," +
                "op_ts  timestamp(6)," +
                "mq_ts  timestamp(6)," +
                "db_ts  timestamp(6)," +
                "EMPNO numeric(4,0)," +
                "ENAME varchar(10)," +
                "JOB varchar(9)," +
                "MGR numeric(4,0)," +
                "HIREDATE timestamp," +
                "SAL numeric(7,2)," +
                "COMM numeric(7,2)," +
                "DEPTNO numeric(2,0)" +
                ")";

        stmt.executeUpdate(sql2);
        stmt.close();
    }

    @Test
    public void drop() throws SQLException {
        String sql = "DROP TABLE \"test\".\"test_user\"";
        stmt.executeUpdate(sql);
        stmt.close();
    }

    @Test
    public void delete() throws SQLException {
        String[] tableNames = {
                "test_user"
        };
        List<String> tableNameList = new ArrayList<>(Arrays.asList(tableNames));
        for (String tableName : tableNameList) {
            stmt.executeUpdate("delete FROM " + tableName + ";");
            System.out.println("清空 " + tableName + " 数据成功");
        }
        stmt.close();
    }
}
