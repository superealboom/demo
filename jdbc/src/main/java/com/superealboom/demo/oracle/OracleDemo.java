package com.superealboom.demo.oracle;

import com.huawei.gauss200.jdbc.copy.CopyManager;
import com.huawei.gauss200.jdbc.core.BaseConnection;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import javax.sql.rowset.serial.SerialBlob;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: oracle例子
 * @author: tianci
 * @date: 2022/5/5 10:22
 */
public class OracleDemo {

    private Connection connection = null;
    private Statement statement = null;


    @Before
    public void before() throws Exception {
        String url = "jdbc:oracle:thin:@59.80.34.108:11521/orcl";
        String userName = "scott";
        String password = "tiger";
        Class.forName("oracle.jdbc.driver.OracleDriver");
        connection = DriverManager.getConnection(url, userName, password);
        statement = connection.createStatement();
    }

    @Test
    public void insert() throws SQLException {

        File file = new File("/Users/tianci/IdeaProjects/d2d/etc/test_user2.dat");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), "utf-8"))) {
            String line;//临时存放每行数据
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                String sql = "INSERT INTO SCOTT.TEST_USER VALUES ('138', 'line''', '18', '1', NULL, NULL)";
                statement.executeUpdate(sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @description: 将文件插入到数据库
     * 这里有clob和blob类型
     * @param: []
     * @return: void
     * @author: tianci
     * @date: 2022/8/10 16:58
     */
    @Test
    public void insertFile() throws Exception{
        StringBuilder stringBuilder = new StringBuilder();
        String str = "12345678123456781234567812345678";//32B
        for (int i=0;i<32*100;i++) {
            stringBuilder.append(str);
        }
        File file = new File("/Users/tianci/Documents/demo2.jpg");
        InputStream in  = new FileInputStream(file);

        byte [] bt = new byte[(int)file.length()];
        int i = in.read(bt);  //将图片存入字节数组bt中
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        int result = -1;
        PreparedStatement pstmt = connection.prepareStatement("insert into BLOB_CLOB_DEMO values (?,?,?)");
        pstmt.setInt(1, 4);
        pstmt.setObject(2, bt);
        pstmt.setString(3, "123\n456");
        result = pstmt.executeUpdate();
        if(result>=0) {
            System.out.println("插入成功");
        }
        in.close();
    }

    /**
     * @description: oracle批量插入
     * @param: []
     * @return: void
     * @author: tianci
     * @date: 2022/8/10 16:59
     */
    @Test
    public void batchInsert() throws Exception {
        List<String> dataList = new ArrayList<>();
        String sql = "insert into SCOTT.TEST_USER VALUES (?, ?, ?, ?, ?, ?)";
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (String data : dataList) {
            preparedStatement.setInt(1,7);
            preparedStatement.setString(2,"1");
            preparedStatement.setInt(3,8);
            preparedStatement.setString(4,"1");
            preparedStatement.setDate(5, null);
            preparedStatement.setDate(6, null);
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        connection.commit();
        preparedStatement.close();
    }

    @Test
    public void describeAllTable() throws Exception {
        DatabaseMetaData dbMetaData = connection.getMetaData();
        ResultSet resultSet = dbMetaData.getTables(null, null, null, new String[]{"TABLE"});
        while (resultSet.next()) {
               System.out.println("表名：" + resultSet.getString("TABLE_NAME"));
               System.out.println("表类型：" + resultSet.getString("TABLE_TYPE"));
               System.out.println("表所属数据库：" + resultSet.getString("TABLE_CAT"));
               System.out.println("表备注：" + resultSet.getString("TABLE_NAME"));
        }
    }

    @Test
    public void describeOneTable() throws Exception {
        DatabaseMetaData dbMetaData = connection.getMetaData();
        ResultSet rs = dbMetaData.getColumns(null, "SCOTT", "BLOB_CLOB_DEMO", "%");
        while (rs.next()) {
            String columnName = rs.getString("COLUMN_NAME");
            String columnType = rs.getString("TYPE_NAME");
            int dataSize = rs.getInt("COLUMN_SIZE");
            int digits = rs.getInt("DECIMAL_DIGITS");
            int nullable = rs.getInt("NULLABLE");
            System.out.println(columnName+" "+columnType+" "+dataSize+" "+digits+" "+ nullable);
        }
    }


    /**
     * @description: oracle支持chr(0)字符串结束符，但是gauss，pgsql不支持
     * 解决bug: invalid byte sequence for encoding "utf8":0x00
     * https://blog.csdn.net/Shaul_Wong/article/details/122879093
     * @param: []
     * @return: void
     * @author: tianci
     * @date: 2022/6/9 10:00
     */
    @Test
    public void select() throws Exception {
        try {
            String[] tableNames = {
                    "TEST_USER"
            };
            List<String> tableNameList = new ArrayList<>(Arrays.asList(tableNames));
            for (String tableName : tableNameList) {
                System.out.println("table:" + tableName);
                ResultSet resultSet = statement.executeQuery("select * from " + tableName);
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int colCount = resultSetMetaData.getColumnCount();

                while (resultSet.next()) {
                    for (int i = 1; i <= colCount; i++) {
                        System.out.print(resultSet.getString(i) + "\t");;
                    }
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @description: oracle读取blob，clob，写入mysql
     * @param: []
     * @return: void
     * @author: tianci
     * @date: 2022/8/10 16:59
     */
    @Test
    public void oracle2Mysql() throws Exception {
        ResultSet resultSet = statement.executeQuery("select * from BLOB_CLOB_DEMO where id = 1");
        String id = null, clob = null;
        Blob blob = null;
        while (resultSet.next()) {
            id = resultSet.getString(1);
            blob = resultSet.getBlob(2);
            clob = resultSet.getString(3);
            // System.out.print(resultSet.getString(1) + "\t");
            // System.out.print(resultSet.getBlob(2) + "\t");
            // System.out.print(resultSet.getString(3) + "\t");
            System.out.println();
        }

        // 由于UTF-8是多字节编码，需要用多个字节来表示一个字符的编码，所以也就出现了在转换之后byte[]数组长度、内容不一致的情况。
        // 而ISO-8859-1编码是单字节编码
        String blobString = new String(blob.getBytes(1, (int) blob.length()), StandardCharsets.ISO_8859_1);
        Blob b = new SerialBlob(blobString.getBytes(StandardCharsets.ISO_8859_1));
        String url = "jdbc:mysql://127.0.0.1:3306/test?autoReconnect=true&autoReconnectForPools=true&useSSL=false&" +
                "rewriteBatchedStatements=true&allowMultiQueries=true" +
                "&characterEncoding=utf-8&serverTimezone=Asia/Shanghai";
        String userName = "root";
        String password = "123456";
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(url, userName, password);

        int result = -1;
        PreparedStatement pstmt = connection.prepareStatement("insert into blob_clob_demo values (?,?,?)");
        pstmt.setString(1, id);
        pstmt.setObject(2, b);
        pstmt.setString(3, clob);
        result = pstmt.executeUpdate();
        if(result>=0) {
            System.out.println("插入成功");
        }
    }

}
