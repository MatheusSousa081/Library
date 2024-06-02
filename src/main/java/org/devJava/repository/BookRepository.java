package org.devJava.repository;

import org.devJava.entitie.book.Book;
import org.devJava.repository.Connection.Database;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.time.Year;

public class BookRepository implements CrudRepository<Book, Integer> {
    private Database connection;

    public Database getConnection() {
        return connection;
    }

    @Override
    public void create(@NotNull Book book) {
        String query = "INSERT  INTO library.books (id, title, author, `year`, gender, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, book.getId());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setInt(4, book.getYear().getValue());
            preparedStatement.setString(5, book.getGender().name());
            preparedStatement.setString(6, book.getStatus().name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error adding the book in repository");
        }
    }

    @Override
    public String read(@NotNull Integer id) {
        String query = "SELECT * FROM library.books WHERE id=?";
        try {
            try (PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query)){
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    return "Id: " + resultSet.getInt("id") +
                            ", " + resultSet.getString("title") +
                            " by " + resultSet.getString("author") +
                            "published in " + Year.of(resultSet.getInt("year")) +
                            ", " + Book.Gender.valueOf(resultSet.getString("gender")) +
                            ", " + Book.Status.valueOf(resultSet.getString("status")) + "\n";
                } else {
                    return "Book not found!";
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error read the book");
        }
    }

    @Override
    public void update(@NotNull Book book) {

    }

    @Override
    public void delete(@NotNull Integer id) {

    }

    @Override
    public String findAll() {
        String query = "SELECT * FROM books";
        StringBuilder result = new StringBuilder();
        try (PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                result.append("Id: ").append(resultSet.getInt("id")).append(", ").append(resultSet.getString("title")).append(" by ").append(resultSet.getString("author")).append("published in ").append(Year.of(resultSet.getInt("year"))).append(", ").append(Book.Gender.valueOf(resultSet.getString("gender"))).append(", ").append(Book.Status.valueOf(resultSet.getString("status"))).append("\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error read the books");
        }
        return result.toString();
    }
}
