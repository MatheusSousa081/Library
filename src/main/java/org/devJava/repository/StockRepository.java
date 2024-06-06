package org.devJava.repository;

import org.devJava.entity.book.Book;
import org.devJava.entity.library.StockItem;
import org.devJava.repository.Connection.Database;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;

public class StockRepository implements CrudRepository<StockItem, Integer> {
    private final @NotNull Database connection;

    public StockRepository(@NotNull Database connection) {
        this.connection = connection;
    }

    public final @NotNull Database getConnection() {
        return connection;
    }

    @Override
    public final void create(@NotNull StockItem stock) {
        String query = "INSERT INTO library.stock (book_id, quantity) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, stock.getBook().getId());
            preparedStatement.setInt(2, stock.getQuantity());
            preparedStatement.executeUpdate();
            System.out.println("Book added successfully in stock");
        } catch (SQLException e) {
            throw new RuntimeException("Error registering book in stock");
        }
    }

    @Override
    public final String read(@NotNull Integer id) {
        String query = "SELECT b.id, b.title, b.author, b.year, b.gender, s.quantity " +
                "FROM library.stock s " +
                "JOIN library.books b ON s.book_id = b.id " +
                "WHERE b.id = ?";
        try (PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return "Id: " + resultSet.getInt("id") +
                        ", " + resultSet.getString("title") +
                        " by " + resultSet.getString("author") +
                        " published in " + Year.of(resultSet.getInt("year")) +
                        ", " + Book.Gender.valueOf(resultSet.getString("gender")) +
                        ", quantity=" + resultSet.getInt("quantity") + "\n";
            } else {
                return "Book not found in stock!";
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error reading the book from stock");
        }
    }

    @Override
    public final void delete(@NotNull Integer id) {
        String query = "DELETE FROM library.stock WHERE book_id=?";
        try (PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Book removed from stock");
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting the book from stock");
        }
    }

    @Override
    public final void update(@NotNull StockItem stock) {
        String query = "UPDATE library.stock s SET s.quantity=? WHERE book_id=?";
        try (PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, stock.getQuantity());
            preparedStatement.setInt(2, stock.getBook().getId());
            preparedStatement.executeUpdate();
            System.out.println("Stock updated successfully!");
        } catch (SQLException e) {
            throw new RuntimeException("Error updating the stock");
        }
    }

    @Override
    public final String findAll() {
        String query = "SELECT b.id, b.title, b.author, b.year, b.gender, s.quantity" +
                "FROM library.stock s" +
                "JOIN library.books b ON s.book_id = b.id";
        StringBuilder result = new StringBuilder();
        try (PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                result.append("Id: ").append(resultSet.getInt("id")).append(", ").append(resultSet.getString("title"))
                        .append(" by ").append(resultSet.getString("author")).append(" published in ").append(resultSet.getInt("year")).append(", ")
                        .append(Book.Gender.valueOf(resultSet.getString("gender"))).append(", quantity=").append(resultSet.getInt("quantity")).append("\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error showing the books");
        }
        return result.toString();
    }
}