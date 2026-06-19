package com.entities;

import com.Exception.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.util.Date;
import java.util.Scanner;

@Entity
public class ReturnBooks {

    @Id
    @Column(name = "return_id", nullable = false)
    private String returnId;

    @Column(name = "issues_id", nullable = false)
    private String issuedId;

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "book_id", nullable = false)
    private String bookId;

    @Column(name = "number_of_book_returned", nullable = false)
    private int numberOfBookReturned;

    @Column(name = "status_of_book", nullable = false)
    private String statusOfBook;

    @Column(name = "date_of_book_returned", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfReturnBook;

    public ReturnBooks() {
        super();
    }

    public ReturnBooks(String returnId, String issuedId, String studentId, String bookId,
                       int numberOfBookReturned, String statusOfBook, Date dateOfReturnBook) {
        this.returnId = returnId;
        this.issuedId = issuedId;
        this.studentId = studentId;
        this.bookId = bookId;
        this.numberOfBookReturned = numberOfBookReturned;
        this.statusOfBook = statusOfBook;
        this.dateOfReturnBook = dateOfReturnBook;
    }

    public String getReturnId() {
        return returnId;
    }
    public void setReturnId(String returnId) {
        this.returnId = returnId;
    }

    public String getIssuedId() {
        return issuedId;
    }
    public void setIssuedId(String issuedId) {
        this.issuedId = issuedId;
    }

    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getBookId() {
        return bookId;
    }
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public int getNumberOfBookReturned() {
        return numberOfBookReturned;
    }
    public void setNumberOfBookReturned(int numberOfBookReturned) {
        this.numberOfBookReturned = numberOfBookReturned;
    }

    public String getStatusOfBook() {
        return statusOfBook;
    }
    public void setStatusOfBook(String statusOfBook) {
        this.statusOfBook = statusOfBook;
    }

    public Date getDateOfReturnBook() {
        return dateOfReturnBook;
    }
    public void setDateOfReturnBook(Date dateOfReturnBook) {
        this.dateOfReturnBook = dateOfReturnBook;
    }

    public static ReturnBooks returnBooksInput(SessionFactory factory) {
        Scanner input = new Scanner(System.in);
        ReturnBooks returnBooks = new ReturnBooks();

        while (true) {
            try {
                System.out.print("Enter return id: ");
                String returnId = input.nextLine();
                if (returnId.isEmpty() || !returnId.matches("R\\d{3}")) {
                    throw new InvalidReturnIdException("Invalid Return Id! Return id must be start with capital 'R' with followed by 3-digit unique number...");
                } else {
                    returnBooks.setReturnId(returnId);

                    String issuedId;
                    IssuedBooks issuedRecord;
                    while (true) {
                        try {
                            System.out.print("Enter issued id of the book being returned: ");
                            issuedId = input.nextLine();
                            if (issuedId.isEmpty() || !issuedId.matches("I\\d{3}")) {
                                throw new InvalidIssuedIdException("Invalid Issued Id! Issued id must be start with capital 'I' with followed by 3-digit unique number...");
                            }
                            Session session = factory.openSession();
                            issuedRecord = session.get(IssuedBooks.class, issuedId);
                            session.close();

                            if (issuedRecord == null) {
                                System.out.println("No issued record found with ID: " + issuedId + ". Please try again.");
                                continue;
                            }
                            if (issuedRecord.getNumberOfIssuedBook() <= 0) {
                                System.out.println("This issued record has already been fully returned.");
                                continue;
                            }
                            System.out.println("Issued Record Found | Student: " + issuedRecord.getStudentId()
                                    + " | Book: " + issuedRecord.getIssueBookId()
                                    + " | Currently Issued Qty: " + issuedRecord.getNumberOfIssuedBook());
                            break;

                        } catch (InvalidIssuedIdException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    returnBooks.setIssuedId(issuedId);
                    returnBooks.setStudentId(issuedRecord.getStudentId());
                    returnBooks.setBookId(issuedRecord.getIssueBookId());

                    System.out.println();
                    int numberOfBookReturned = 0;
                    while (true) {
                        try {
                            System.out.print("Enter how many books are being returned: ");
                            if (input.hasNextInt()) {
                                numberOfBookReturned = input.nextInt();
                                input.nextLine();
                                if (numberOfBookReturned <= 0) {
                                    throw new InvalidNumberOfBookIssuedException("Invalid Number of Book! Number of returned books must be a positive numeric value...");
                                }
                                if (numberOfBookReturned > issuedRecord.getNumberOfIssuedBook()) {
                                    System.out.println("Cannot return " + numberOfBookReturned + " book(s); only "
                                            + issuedRecord.getNumberOfIssuedBook() + " book(s) are recorded as issued under this id.");
                                    continue;
                                }
                                break;
                            } else {
                                System.out.println("Invalid Number of Returned Book! Try Again, only numeric input is allowed.");
                                input.nextLine();
                            }
                        } catch (InvalidNumberOfBookIssuedException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    returnBooks.setNumberOfBookReturned(numberOfBookReturned);

                    System.out.println();
                    while (true) {
                        try {
                            System.out.print("Enter the status of book(only returned): ");
                            String statusOfBookReturned = input.nextLine();
                            if (statusOfBookReturned.isEmpty() || !statusOfBookReturned.equals("returned")) {
                                throw new InvalidStatusOfBookException("Status of Book Returned must be input as only returned.");
                            } else {
                                returnBooks.setStatusOfBook(statusOfBookReturned);
                                break;
                            }
                        } catch (InvalidStatusOfBookException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    returnBooks.setDateOfReturnBook(new Date());

                    Session updateSession = factory.openSession();
                    Transaction tx = updateSession.beginTransaction();
                    try {
                        updateSession.createQuery(
                                        "UPDATE AddBooks " +
                                                "SET availableNumberOfCopiesOfBooks = availableNumberOfCopiesOfBooks + :returnedCount " +
                                                "WHERE bookId = :id")
                                .setParameter("returnedCount", numberOfBookReturned)
                                .setParameter("id", returnBooks.getBookId())
                                .executeUpdate();

                        int remaining = issuedRecord.getNumberOfIssuedBook() - numberOfBookReturned;
                        if (remaining > 0) {
                            updateSession.createQuery(
                                            "UPDATE IssuedBooks " +
                                                    "SET numberOfIssuedBook = :remaining " +
                                                    "WHERE issuedId = :id")
                                    .setParameter("remaining", remaining)
                                    .setParameter("id", issuedId)
                                    .executeUpdate();
                        } else {
                            updateSession.createQuery(
                                            "UPDATE IssuedBooks " +
                                                    "SET numberOfIssuedBook = 0, statusOfBook = :status " +
                                                    "WHERE issuedId = :id")
                                    .setParameter("status", "returned")
                                    .setParameter("id", issuedId)
                                    .executeUpdate();
                        }

                        tx.commit();
                        System.out.println(numberOfBookReturned + " book(s) returned successfully.");
                    } catch (Exception e) {
                        tx.rollback();
                        System.out.println("Failed to update book/issued records: " + e.getMessage());
                    } finally {
                        updateSession.close();
                    }
                    break;
                }
            } catch (InvalidReturnIdException e) {
                System.out.println(e.getMessage());
            }
        }

        return returnBooks;
    }
}