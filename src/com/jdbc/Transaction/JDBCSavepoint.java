package com.jdbc.Transaction;

import com.jdbc.DBConfig;
import com.jdbc.DBUtil;

import java.sql.*;

/**
 * Created by binzhang213309 on 2018/2/28.
 */
public class JDBCSavepoint {

    public static void main(String[] args) {

        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(DBConfig.JDBC_DRIVER);

            conn = DriverManager.getConnection(DBConfig.DB_URL,DBConfig.USER,DBConfig.PASS);

            conn.setAutoCommit(false);

            stmt = conn.createStatement();

            String sql = "select id, first, last, age from employees";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("List result set for reference....");
            DBUtil.displayDBDataWithResultSet(rs);

            Savepoint savepoint1 = conn.setSavepoint("ROWS_DELETED_1");
            System.out.println("Deleting row...");
            String SQL = "delete from employees " + "where id = 110";
            stmt.executeUpdate(SQL);

            // oops... we deleted too wrong employees!
            conn.rollback(savepoint1);

            Savepoint savepoint2 = conn.setSavepoint("ROWS_DELETED_2");
            System.out.println("Deleting row....");
            SQL = "delete from employees where id = 95";
            stmt.executeUpdate(SQL);

            sql = "select * from employees";
            rs = stmt.executeQuery(sql);
            DBUtil.displayDBDataWithResultSet(rs);

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
            System.out.println("Rolling backe data here....");
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
            DBUtil.cleanupDBConnection(stmt,conn);
        }
    }
}
