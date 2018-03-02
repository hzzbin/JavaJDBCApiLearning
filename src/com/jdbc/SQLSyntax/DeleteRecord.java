package com.jdbc.SQLSyntax;

import com.jdbc.DBUtil;
import com.jdbc.SQLSyntax.Base.SQLOperation;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by binzhang213309 on 2018/3/1.
 */
public class DeleteRecord implements SQLOperation {

    @Override
    public void executeSQL(Statement stmt) throws Exception {
        String sql = "delete from registration " +
                "where id = 101";
        stmt.executeUpdate(sql);

        sql = "select * from registration";
        ResultSet rs = stmt.executeQuery(sql);
        DBUtil.displayDBDataWithResultSet(rs);
        rs.close();
    }
}
