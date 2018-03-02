package com.jdbc.Transaction;

import com.jdbc.DBConfig;
import com.jdbc.DBUtil;

import java.sql.*;

/**
 * Created by binzhang213309 on 2018/2/28.
 */
public class CommitRollback {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(DBConfig.JDBC_DRIVER);

            conn = DriverManager.getConnection(DBConfig.DB_URL,DBConfig.USER,DBConfig.PASS);

            // Set auto commit as false
            conn.setAutoCommit(false);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            String sql = "select * from employees";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("List result set for reference....");
            DBUtil.displayDBDataWithResultSet(rs);

            System.out.println("Inserting one row....");
            String SQL = "insert into employees " + "values (108, 20, 'Rita', 'Tez')";
            stmt.executeUpdate(SQL);


            // insert 104 that already exists in the table to cast a SQLException
            //SQL = "insert into employees " + "values (104, 22, 'Sita', 'Singh')";
            SQL = "insert into employees " + "values (109, 22, 'Sita', 'Singh')";
            stmt.executeUpdate(SQL);

            System.out.println("Committing data here....");
            conn.commit();


            // Now list all the available records.
            sql = "select * from employees";
            rs = stmt.executeQuery(sql);
            System.out.println("List result set for reference....");
            DBUtil.displayDBDataWithResultSet(rs);

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();

            // If there is an error then rollback the changes.
            System.out.println("Rolling back data here....");
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.cleanupDBConnection(stmt, conn);
        }
        System.out.println("Goodbye!");
    }
}
