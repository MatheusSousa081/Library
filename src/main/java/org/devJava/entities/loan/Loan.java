package org.devJava.entities.loan;

import org.devJava.exceptions.LibraryException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;

public final class Loan {
    private final@NotNull int user_id;
    private final @NotNull int book_id;
    private final @NotNull LocalDate loanDate;
    private final @Nullable LocalDate returnDate;

    private Loan(@NotNull int user_id,@NotNull int book_id, @NotNull LocalDate loanDate, @Nullable LocalDate returnDate) {
        this.user_id = user_id;
        this.book_id = book_id;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    public static Loan create(@NotNull int user_id,@NotNull int book_id,@NotNull LocalDate loanDate,@Nullable LocalDate returnDate) throws LibraryException {
        if (loanDate.isAfter(returnDate)) {
            throw new LibraryException("Loan date must be less than return date");
        }
        return new Loan(user_id, book_id, loanDate, returnDate);
    }

    public int getUser_id() {
        return user_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }
}
