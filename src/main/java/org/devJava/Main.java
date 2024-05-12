package org.devJava;

import org.devJava.entities.book.Book;
import org.devJava.entities.library.Library;
import org.devJava.entities.library.LibrarySystem;
import org.devJava.entities.user.User;

import java.time.Year;

public class Main {
    private static long countUsers = 0;

    public static void main(String[] args) {
        LibrarySystem library = new LibrarySystem();

        User user = new User(countUsers += 1, "Carlos", 17);
        Book book = new Book("Assim que acaba ", "CoHo", Book.Gender.ROMANCE, Year.of(2019));

        library.addBook(book);
        library.addBook("Pequeno principe ", "Eu", Book.Gender.ADVENTURE, Year.of(2008));

        library.listBook();
        library.registerUser(user, 1l);
        library.loanBook(user, book.getTitle());
        library.listBook();
        library.listBorrowedBooks();
        library.returnBook(user, book.getTitle());
        library.listBook();

        library.removeBook(book);
        library.removeBook("Pequeno principe ");
        library.listBook();
        library.listBorrowedBooks();
    }
}