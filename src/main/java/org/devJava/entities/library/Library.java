package org.devJava.entities.library;

import org.devJava.entities.book.Book;
import org.devJava.entities.user.User;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface Library {
    void addBook(@NotNull Book book);

    void removeBook(@NotNull Book book);

    void listBook();

    void loanBook(User user, String title);
    void returnBook(User user, String title);
    void registerUser(@NotNull Long id,@NotNull String name);
}
