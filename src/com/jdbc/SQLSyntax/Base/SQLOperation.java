package com.jdbc.SQLSyntax.Base;

import java.sql.Statement;

/**
 * Created by binzhang213309 on 2018/3/1.
 */
public interface SQLOperation{

    void executeSQL(Statement stmt) throws Exception;
}
