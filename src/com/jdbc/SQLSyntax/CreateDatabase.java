package com.jdbc.SQLSyntax;

import com.jdbc.SQLSyntax.Base.SQLOperation;

import java.sql.Statement;

/**
 * Created by binzhang213309 on 2018/3/1.
 */
public class CreateDatabase implements SQLOperation {

    @Override
    public void executeSQL(Statement stmt) throws Exception {
        String sql = "drop database if exists students";
        stmt.executeUpdate(sql);
        sql = "create database students";
        stmt.executeUpdate(sql);
        sql = "use students";
        stmt.executeUpdate(sql);
        System.out.println("Database created successfully...");
    }
}
