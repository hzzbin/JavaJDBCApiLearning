package com.jdbc.ResultSet;

import com.jdbc.DBConfig;
import com.jdbc.DBUtil;

import java.sql.*;

/**
 * Created by binzhang213309 on 2018/2/28.
 */
public class NavigationResultSet {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(DBConfig.JDBC_DRIVER);

            conn = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.USER,DBConfig.PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            String sql;
            sql = "select id, first, last, age from employees";
            ResultSet rs = stmt.executeQuery(sql);

            DBUtil.displayDBDataWithResultSet(rs);

            // Move cursor to the last now.
            System.out.println("Moving cursor to the last...");
            rs.last();

            String id = Integer.toString(rs.getInt("id"));
            String age = rs.getString("age");
            String first = rs.getString("first");
            String last = rs.getString("last");

            DBUtil.displayDBData(id, age, first, last);

            // Move cursor to the first row.
            System.out.println("Moving cursor to the first row...");
            rs.first();

            id = rs.getString("id");
            age = rs.getString("age");
            first = rs.getString("first");
            last = rs.getString("last");

            DBUtil.displayDBData(id, age, first, last);


            // Move cursor to the next row.
            System.out.println("Moving cursor to the next row...");
            rs.next();

            id = rs.getString("id");
            age = rs.getString("age");
            first = rs.getString("first");
            last = rs.getString("last");

            DBUtil.displayDBData(id, age, first, last);

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
