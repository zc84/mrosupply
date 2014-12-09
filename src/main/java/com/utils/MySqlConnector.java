package com.utils;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.apache.log4j.Logger;

import java.net.ServerSocket;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

public class MySqlConnector {

    private static Logger logger = Logger.getLogger(MySqlConnector.class);
    final int dbPort = 3306;
    final int sshPort = 22;
    String driverName = "com.mysql.jdbc.Driver";
    Connection connection;
    Session session;
    String serverName;
    String database;
    String username;
    String password;
    String sshUser;
    String sshPassword;
    String sshHost;
    int sshLocalPort;
    ServerSocket serverSocket;

    public MySqlConnector() throws Exception {
        close();

        serverName = LoadProperties.loadProperty("serverName");

        logger.info("Init connection to the local " + serverName + " host");

        database = LoadProperties.loadProperty("database");
        username = LoadProperties.loadProperty("username");
        password = LoadProperties.loadProperty("password");
        createConnection();
    }

    // public MySqlConnector(String sshHost) throws NumberFormatException,
    // JSchException, SQLException, IOException, ClassNotFoundException {
    //
    // serverSocket = new ServerSocket(0);
    //
    // serverName = LoadProperties.loadProperty("serverName");
    // database = LoadProperties.loadProperty("database");
    // username = LoadProperties.loadProperty("username");
    // password = LoadProperties.loadProperty("password");
    //
    // sshUser = LoadProperties.loadProperty("sshUser");
    // sshPassword = LoadProperties.loadProperty("sshPassword");
    // sshLocalPort = serverSocket.getLocalPort();
    // this.sshHost = sshHost;
    //
    // logger.info("Init connection to the remote " + sshHost + " host");
    // createSSHsession();
    // createConnection();
    // }

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

    public void createSSHsession() throws NumberFormatException, JSchException, SQLException {

        logger.info("Init port forwarding to the " + sshHost);
        JSch jsch = new JSch();
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");

        session = jsch.getSession(sshUser, sshHost, sshPort);
        session.setPassword(sshPassword);
        session.setConfig(config);
        session.setTimeout(0);
        session.connect();
        int assinged_port = session.setPortForwardingL(sshLocalPort, sshHost, dbPort);

        logger.info("Forward data " + sshHost + ":" + dbPort + " --> " + serverName + ":" + assinged_port);
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
