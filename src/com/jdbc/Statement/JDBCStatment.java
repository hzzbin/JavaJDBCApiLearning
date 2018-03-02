package com.jdbc.Statement;

import com.jdbc.DBConfig;
import com.jdbc.DBUtil;

import java.sql.*;

/**
 * Created by binzhang213309 on 2018/2/28.
 */
public class JDBCStatment {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.USER, DBConfig.PASS);

            stmt = conn.createStatement();
            String sql = "update employees set age=300 where id=103";

            // Let us check if it returns a true Result Set or not.
            Boolean ret = stmt.execute(sql);
            System.out.println("Return value is : " + ret.toString() );

            int rows = stmt.executeUpdate(sql);
            System.out.println("Rows impacted : " + rows );

            sql = "select id, first, last, age from employees";
            ResultSet rs = stmt.executeQuery(sql);

            DBUtil.displayDBDataWithResultSet(rs);

            rs.close();
            stmt.close();
            conn.close();
        } catch(SQLException sex) {
            sex.printStackTrace();
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if(stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex2) {
                // Nothing we can do
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch(SQLException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}
