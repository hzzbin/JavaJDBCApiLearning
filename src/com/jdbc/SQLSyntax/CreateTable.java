package com.jdbc.SQLSyntax;

import com.jdbc.SQLSyntax.Base.SQLOperation;

import java.sql.Statement;

/**
 * Created by binzhang213309 on 2018/3/1.
 */
public class CreateTable implements SQLOperation {

    @Override
    public void executeSQL(Statement stmt) throws Exception {
        String sql = "drop table if exists registration";
        stmt.executeUpdate(sql);

        sql = "create table registration " +
                "(id integer not null, " +
                "first varchar(255), " +
                "last varchar(255), " +
                "age integer, " +
                " primary key (id))";
        stmt.executeUpdate(sql);
        System.out.println("Created table into given database...");
    }
}
