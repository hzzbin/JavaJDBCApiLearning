package com.jdbc.Statement;

import com.jdbc.DBConfig;
import com.jdbc.DBUtil;

import java.sql.*;

/**
 * Created by binzhang213309 on 2018/2/28.
 */
public class JDBCPreparedStatement {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName(DBConfig.JDBC_DRIVER);

            conn = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.USER,DBConfig.PASS);

            String sql = "update employees set age=? where id=?";
            stmt = conn.prepareStatement(sql);

            // Bind values into the parameters.
            stmt.setInt(1, 350);
            stmt.setInt(2, 102);

            int rows = stmt.executeUpdate();
            System.out.println("Rows impacted : " + rows );

            sql = "select id, first, last, age from employees";
            ResultSet rs = stmt.executeQuery(sql);

            DBUtil.displayDBDataWithResultSet(rs);

            rs.close();
            stmt.close();
            conn.close();
        } catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.cleanupDBConnection(stmt, conn);
        }

        System.out.println("Goodbye!");
    }
}
