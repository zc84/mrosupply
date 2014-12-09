package com.utils;


import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class MySqlConnector {

    private static Logger logger = Logger.getLogger(MySqlConnector.class);
    final int dbPort = 3306;
    String driverName = "com.mysql.jdbc.Driver";
    Connection connection;
    String serverName;
    String database;
    String username;
    String password;

    public MySqlConnector() throws Exception {
        close();

        serverName = LoadProperties.loadProperty("serverName");

        logger.info("Init connection to the local " + serverName + " host");

        database = LoadProperties.loadProperty("database");
        username = LoadProperties.loadProperty("username");
        password = LoadProperties.loadProperty("password");
        createConnection();
    }

    public MySqlConnector(String database) throws Exception {
        close();

        serverName = LoadProperties.loadProperty("serverName");

        logger.info("Init connection to the local " + serverName + " host");

        this.database = database;
        username = LoadProperties.loadProperty("username");
        password = LoadProperties.loadProperty("password");
        createConnection();
    }

    public void createConnection() throws Exception {

        logger.info("Creating connection to " + serverName);
        String dbUrl = "jdbc:mysql://" + serverName + ":" + dbPort + "/" + database + "?&autoReconnect=true&failOverReadOnly=false&maxReconnects=10";

        Class.forName(driverName);

        int limiter = 0;
        String error = "";

        do {
            try {
                error = "";
                connection = DriverManager.getConnection(dbUrl, username, password);
                break;
            } catch (Exception e) {
                error = e.getMessage();
                limiter++;
                Thread.sleep(5000);
            }
        } while (limiter < 5);

        if (!error.isEmpty()) {
            throw new Exception(error);
        }
    }

    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public Statement creteSelect() throws SQLException, ClassNotFoundException {
        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        return stmt;
    }

    public Statement creteUpdate() throws SQLException, ClassNotFoundException {
        Statement stmt = connection.createStatement();
        return stmt;
    }

    public ResultSet execSelect(String querry) throws SQLException, ClassNotFoundException {
        ResultSet rs;
        logger.info("Exceute sql querry *** " + querry + " ***");
        rs = creteSelect().executeQuery(querry);
        return rs;
    }

    public void execUpdate(String querry) throws SQLException, ClassNotFoundException {
        logger.info("Exceute sql querry *** " + querry + " ***");

        String[] querry_batches = querry.split(";");
        Statement stmt = creteUpdate();

        for (String batch : querry_batches) {
            if (batch.length() > 3) stmt.addBatch(batch);
        }
        stmt.executeBatch();
    }

    public Map<String, String> readResCol(String querry, String colList) throws SQLException, ClassNotFoundException {
        Map<String, String> resList = new LinkedHashMap<>();
        ResultSet rs = execSelect(querry);

        while (rs.next()) {
            for (String col : colList.split(","))
                resList.put(col, rs.getString(col));
        }
        return resList;
    }
}
