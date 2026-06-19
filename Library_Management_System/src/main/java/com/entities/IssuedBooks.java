package com.entities;

import com.Exception.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.util.Date;
import java.util.Scanner;

@Entity
public class IssuedBooks {

    @Id
    @Column(name = "issues_id", nullable = false)
    private String issuedId;

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "book_id", nullable = false)
    private String issueBookId;

    @Column(name = "number_of_issued_book", nullable = false)
    private int numberOfIssuedBook;

    @Column(name = "status_of_book", nullable = false)
    private String statusOfBook;

    @Column(name = "date_of_book_issued", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfIssueBook;

    public IssuedBooks() {
        super();
    }
    public IssuedBooks(String issuedId, String issueBookId, String studentId, String statusOfBook, int numberOfIssuedBook , Date dateOfIssueBook) {
        this.issuedId = issuedId;
        this.issueBookId = issueBookId;
        this.studentId = studentId;
        this.statusOfBook = statusOfBook;
        this.numberOfIssuedBook = numberOfIssuedBook;
        this.dateOfIssueBook = dateOfIssueBook;
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

    public String getIssueBookId() {
        return issueBookId;
    }
    public void setIssueBookId(String issueBookId) {
        this.issueBookId = issueBookId;
    }

    public int getNumberOfIssuedBook() {
        return numberOfIssuedBook;
    }
    public void setNumberOfIssuedBook(int numberOfIssuedBook) {
        this.numberOfIssuedBook = numberOfIssuedBook;
    }

    public String getStatusOfBook() {
        return statusOfBook;
    }
    public void setStatusOfBook(String statusOfBook) {
        this.statusOfBook = statusOfBook;
    }

    public Date getDateOfIssueBook() {
        return dateOfIssueBook;
    }
    public void setDateOfIssueBook(Date dateOfIssueBook) {
        this.dateOfIssueBook = dateOfIssueBook;
    }

    public static IssuedBooks issuedBooksInput(SessionFactory factory) {
        Scanner input = new Scanner(System.in);
        IssuedBooks issuedBooks = new IssuedBooks();

        while (true) {
            try {
                System.out.print("Enter book issued id: ");
                String issuedId = input.nextLine();
                if (issuedId.isEmpty() || !issuedId.matches("I\\d{3}")) {
                    throw new InvalidIssuedIdException("Invalid Issued Id! Issued id must be start with capital 'I' with followed by 3-digit unique number...");
                } else {
                    issuedBooks.setIssuedId(issuedId);

                    String studentId;
                    while (true) {
                        try {
                            System.out.print("Enter student id: ");
                            studentId = input.nextLine();
                            if (studentId.isEmpty() || !studentId.matches("K\\d{5}")) {
                                throw new InvalidStudentIdException("Invalid Student Id! Student Id must be start with capital 'K' with followed by 5-digits unique number...");
                            }
                            Session session = factory.openSession();
                            AddStudents existingId = session.get(AddStudents.class, studentId);
                            session.close();

                            if (existingId == null) {
                                System.out.println("Student not found with ID: " + studentId + ". Please try again.");
                                continue;
                            }
                            System.out.println("Student Found: " + existingId.getStudentName() + " | ID: " + studentId);
                            break;

                        } catch (InvalidStudentIdException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    issuedBooks.setStudentId(studentId);
                    System.out.println();
                    while (true) {
                        try {
                            System.out.print("Enter book id to issued book now: ");
                            String bookId = input.nextLine();
                            if (bookId.isEmpty() || !bookId.matches("B\\d{3}")) {
                                throw new InvalidBookIdException("Invalid Book Id! Book Id must be start with capital 'B' with followed by 3-digits unique number...");
                            }
                            Session session = factory.openSession();
                            AddBooks existingBookId = session.get(AddBooks.class, bookId);
                            session.close();

                            if (existingBookId == null) {
                                System.out.println("Book not found with ID: " + bookId + ". Please try again.");
                                continue;
                            }
                            System.out.println("Book Found: " + existingBookId.getBookTitle() + " | ID: " + bookId);
                            System.out.println();
                            while (true) {
                                try {
                                    System.out.print("Enter how many number of book issued: ");
                                    if (input.hasNextInt()) {
                                        int numberOfBookIssued = input.nextInt();
                                        input.nextLine();
                                        if (numberOfBookIssued <= 0) {
                                            throw new InvalidNumberOfBookIssuedException("Invalid Number of Book! Number of Book will be in numeric not characters...");
                                        } else {
                                            if (numberOfBookIssued > existingBookId.getAvailableNumberOfCopiesOfBooks()) {
                                                System.out.println("Not Issued Book because Only " + existingBookId.getAvailableNumberOfCopiesOfBooks() + " total number of books are available.");
                                                continue;
                                            }
                                            Session session1 = factory.openSession();
                                            Transaction tx = session1.beginTransaction();
                                            try {
                                                int rowsUpdated = session1.createQuery(
                                                                "UPDATE AddBooks " +
                                                                        "SET availableNumberOfCopiesOfBooks = availableNumberOfCopiesOfBooks - :issuedCount " +
                                                                        "WHERE bookId = :id AND availableNumberOfCopiesOfBooks >= :issuedCount")
                                                        .setParameter("issuedCount", numberOfBookIssued)
                                                        .setParameter("id", bookId)
                                                        .executeUpdate();

                                                tx.commit();
                                                if (rowsUpdated > 0) {
                                                    System.out.println(numberOfBookIssued + " book(s) issued successfully.");
                                                    issuedBooks.setIssueBookId(bookId);
                                                    issuedBooks.setNumberOfIssuedBook(numberOfBookIssued);
                                                    break;
                                                } else {
                                                    System.out.println("Issue failed: not enough copies available.");
                                                }
                                            } catch (Exception e) {
                                                tx.rollback();
                                                System.out.println(e.getMessage());
                                            } finally {
                                                session1.close();
                                            }
                                        }
                                    } else {
                                        System.out.println("Invalid Number of Issued Book! Try Again Only number of issued book is input as numeric..");
                                        input.nextLine();
                                    }
                                } catch (InvalidNumberOfBookIssuedException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                            break;

                        } catch (InvalidBookIdException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    System.out.println();
                    while (true) {
                        try {
                            System.out.print("Enter the status of book(only issued): ");
                            String statusOfBookIssued = input.nextLine();
                            if (statusOfBookIssued.isEmpty() || !statusOfBookIssued.equals("issued")) {
                                throw new InvalidStatusOfBookException("Status of Book Issued must be input as only issued.");
                            } else {
                                issuedBooks.setStatusOfBook(statusOfBookIssued);
                                break;
                            }
                        } catch (InvalidStatusOfBookException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    issuedBooks.setDateOfIssueBook(new Date());
                    break;
                }
            } catch (InvalidIssuedIdException e) {
                System.out.println(e.getMessage());
            }
        }

        return issuedBooks;
    }
}