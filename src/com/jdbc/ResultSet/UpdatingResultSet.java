package com.jdbc.ResultSet;

import com.jdbc.DBConfig;
import com.jdbc.DBUtil;

import java.sql.*;

/**
 * Created by binzhang213309 on 2018/2/28.
 */
public class UpdatingResultSet {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(DBConfig.JDBC_DRIVER);

            conn = DriverManager.getConnection(DBConfig.DB_URL,DBConfig.USER,DBConfig.PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String sql = "select id, first, last, age from employees";
            ResultSet rs = stmt.executeQuery(sql);

            DBUtil.displayDBDataWithResultSet(rs);

            // Move to BFR position so while-loop works properly
            rs.beforeFirst();
            while(rs.next()) {
                int newAge = rs.getInt("age") + 5;
                rs.updateDouble("age", newAge);
                rs.updateRow();
            }
            System.out.println("List result set showing new ages...");
            DBUtil.displayDBDataWithResultSet(rs);

            // Insert a record into the table.
            // Move to insert row and add column data with updateXXX()
            System.out.println("Inserting a new record...");
            rs.moveToInsertRow();
            rs.updateInt("id", 104);
            rs.updateString("first","John");
            rs.updateString("last","Paul");
            rs.updateInt("age", 40);

            rs.insertRow();

            System.out.println("List result set showing new set...");
            DBUtil.displayDBDataWithResultSet(rs);

            // Delete second record from the table.
            // Set position to second record first
            rs.absolute(2);
            System.out.println("List the record before deleting...");
            String id = rs.getString("id");
            String age = rs.getString("age");
            String first = rs.getString("first");
            String last = rs.getString("last");

            DBUtil.displayDBData(id, age, first, last);

            rs.deleteRow();
            System.out.println("List result set after deleting one records...");
            DBUtil.displayDBDataWithResultSet(rs);

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.cleanupDBConnection(stmt,conn);
        }

        System.out.println("Goodbye!");
    }
}
