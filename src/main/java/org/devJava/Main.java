package org.devJava;

import org.devJava.entity.book.Book;
import org.devJava.entity.library.LibrarySystem;
import org.devJava.entity.user.Client;
import org.devJava.exception.LibraryException;
import org.devJava.repository.BookRepository;
import org.devJava.repository.ClientRepository;
import org.devJava.repository.Connection.Database;
import org.devJava.repository.LoanRepository;
import org.devJava.repository.StockRepository;

import java.sql.SQLException;
import java.time.Year;

public class Main {
    public static void main(String[] args) throws SQLException, LibraryException {
        Database connection = new Database("jdbc:mysql://localhost:3306/library", "matheus", "0511");
        connection.createConnection();
        BookRepository bookRepository = new BookRepository(connection);
        ClientRepository clientRepository = new ClientRepository(connection);
        StockRepository stockRepository = new StockRepository(connection);
        LoanRepository loanRepository = new LoanRepository(connection);
        LibrarySystem library = new LibrarySystem(bookRepository, clientRepository, stockRepository, loanRepository);

        Client client1 = new Client(1, "Miguel", "miguel@email");
        Client client2 = new Client(2, "Lucas", "lucas@email");
        Client client3 = new Client(3, "A", "A@email");
        library.registerClient(client1, client1.getName(), client1.getEmail());
        library.registerClient(client2, client2.getName(), client2.getEmail());
        library.registerClient(client3, client3.getName(), client3.getEmail());

        Book book1 = Book.create(1, "Assim que acaba", "CoHo", Year.of(2019), Book.Gender.ROMANCE);
        Book book2 = Book.create(2, "Java efetivo", "A", Year.of(2018), Book.Gender.ADVENTURE);
        Book book3 = Book.create(4, "The strongest", "Gojo", Year.of(2024), Book.Gender.ACTION);
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(3, "Assim que começa", "CoHo", Year.of(2020), Book.Gender.ROMANCE);
        library.addBook(book3);
        library.removeBook(book2.getId());

        library.loanBook(book1, client1);
        library.loanBook(book3, client3);

        System.out.println("-----");
        library.getBook(book1.getId());
        System.out.println("--------");
        System.out.println("Lista de livros: ");
        library.getAllBooks();

        System.out.println("------");
        System.out.println("Lista de livros emprestadaos: ");
        System.out.println(library.getAllBorrowedBooks());

        System.out.println("-----------\n Clients: ");
        System.out.println(library.getClients());

        library.returnBook(book3, client3);
        
        System.out.println("--------");
        System.out.println("Eprestimos");
        System.out.println(library.getLoans());
    }
}