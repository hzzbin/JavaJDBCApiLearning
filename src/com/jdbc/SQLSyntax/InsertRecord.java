package com.jdbc.SQLSyntax;

import com.jdbc.SQLSyntax.Base.SQLOperation;

import java.sql.Statement;

/**
 * Created by binzhang213309 on 2018/3/1.
 */
public class InsertRecord implements SQLOperation {

    @Override
    public void executeSQL(Statement stmt) throws Exception {
        String sql = "insert into registration " +
                "values(100, 'Zara', 'Ali', 18)";
        stmt.executeUpdate(sql);

        sql = "insert into registration " +
                "values(101, 'Mahnaz', 'Fatma', 25)";
        stmt.executeUpdate(sql);

        sql = "insert into registration " +
                "values(102, 'Zaid', 'Khan', 30)";
        stmt.executeUpdate(sql);

        sql = "insert into registration " +
                "values(103, 'Sumit', 'Mittal', 28)";
        stmt.executeUpdate(sql);
        System.out.println("Inserted records into the table...");
    }
}
