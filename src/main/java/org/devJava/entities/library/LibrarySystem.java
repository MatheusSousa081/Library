package org.devJava.entities.library;

import org.devJava.entities.book.Book;
import org.devJava.entities.user.User;
import org.devJava.exceptions.LibraryException;
import org.devJava.exceptions.UserException;
import org.jetbrains.annotations.NotNull;

import java.time.Year;
import java.util.*;

public class LibrarySystem implements Library{
    private List<Book> listOfBooks = new ArrayList<>();
    private List<Book> borrowedBooks = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    public LibrarySystem() {
    }

    @Override
    public void addBook(@NotNull Book book) {
        listOfBooks.add(book);
        System.out.println("Book added successfully");
        listOfBooks.sort(Comparator.comparing(Book::getTitle, String.CASE_INSENSITIVE_ORDER));
    }

    @Override
    public void addBook(@NotNull String title, @NotNull String author, Book.@NotNull Gender gender, @NotNull Year year) {
        listOfBooks.add(new Book(title, author, gender, year));
        System.out.println("Book added successfully");
        listOfBooks.sort(Comparator.comparing(Book::getTitle, String.CASE_INSENSITIVE_ORDER));
    }

    @Override
    public void removeBook(@NotNull Book book) {
        listOfBooks.remove(book);
        System.out.println("Book removed successfully");
    }

    @Override
    public void removeBook(@NotNull String title) {
        int index = Collections.binarySearch(listOfBooks, new Book(title, "", Book.Gender.ROMANCE, Year.of(0)), Comparator.comparing(Book::getTitle, String.CASE_INSENSITIVE_ORDER));
        if (index >= 0){
            listOfBooks.remove(index);
            System.out.println("Book removed successfully");
        } else {
            System.out.println("Book not found");
        }
    }

    @Override
    public void listBook() {
        for (Book book : listOfBooks) {
            System.out.println(book);
        }
    }

    @Override
    public void listBorrowedBooks() {
        for (Book book : borrowedBooks) {
            System.out.println(book);
        }
    }

    @Override
    public void loanBook(@NotNull User user,@NotNull String title) throws LibraryException {
        if (!(users.contains(user))) {
            throw new LibraryException("User is not registered");
        }

        for (Book book : listOfBooks) {
            if (book.getTitle().equalsIgnoreCase(title)) {;
                if (book.getStatus() == Book.Status.LOANED) {
                    throw new LibraryException("The book is already on loan");
                }
                book.setStatus(Book.Status.LOANED);
                borrowedBooks.add(book);
                borrowedBooks.sort(Comparator.comparing(Book::getTitle, String.CASE_INSENSITIVE_ORDER));
                return;
            }
        }

        throw new LibraryException("Book not found");
    }

    @Override
    public void returnBook(@NotNull User user,@NotNull String title) throws LibraryException{
        if (!(users.contains(user))) {
            throw new LibraryException("User is not registered");
        }

        for (Book book : listOfBooks) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.setStatus(Book.Status.AVAILABLE);
                borrowedBooks.remove(book);
                return;
            }
        }
    }

    @Override
    public void registerUser(@NotNull User user, @NotNull Long id) throws UserException {
        if (user.getId() != id) {
            throw new UserException("Registration failed");
        }

        if (user.getId() == id) {
            System.out.println("User registered successfully!");
            users.add(user);
        }
    }
}