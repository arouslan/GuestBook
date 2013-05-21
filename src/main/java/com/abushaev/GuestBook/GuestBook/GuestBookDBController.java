package com.abushaev.GuestBook.GuestBook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Author: Abushaev Ruslan
 * e-mail: rouslan@inbox.ru
 * Date: 20.05.13
 * Time: 20:11
 */
public class GuestBookDBController implements GuestBookController, AutoCloseable {
    private Connection connection = null;
    private ResultSet res = null;
    private Statement statement = null;


    public GuestBookDBController(String user, String psw) {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(Conf.DB_PATH, user, psw);
            statement = connection.createStatement();
            CreateTableIfNotPresentTry(Conf.TABLE_NAME);
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(GuestBookDBController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    private void CreateTableIfNotPresent(String tableName) {

        DatabaseMetaData meta = null;
        try {
            meta = connection.getMetaData();

            res = meta.getTables(null, null, tableName, new String[]{"TABLE"});

            if (res.next()) {
            } else {
                // Если нет таблици сделаем
                statement.executeUpdate("CREATE TABLE posts (ID INT  PRIMARY KEY AUTO_INCREMENT," +
                        " date TIMESTAMP, " +
                        "comment VARCHAR(255));");
            }
            ;

        } catch (SQLException e) {
            Logger.getLogger(GuestBookDBController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    // Через MetaData не сработало определение наличия таблицы, поэтому пойдем по грубому пути!!!
    private void CreateTableIfNotPresentTry(String tableName) {

        DatabaseMetaData meta = null;
        try {
            statement.executeUpdate("CREATE TABLE " + tableName + " (ID INT  PRIMARY KEY AUTO_INCREMENT," +
                    " date TIMESTAMP, " +
                    "comment VARCHAR(255));");

        } catch (SQLException e) {

        }
    }

    @Override
    public void addRecord(String message) {
        try {
            statement.executeUpdate("INSERT INTO PUBLIC.POSTS ( DATE, COMMENT)" +
                    " VALUES (CURRENT_TIMESTAMP(), '" + message + "');");
        } catch (SQLException e) {
            Logger.getLogger(GuestBookDBController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @Override
    public List<Record> getRecords() {
        List<Record> list = new ArrayList<Record>();
        try {
            res = statement.executeQuery("SELECT * FROM " + Conf.TABLE_NAME);

            while (res.next()) {
                list.add(new Record(res.getLong("ID"), res.getTimestamp("DATE"), res.getNString("COMMENT")));
            }

        } catch (SQLException e) {
            Logger.getLogger(GuestBookDBController.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    @Override
    public void close() throws Exception {
        try {
            if (connection != null) connection.close();
            if (statement != null) statement.close();
            if (res != null) res.close();

        } catch (SQLException e) {
            Logger.getLogger(GuestBookDBController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
