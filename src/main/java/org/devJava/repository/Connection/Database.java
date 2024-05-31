package org.devJava.repository.Connection;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.*;

public class Database {
    private final @NotNull String url;
    private final @Nullable String username;
    private final @Nullable String password;

    public Database(@NotNull String url, @Nullable String username, @Nullable String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    private @Nullable Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public @NotNull String getUrl() {
        return url;
    }

    public @Nullable String getUsername() {
        return username;
    }

    public @Nullable String getPassword() {
        return password;
    }

    public final void showInfo() throws SQLException {
        System.out.println(connection.getClientInfo());
    }

    public final void createConnection() throws SQLException {
        if (isConnected()) {
            throw new IllegalStateException("Existing connection");
        }
        connection = DriverManager.getConnection(url, username, password);
        System.out.println("Connected to the database");
    }

    public final void closeConnection() throws SQLException {
        if (!isConnected()) {
            throw new IllegalStateException("Connection not existing");
        }
        connection.close();
        connection = null;
        System.out.println("Connection closed");
    }

    public final boolean isConnected() {
        return connection != null;
    }
}
