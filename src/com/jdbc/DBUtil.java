package com.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by binzhang213309 on 2018/2/28.
 */
public class DBUtil {

    public static void displayDBData(String... datas) {
        for(String item : datas) {
            System.out.print(item + " ");
        }
        if (datas.length > 0) {
            System.out.println();
        }
    }

    public static void displayDBDataWithResultSet(ResultSet rs)
            throws SQLException {
        if(rs != null && !rs.isClosed()) {
            // Ensure we start with first row
            rs.beforeFirst();

            while (rs.next()) {
                String id = Integer.toString(rs.getInt("id"));
                String age = Integer.toString(rs.getInt("age"));
                String first = rs.getString("first");
                String last = rs.getString("last");

                DBUtil.displayDBData(id, age, first, last);
            }
        }
    }

    public static void cleanupDBConnection(Statement stmt, Connection conn) {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
