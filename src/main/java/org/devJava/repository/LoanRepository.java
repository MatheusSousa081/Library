package org.devJava.repository;

import org.devJava.entity.loan.Loan;
import org.devJava.repository.Connection.Database;
import org.jetbrains.annotations.NotNull;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;

public class LoanRepository implements CrudRepository<Loan, Integer>{
    private final @NotNull Database connection;

    public LoanRepository(@NotNull Database connection) {
        this.connection = connection;
    }

    public final @NotNull Database getConnection() {
        return connection;
    }

    @Override
    public final void create(@NotNull Loan loan) {
        String query = "INSERT INTO library.loans (id, user_id, book_id, loan_date, return_date) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, loan.getId());
            preparedStatement.setInt(2, loan.getUserId());
            preparedStatement.setInt(3, loan.getBookId());
            preparedStatement.setDate(4, Date.valueOf(loan.getLoanDate().atZone(ZoneId.systemDefault()).toLocalDate()));
            preparedStatement.setDate(5, Date.valueOf(loan.getReturnDate().atZone(ZoneId.systemDefault()).toLocalDate()));
            preparedStatement.executeUpdate();
            System.out.println("Loan added with successfully!");
        } catch (SQLException e) {
            throw new RuntimeException("Error in loan");
        }
    }

    @Override
    public final String read(@NotNull Integer id) {
        String query = "SELECT l.id ,l.loan_date, l.return_date, c.id as client_id, c.name as client_name, " +
                "b.id as book_id, b.title FROM library.loans l JOIN library.users c ON l.user_id = c.id" +
                "JOIN library.books b ON l.book_id = b.id WHERE l.id=?";
        try (PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return "Loan id: " + resultSet.getInt("id") +
                        "\nLoan date: " + resultSet.getDate("loan_date").toInstant() +
                        "\nReturn date: " + resultSet.getDate("return_date").toInstant() +
                        "\nClient: " + resultSet.getInt("client_id") + ", " + resultSet.getString("client_name") +
                        "\nBook: " + resultSet.getInt("book_id") + ", " + resultSet.getString("title") + "\n";
            } else {
                return "Loan not found";
            }
        } catch (SQLException e){
            throw new RuntimeException("Error in reading the loan");
        }
    }

    @Override
    public final void delete(@NotNull Integer id) {
        String query = "DELETE FROM library.loans WHERE id=?";
        try (PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Loan deleted successfully!");
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting the loan");
        }
    }

    @Override
    public final void update(@NotNull Loan loan) {
        String query = "UPDATE library.loans SET user_id=?, book_id=?, loan_date=?, return_date=? WHERE id=?";
        try (PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, loan.getUserId());
            preparedStatement.setInt(2, loan.getBookId());
            preparedStatement.setDate(3, Date.valueOf(loan.getLoanDate().atZone(ZoneId.systemDefault()).toLocalDate()));
            preparedStatement.setDate(4, Date.valueOf(loan.getReturnDate().atZone(ZoneId.systemDefault()).toLocalDate()));
            preparedStatement.setInt(5, loan.getId());
            preparedStatement.executeUpdate();
            System.out.println("Loan Updated successfully!");
        } catch (SQLException e) {
            throw new RuntimeException("Error update the loan");
        }
    }

    @Override
    public final String findAll() {
        String query = "SELECT l.id ,l.loan_date, l.return_date, c.id as client_id, c.name as client_name," +
                "b.id as book_id, b.title FROM library.loans l JOIN library.users c ON l.user_id = c.id" +
                "JOIN library.books b ON l.book_id = b.id;";
        StringBuilder result = new StringBuilder();
        try (PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                result.append("Loan id: ").append(resultSet.getInt("id"))
                        .append("\nLoan date: ").append(resultSet.getDate("loan_date").toInstant()).append("\nReturn date: ").append(resultSet.getDate("return_date").toInstant())
                        .append("\nClient: ").append(resultSet.getInt("client_id")).append(", ").append(resultSet.getString("client_name"))
                        .append("\nBook: ").append(resultSet.getInt("book_id")).append(", ").append(resultSet.getString("title")).append("\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error show the loans");
        }
        return result.toString();
    }
}