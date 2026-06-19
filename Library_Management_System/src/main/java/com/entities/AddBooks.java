package com.entities;

import com.Exception.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Scanner;

@Entity
public class AddBooks {
    public enum Category {
        Biography, Real_Story, Programming, Coding, Science, Math, Poem, Story, Comedy_Book,
        Motivation_Book, Social_Science
    }

    @Id
    @Column(name="Book_id", nullable = false)
    private String bookId;

    @Column(name="Book_title", length=100, nullable = false)
    private String bookTitle;

    @Column(name="Author_name", length=120, nullable = false)
    private String authorName;

    @Column(name="ISBM_number", unique = true, length=13, nullable = false)
    private String isbmNumber;

    @Column(name="Category_book", nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name="Publisher_name", length=120, nullable = false)
    private String publisherName;

    @Column(name="Publish_year", length=4, nullable = false)
    private int publishYear;

    @Column(name="Book_price", nullable = false)
    private double bookPrice;

    @Column(name="TotalCopies_Book", nullable = false)
    private int totalNumberOfCopiesOfBooks;

    @Column(name="AvailableCopies_Book", nullable = false)
    private int availableNumberOfCopiesOfBooks;

    @Column(name="Book_inserted_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfAdded;

    public AddBooks() {
        super();
    }
    public AddBooks(String bookId, String bookTitle, String authorName, String isbmNumber, Category category, String publisherName, int publishYear, double bookPrice, int totalNumberOfCopiesOfBooks, int availableNumberOfCopiesOfBooks, Date dateOfAdded) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.authorName = authorName;
        this.isbmNumber = isbmNumber;
        this.category = category;
        this.publisherName = publisherName;
        this.publishYear = publishYear;
        this.bookPrice = bookPrice;
        this.totalNumberOfCopiesOfBooks = totalNumberOfCopiesOfBooks;
        this.availableNumberOfCopiesOfBooks = availableNumberOfCopiesOfBooks;
        this.dateOfAdded = dateOfAdded;
    }

    public String getBookId() {
        return bookId;
    }
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getIsbmNumber() {
        return isbmNumber;
    }
    public void setIsbmNumber(String isbmNumber) {
        this.isbmNumber = isbmNumber;
    }

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    public String getPublisherName() {
        return publisherName;
    }
    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public int getPublishYear() {
        return publishYear;
    }
    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public double getBookPrice() {
        return bookPrice;
    }
    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public int getTotalNumberOfCopiesOfBooks() {
        return totalNumberOfCopiesOfBooks;
    }
    public void setTotalNumberOfCopiesOfBooks(int totalNumberOfCopiesOfBooks) {
        this.totalNumberOfCopiesOfBooks = totalNumberOfCopiesOfBooks;
    }

    public int getAvailableNumberOfCopiesOfBooks() {
        return availableNumberOfCopiesOfBooks;
    }
    public void setAvailableNumberOfCopiesOfBooks(int availableNumberOfCopiesOfBooks) {
        this.availableNumberOfCopiesOfBooks = availableNumberOfCopiesOfBooks;
    }

    public Date getDateOfAdded() {
        return dateOfAdded;
    }
    public void setDateOfAdded(Date dateOfAdded) {
        this.dateOfAdded = dateOfAdded;
    }

    public static AddBooks addBooksInput() {
        Scanner input = new Scanner(System.in);
        AddBooks addBooks = new AddBooks();

        while (true) {
            try {
                System.out.print("Enter book id: ");
                String id = input.nextLine();
                if (id.isEmpty() || !id.matches("B\\d{3}")) {
                    throw new InvalidBookIdException("Invalid Book Id! Book Id must start with capital 'B' with followed by 3-digits unique number...");
                } else {
                    addBooks.setBookId(id);
                    while (true) {
                        try {
                            System.out.print("Enter book title: ");
                            String title = input.nextLine();
                            if (title.isEmpty() || !title.matches("[a-zA-Z ]+")) {
                                throw new InvalidBookTitleException("Invalid Book Title! Book Title does not contain any number or any special character...");
                            } else {
                                addBooks.setBookTitle(title);
                                while (true) {
                                    try {
                                        System.out.print("Enter book author name: ");
                                        String author = input.nextLine();
                                        if (author.isEmpty() || !author.matches("[a-zA-Z, ]+")) {
                                            throw new InvalidAuthorNameException("Invalid Book Author Name! Book Author Name does not contain any number or any special character...");
                                        } else {
                                            addBooks.setAuthorName(author);
                                            while (true) {
                                                try {
                                                    System.out.print("Enter book isbm number(13-digits number): ");
                                                    String isbmNum = input.nextLine();
                                                    if (isbmNum.length() != 13 || isbmNum.isEmpty()) {
                                                        throw new InvalidISBMNumberException("Invalid ISBM Number! ISBM Number must be length of 13-digits unique number...");
                                                    } else {
                                                        addBooks.setIsbmNumber(isbmNum);
                                                        while (true) {
                                                            try {
                                                                System.out.println("Book Category: ");
                                                                for (Category c : Category.values()) {
                                                                    System.out.println("  " + c.ordinal() + " -> " + c);
                                                                }
                                                                System.out.print("Enter book category: ");
                                                                if (input.hasNextInt()) {
                                                                    int categoryChoice = input.nextInt();
                                                                    input.nextLine();
                                                                    if (categoryChoice < 0 || categoryChoice >= Category.values().length) {
                                                                        throw new InvalidCategoryChoiceException("Invalid Category Choice! Category choice must be taken from 0 to length of category book...");
                                                                    } else {
                                                                        addBooks.setCategory(Category.values()[categoryChoice]);
                                                                        while (true) {
                                                                            try {
                                                                                System.out.print("Enter book publisher name: ");
                                                                                String publisher = input.nextLine();
                                                                                if (publisher.isEmpty() || !publisher.matches("[a-zA-Z ]+")) {
                                                                                    throw new InvalidPublisherNameException("Invalid Book Publisher Name! Book Publisher Name does not contain any number or any special character...");
                                                                                } else {
                                                                                    addBooks.setPublisherName(publisher);
                                                                                    while (true) {
                                                                                        try {
                                                                                            System.out.print("Enter book publish year: ");
                                                                                            if (input.hasNextInt()) {
                                                                                                int year = input.nextInt();
                                                                                                input.nextLine();
                                                                                                if (String.valueOf(year).length() != 4) {
                                                                                                    throw new InvalidPublishYearException("Invalid Publish Year! Publish Year must be length of 4 (such as 2022, 2024, 1957) and also does not contains alphabets...");
                                                                                                } else {
                                                                                                    addBooks.setPublishYear(year);
                                                                                                    while (true) {
                                                                                                        try {
                                                                                                            System.out.print("Enter book price: ");
                                                                                                            if (input.hasNextDouble()) {
                                                                                                                double price = input.nextDouble();
                                                                                                                input.nextLine();
                                                                                                                if (price <= 0) {
                                                                                                                    throw new InvalidBookPriceException("Invalid Book Price! Book price have not zero and negative price...");
                                                                                                                } else {
                                                                                                                    addBooks.setBookPrice(price);
                                                                                                                    while (true) {
                                                                                                                        try {
                                                                                                                            System.out.print("Enter total number of copies of " + title + " book: ");
                                                                                                                            if (input.hasNextInt()) {
                                                                                                                                int totalCopies = input.nextInt();
                                                                                                                                input.nextLine();
                                                                                                                                if (totalCopies <= 0) {
                                                                                                                                    throw new InvalidNegativeNumberOfCopiesException("Invalid Total Number of Copies of Book! Negative not be happended...");
                                                                                                                                } else {
                                                                                                                                    addBooks.setTotalNumberOfCopiesOfBooks(totalCopies);
                                                                                                                                    while (true) {
                                                                                                                                        try {
                                                                                                                                            System.out.print("Enter available number of copies of " + title + " book: ");
                                                                                                                                            if (input.hasNextInt()) {
                                                                                                                                                int availCopies = input.nextInt();
                                                                                                                                                addBooks.setAvailableNumberOfCopiesOfBooks(availCopies);
                                                                                                                                                input.nextLine();
                                                                                                                                                if (availCopies <= 0) {
                                                                                                                                                    throw new InvalidNegativeNumberOfCopiesException("Invalid Available Number of Copies of Book! Negative not be happended...");
                                                                                                                                                } else {
                                                                                                                                                    addBooks.setDateOfAdded(new Date());
                                                                                                                                                    break;
                                                                                                                                                }
                                                                                                                                            } else {
                                                                                                                                                System.out.println("Invalid Available Copies Number! Available Copies Number must be Number not alphabetic form...");
                                                                                                                                                input.nextLine();
                                                                                                                                            }
                                                                                                                                        } catch (
                                                                                                                                                InvalidNegativeNumberOfCopiesException e) {
                                                                                                                                            System.out.println(e.getMessage());
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                    break;
                                                                                                                                }
                                                                                                                            } else {
                                                                                                                                System.out.println("Invalid Total Copies Number! Total Copies Number must be Number not alphabetic form...");
                                                                                                                                input.nextLine();
                                                                                                                            }
                                                                                                                        } catch (InvalidNegativeNumberOfCopiesException e) {
                                                                                                                            System.out.println(e.getMessage());
                                                                                                                        }
                                                                                                                    }
                                                                                                                    break;
                                                                                                                }
                                                                                                            } else {
                                                                                                                System.out.println("Invalid Price! Price must be Number not alphabetic form...");
                                                                                                                input.nextLine();
                                                                                                            }
                                                                                                        } catch (InvalidBookPriceException e) {
                                                                                                            System.out.println(e.getMessage());
                                                                                                        }
                                                                                                    }
                                                                                                    break;
                                                                                                }
                                                                                            } else {
                                                                                                System.out.println("Invalid Publish Year! Publish Year must be Number not alphabetic form...");
                                                                                                input.nextLine();
                                                                                            }
                                                                                        } catch (InvalidPublishYearException e) {
                                                                                            System.out.println(e.getMessage());
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                                }
                                                                            } catch (InvalidPublisherNameException e) {
                                                                                System.out.println(e.getMessage());
                                                                            }
                                                                        }
                                                                        break;
                                                                    }
                                                                }
                                                            } catch (InvalidCategoryChoiceException e) {
                                                                System.out.println(e.getMessage());
                                                            }
                                                        }
                                                        break;
                                                    }
                                                } catch (InvalidISBMNumberException e) {
                                                    System.out.println(e.getMessage());
                                                }
                                            }
                                            break;
                                        }
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                                break;
                            }
                        } catch (InvalidBookTitleException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                }
            } catch (InvalidBookIdException e) {
                System.out.println(e.getMessage());
            }
        }

        return addBooks;
    }
}
