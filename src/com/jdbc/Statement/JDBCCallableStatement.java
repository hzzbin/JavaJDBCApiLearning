package com.jdbc.Statement;

import com.jdbc.DBConfig;
import com.jdbc.DBUtil;

import java.sql.*;

/**
 * Created by binzhang213309 on 2018/2/28.
 */
public class JDBCCallableStatement {

    public static void main(String[] args) {
        Connection conn = null;
        CallableStatement stmt = null;
        try {
            Class.forName(DBConfig.JDBC_DRIVER);
            conn = DriverManager.getConnection(DBConfig.DB_URL,DBConfig.USER,DBConfig.PASS);

            String sql = "{call getEmpName (?, ?)}";
            stmt = conn.prepareCall(sql);

            // Bind IN parameter first, then bing OUT parameter
            int empID = 102;
            stmt.setInt(1, empID);
            // Because second parameter is OUT so register it
            stmt.registerOutParameter(2, Types.VARCHAR);

            System.out.println("Executing stored procedure...");
            stmt.execute();

            String empName = stmt.getString(2);
            System.out.println("Emp Name with ID:" +
                    empID + " is " + empName);

            stmt.close();
            conn.close();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.cleanupDBConnection(stmt, conn);
        }
        System.out.println("Goodbye!");
    }
}
