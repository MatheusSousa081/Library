package org.devJava.entity.library;

import org.devJava.entity.book.Book;
import org.devJava.entity.user.Client;
import org.devJava.exception.LibraryException;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.time.Year;
import java.util.Set;

public interface Library {
    void addBook(int id, String title, String author, Year year, Book.Gender gender) throws LibraryException;
    void addBook(Book book) throws LibraryException;
    void removeBook(int id) throws SQLException;
    void removeBook(Book book) throws SQLException;
    String getBook(int id);
    void getAllBooks();
    Set<Book> getAllBorrowedBooks();
    String getLoans();
    void loanBook (Book book, Client client) throws LibraryException, SQLException;
    void returnBook(Book book, Client client) throws LibraryException;
    void registerClient(Client client, String name, String email) throws SQLException;
}
