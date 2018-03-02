package com.jdbc.SQLSyntax;

import com.jdbc.SQLSyntax.Base.AppContext;

/**
 * Created by binzhang213309 on 2018/3/1.
 */
public class Main {
    public static AppContext appContext = new AppContext("");

    public static void main(String[] args) {
        CreateDatabase cdb = new CreateDatabase();
        CreateTable createTable = new CreateTable();
        InsertRecord insertRecord = new InsertRecord();
        SelectRecord selectRecord = new SelectRecord();
        UpdateRecord updateRecord = new UpdateRecord();
        SortingData sortingData = new SortingData();
        WhereClause whereClause = new WhereClause();
        LikeClause likeClause = new LikeClause();
        DeleteRecord deleteRecord = new DeleteRecord();
        DropTable dropTable = new DropTable();
        DropDatabase dropDatabase = new DropDatabase();


        appContext.doWork(cdb);
        appContext.doWork(createTable);
        appContext.doWork(insertRecord);
        appContext.doWork(selectRecord);
        appContext.doWork(updateRecord);
        appContext.doWork(sortingData);
        appContext.doWork(whereClause);
        appContext.doWork(likeClause);
        appContext.doWork(deleteRecord);
        appContext.doWork(dropTable);
        appContext.doWork(dropDatabase);


        appContext.destroy();
        System.out.println("Goodbye!");
    }
}
