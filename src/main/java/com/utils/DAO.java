package com.utils;

import java.sql.SQLException;
import java.util.Map;

/**
 * @author Dmitry Sherstobitov
 *         QA LSE at intetics.tshop
 *         skype: dmitry_sherstobitov
 */
public class DAO {

    MySqlConnector connector;

    public DAO() throws Exception {
        connector = new MySqlConnector();
    }

    public DAO(String remote_host) throws Exception {
        connector = new MySqlConnector(remote_host);
    }

    public void execute(String querry) throws ClassNotFoundException, SQLException {
        connector.execUpdate(querry);
    }

    public Map<String, String> get_results_from(String querry, String col) throws ClassNotFoundException, SQLException {
        return connector.readResCol(querry, col);
    }
}
