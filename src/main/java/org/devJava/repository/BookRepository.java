package org.devJava.repository;

import org.devJava.entitie.book.Book;
import org.devJava.repository.Connection.Database;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.time.Year;
import java.util.HashSet;
import java.util.Set;

public class BookRepository implements CrudRepository<Book, Integer> {
    private final @NotNull Database connection;

    public BookRepository(@NotNull Database connection) {
        this.connection = connection;
    }

    public @NotNull Database getConnection() {
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
            System.out.println("Book added successfully!");
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
        String query = "UPDATE library.books SET title=?, author=?, year=?, gender=?, status=? WHERE id=?";
        try (PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setInt(3, book.getYear().getValue());
            preparedStatement.setString(4, book.getGender().name());
            preparedStatement.setString(5, book.getStatus().name());
            preparedStatement.setInt(6, book.getId());
            preparedStatement.executeUpdate();
            System.out.println("Book updated successfully!");
        } catch (SQLException e) {
            throw new RuntimeException("Error update the book");
        }
    }

    @Override
    public void delete(@NotNull Integer id) {
        String query = "DELETE FROM library.books WHERE id=?";
        try (PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Book removed successfully!");
        } catch (SQLException e) {
            throw new RuntimeException("Error delete the book");
        }
    }

    @Override
    public Set<Book> findAll() {
        String query = "SELECT * FROM library.books";
        Set<Book> books = new HashSet<>();
        try (PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Book book = new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        Year.of(resultSet.getInt("year")),
                        Book.Gender.valueOf(resultSet.getString("gender")));
                books.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error read the books");
        }
        return books;
    }
}