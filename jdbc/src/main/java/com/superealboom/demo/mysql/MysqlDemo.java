package com.superealboom.demo.mysql;

import org.junit.Before;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;

/**
 * @description: TODO
 * @author: tianci
 * @date: 2022/6/10 10:19
 */
public class MysqlDemo {

    private Connection connection = null;
    private Statement statement = null;

    @Before
    public void before() throws Exception {
        String url = "jdbc:mysql://127.0.0.1:3306/test?autoReconnect=true&autoReconnectForPools=true&useSSL=false&" +
                "rewriteBatchedStatements=true&allowMultiQueries=true" +
                "&characterEncoding=utf-8&serverTimezone=Asia/Shanghai";;
        String userName = "root";
        String password = "123456";
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(url, userName, password);
        statement = connection.createStatement();
    }

    @Test
    public void select() throws Exception {
        String sql = "select * from sync_target";
        ResultSet rs = this.statement.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getString("target_auth_conf"));
            System.out.println(rs.getString("target_auth_info"));
        }
    }


    /**
     * @description: 读取mysql中的blob下载文件到本地
     * @param: []
     * @return: void
     * @author: tianci
     * @date: 2022/8/10 17:00
     */
    @Test
    public void readFile() throws Exception {
        InputStream is=null;
        FileOutputStream os=null;
        //获取数据
        String sql="select * from blob_clob_demo where id = 1";
        PreparedStatement ps=connection.prepareStatement(sql);
        ResultSet rs = rs=ps.executeQuery();
        if(rs.next()) {
            Blob blob=(Blob) rs.getBlob(2);
            //获取Blob对象的输入流
            is=blob.getBinaryStream();
            //创建指向桌面文件的流
            os=new FileOutputStream("/Users/tianci/Desktop/demo3.jpg");
            //输出到桌面
            byte []data=new byte[1024];
            int len;
            int i=0;
            while((len=is.read(data))!=-1) {
                i++;
                os.write(data, 0, len);
            }
        }
    }
}
