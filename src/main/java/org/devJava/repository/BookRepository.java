package org.devJava.repository;

import org.devJava.entities.book.Book;
import org.devJava.repository.Connection.Database;

import java.sql.*;
import java.time.Year;

public class BookRepository implements CrudRepository<Book, Integer> {
    private Database connection;

    public Database getConnection() {
        return connection;
    }

    @Override
    public void create(Book book) {
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
    public String read(Integer id) {
        String query = "SELECT * FROM library.books WHERE id=?";
        try (PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return "Id: " + resultSet.getInt("id") +
                        "Title: " + resultSet.getString("title") +
                        "Author: " + resultSet.getString("author") +
                        "Year: " + Year.of(resultSet.getInt("year")) +
                        "Gender: " + Book.Gender.valueOf(resultSet.getString("gender")) +
                        "Status: " + Book.Status.valueOf(resultSet.getString("status"));
            } else {
                return "Book not found!";
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error read the book");
        }
    }

    @Override
    public void update(Book entity) {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public String findAll() {
        String query = "SELECT * FROM books";
        try (Statement statement = connection.getConnection().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery(query);

        } catch (SQLException e) {
            throw new RuntimeException("Error read the books");
        }
    }
}
