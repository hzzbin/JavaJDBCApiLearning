package com.jdbc.StreamingData;

import com.jdbc.DBConfig;
import com.jdbc.DBUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;

/**
 * Created by binzhang213309 on 2018/3/1.
 */
public class JDBCStream {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(DBConfig.JDBC_DRIVER);

            conn = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.USER, DBConfig.PASS);

            stmt = conn.createStatement();

            String streamingDataSql = "create table xml_data (id integer, data text)";

            System.out.println("Creating XML_Data table...");
            stmt.executeUpdate("drop table if exists xml_data");
            stmt.executeUpdate(streamingDataSql);

            File f = new File("d:/TempUploadFile/XML_Data.xml");
            long fileLength = f.length();
            FileInputStream fis = new FileInputStream(f);

            // Create PreparedStatement and stream data
            String SQL = "insert into xml_data values (?,?)";
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, 100);
            pstmt.setAsciiStream(2, fis, (int)fileLength);
            pstmt.execute();

            fis.close();

            SQL = "select data from xml_data where id=100";
            rs = stmt.executeQuery(SQL);

            if (rs.next()) {
                // Retrieve data from input stream
                InputStream xmlInputStream = rs.getAsciiStream (1);
                int c;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                while ((c = xmlInputStream.read ()) != -1) {
                    bos.write(c);
                }
                System.out.println(bos.toString());
            }

            rs.close();
            stmt.close();
            pstmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.cleanupDBConnection(stmt, null);
            DBUtil.cleanupDBConnection(pstmt, conn);
        }
    }
}
