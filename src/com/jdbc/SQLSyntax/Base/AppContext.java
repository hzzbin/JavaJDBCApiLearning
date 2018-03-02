package com.jdbc.SQLSyntax.Base;

import com.jdbc.DBConfig;
import com.jdbc.DBUtil;
import com.mysql.jdbc.StringUtils;

import java.sql.*;

/**
 * Created by binzhang213309 on 2018/3/1.
 */
public class AppContext {
    private Connection conn;
    private Statement stmt;

    public AppContext(String dbName) {
        try {
            Class.forName(DBConfig.JDBC_DRIVER);
            if (dbName == null) {
                conn = DriverManager.getConnection(DBConfig.DB_URL, DBConfig.USER, DBConfig.PASS);
            } else {
                String newDBUrl = DBConfig.DB_URL.replaceFirst("db_example", dbName);
                System.out.println("newDBUrl: " + newDBUrl);
                conn = DriverManager.getConnection(newDBUrl, DBConfig.USER, DBConfig.PASS);
            }

            System.out.println("Connected database successfully...");

            stmt = conn.createStatement();
        } catch (Exception se) {
            se.printStackTrace();
            DBUtil.cleanupDBConnection(stmt, conn);
        }
    }

    public AppContext () {
        this(null);
    }


    public void doWork (SQLOperation op) {
        try {
            if (conn.isClosed()) {
                String newDBUrl = DBConfig.DB_URL.replaceFirst("db_example", "students");
                System.out.println("newDBUrl: " + newDBUrl);
                conn = DriverManager.getConnection(newDBUrl, DBConfig.USER, DBConfig.PASS);
            }
            if (stmt.isClosed()) {
                stmt = conn.createStatement();
            }
            op.executeSQL(stmt);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.destroy();
        }
    }

    public void destroy() {
        DBUtil.cleanupDBConnection(stmt,conn);
    }

    public Statement getStatement() {
        return stmt;
    }

}
