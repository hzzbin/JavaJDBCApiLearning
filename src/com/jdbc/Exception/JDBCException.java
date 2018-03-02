package com.jdbc.Exception;

import com.jdbc.DBConfig;
import com.jdbc.DBUtil;

import java.sql.*;

/**
 * Created by binzhang213309 on 2018/3/1.
 */
public class JDBCException {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(DBConfig.JDBC_DRIVER);

            conn = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.USER, DBConfig.PASS);

            stmt = conn.createStatement();
            String sql;
            sql = "select id, first, last, age from employees";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                String id = rs.getString("id");
                String age = rs.getString("age");
                String first = rs.getString("first");
                String last = rs.getString("last");

                DBUtil.displayDBData(id, age, first, last);
            }

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
