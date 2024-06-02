package org.devJava.repository;

import org.devJava.entitie.user.Client;
import org.devJava.repository.Connection.Database;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;


public class ClientRepository implements CrudRepository<Client, Integer> {
    private final @NotNull Database connection;

    public ClientRepository(@NotNull Database connection) {
        this.connection = connection;
    }

    public @NotNull Database getConnection() {
        return connection;
    }

    @Override
    public void create(@NotNull Client client) {
        String query = "INSERT INTO library.users (id name, email, quantity_books_borrowed) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, client.getId());
            preparedStatement.setString(2, client.getName());
            preparedStatement.setString(3, client.getEmail());
            preparedStatement.setInt(4, client.size());
            preparedStatement.executeUpdate();
            System.out.println("Client added successfully!");
        } catch (SQLException e) {
            throw new RuntimeException("Error adding the user in repository");
        }
    }

    @Override
    public String read(@NotNull Integer id) {
        String query = "Select * FROM library.users WHERE id=?";
        try (PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return "Id: " + resultSet.getInt("id") +
                        ", " + resultSet.getString("name") +
                        ", " + resultSet.getString("email") +
                        ", " + resultSet.getInt("quantity_books_borrowed") +
                        " books borrowed.\n";
            } else {
                return "Client not found!";
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error read the user");
        }
    }

    @Override
    public void delete(@NotNull Integer id) {
        String query = "DELETE FROM library.users WHERE id=?";
        try (PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Client removed successfully!");
        } catch (SQLException e) {
            throw new RuntimeException("Error delete client");
        }
    }

    @Override
    public void update(@NotNull Client client) {
        String query = "UPDATE library.users SET name=?, email=?, quantity_books_borrowed=? WHERE id=?";
        try (PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getEmail());
            preparedStatement.setInt(3, client.size());
            preparedStatement.setInt(4, client.getId());
            preparedStatement.executeUpdate();
            System.out.println("Client updated successfully!");
        } catch (SQLException e) {
        throw new RuntimeException("Error update the book");
        }
    }

    @Override
    public Set<Client> findAll() {
        String query = "SELECT * FROM library.users";
        Set<Client> clients = new HashSet<>();
        try (PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Client client = new Client(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email")
                );
                clients.add(client);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error read the books");
        }
        return clients;
    }
}