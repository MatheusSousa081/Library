package org.devJava.entities.library;

import org.devJava.entities.book.Book;
import org.devJava.entities.user.User;
import org.jetbrains.annotations.NotNull;

import java.time.Year;

public interface Library {
    void addBook(@NotNull Book book);
    void addBook(@NotNull String title, @NotNull String author, @NotNull Book.Gender gender, @NotNull Year year);
    void removeBook(@NotNull Book book);
    void removeBook(@NotNull String title);
    void listBook();
    void listBorrowedBooks();
    void loanBook(@NotNull User user,@NotNull String title);
    void returnBook(@NotNull User user,@NotNull String title);
    void registerUser(@NotNull User user,@NotNull Long id);
}
