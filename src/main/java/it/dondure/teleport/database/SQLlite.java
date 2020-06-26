package it.dondure.teleport.database;

import it.dondure.TeleportSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLlite {
    private  final String path;
    protected Connection connection;
    public Connection getConnection() {
        return this.connection;
    }

    public SQLlite(String path) throws ClassNotFoundException {
        this.path = path;
        Class.forName("org.sqlite.JDBC");

    }


    public void openConnection() {
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:" +path);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connection = null;
    }

}
