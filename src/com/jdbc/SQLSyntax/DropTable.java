package com.jdbc.SQLSyntax;

import com.jdbc.SQLSyntax.Base.SQLOperation;

import java.sql.Statement;

/**
 * Created by binzhang213309 on 2018/3/1.
 */
public class DropTable implements SQLOperation {

    @Override
    public void executeSQL(Statement stmt) throws Exception {
        String sql = "drop table registration";
        stmt.executeUpdate(sql);
        System.out.println("Table deleted in given database...");
    }
}
