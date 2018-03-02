package com.jdbc.Batch;

import com.jdbc.DBConfig;
import com.jdbc.DBUtil;

import java.sql.*;

/**
 * Created by binzhang213309 on 2018/3/1.
 */
public class JDBCBatch {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(DBConfig.JDBC_DRIVER);

            conn = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.USER, DBConfig.PASS);
            stmt = conn.createStatement();

            // JDBC drivers are not required to support Batch Processing.
            // Ensure target database supporting batch.
            boolean isSupportBatch = conn.getMetaData().supportsBatchUpdates();
            if(!isSupportBatch) {
                return;
            }

            conn.setAutoCommit(false);

            String sql = "select * from employees";
            ResultSet rs = stmt.executeQuery(sql);
            DBUtil.displayDBDataWithResultSet(rs);


            // Create batch of SQL statement
            String SQL = "insert into employees (id, first, last, age) " +
                    "values(200, 'Zia', 'Ali', 30)";
            // Add above SQL statement in the batch
            stmt.addBatch(SQL);

            SQL = "insert into employees (id, first, last, age) " +
                    "values(201, 'Raj', 'Kumar', 35)";
            stmt.addBatch(SQL);

            SQL = "update employees set age = 35 " +
                    "where id = 100";
            stmt.addBatch(SQL);

            int[] count = stmt.executeBatch();

            for (int i : count) {
                System.out.println(i);
            }

            // Explicitly commit statements to apply changes
            conn.commit();


            sql = "select * from employees";
            rs = stmt.executeQuery(sql);
            DBUtil.displayDBDataWithResultSet(rs);

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.cleanupDBConnection(stmt, conn);
        }
    }
}
