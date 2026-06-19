package com.entities;

import com.Exception.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Scanner;

public class UpdateBooks {
    public static void updateBooksInput(SessionFactory factory) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("1. Book Title (with new Edition)");
            System.out.println("2. Author Name");
            System.out.println("3. Publisher Name");
            System.out.println("4. Publisher Year");
            System.out.println("5. Book Price");
            System.out.println("6. Total Number of Copies Of Books");
            System.out.println("7. Available Number of Copies of Books");
            System.out.println("0. Back to Main Menu");

            System.out.print("Choice any option: ");
            if (input.hasNextInt()) {
                int choice = input.nextInt();
                input.nextLine();

                if (choice == 0) {
                    System.out.println("Returning to Main Menu...");
                    break;
                }

                if (choice == 1) {
                    String id;
                    System.out.println("\n====Update Book Title====");

                    while (true) {
                        try {
                            System.out.print("Enter book id: ");
                            id = input.nextLine().trim();
                            if (!id.matches("B\\d{3}") || id.isEmpty()) {
                                throw new InvalidStudentIdException("Invalid Book Id! Book Id must start with capital 'B' with followed by 3-digits unique numbers...");
                            }
                            Session session = factory.openSession();
                            AddBooks existingId = session.get(AddBooks.class, id);
                            session.close();

                            if (existingId == null) {
                                System.out.println("Book Found: " + existingId.getBookTitle() + " | ID: " + id);
                                continue;
                            }
                            System.out.println("Book Found: " + existingId.getBookTitle() + " | ID: " + id);
                            break;

                        } catch (InvalidStudentIdException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    Session session = factory.openSession();
                    Transaction tx = session.beginTransaction();

                    while (true) {
                        try {
                            System.out.print("Enter update book title: ");
                            String updateBookTitle = input.nextLine();
                            if (updateBookTitle.isEmpty() || !updateBookTitle.matches("[a-zA-Z0-9 ]+")) {
                                throw new InvalidBookTitleException("Invalid Book Title Name! Book Title does not contains any special characters...");
                            } else {
                                session.createQuery("update AddBooks set bookTitle=:title where bookId=:id")
                                        .setParameter("title", updateBookTitle)
                                        .setParameter("id", id)
                                        .executeUpdate();
                                tx.commit();
                                System.out.println("Book Title Updated Successfully!");
                                break;
                            }
                        } catch (InvalidBookTitleException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                } else if (choice == 2) {
                    String id;
                    System.out.println("\n====Update Book Author Name====");

                    while (true) {
                        try {
                            System.out.print("Enter book id: ");
                            id = input.nextLine().trim();
                            if (!id.matches("B\\d{3}") || id.isEmpty()) {
                                throw new InvalidStudentIdException("Invalid Book Id! Book Id must start with capital 'B' with followed by 3-digits unique numbers...");
                            }
                            Session session = factory.openSession();
                            AddBooks existingId = session.get(AddBooks.class, id);
                            session.close();

                            if (existingId == null) {
                            System.out.println("Student Found: " + existingId.getAuthorName() + " | ID: " + id);
                                continue;
                            }
                            System.out.println("Student Found: " + existingId.getAuthorName() + " | ID: " + id);
                            break;

                        } catch (InvalidStudentIdException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    Session session = factory.openSession();
                    Transaction tx = session.beginTransaction();

                    while (true) {
                        try {
                            System.out.print("Enter update book author name: ");
                            String updateAuthorName = input.nextLine();
                            if (updateAuthorName.isEmpty() || !updateAuthorName.matches("[a-zA-Z ]+")) {
                                throw new InvalidAuthorNameException("Invalid Update Author Name! Author name does not contain any number and special charcater...");
                            } else {
                                session.createQuery("update AddBooks set authorName=:authorName where bookId=:id")
                                        .setParameter("authorName", updateAuthorName)
                                        .setParameter("id", id)
                                        .executeUpdate();
                                tx.commit();
                                System.out.println("Book Author Name Updated Successfully!");
                                break;
                            }
                        } catch (InvalidAuthorNameException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } else if (choice == 3) {
                    String id;
                    System.out.println("\n====Update Book Publisher Name====");

                    while (true) {
                        try {
                            System.out.print("Enter book id: ");
                            id = input.nextLine().trim();
                            if (!id.matches("B\\d{3}") || id.isEmpty()) {
                                throw new InvalidStudentIdException("Invalid Book Id! Book Id must start with capital 'B' with followed by 3-digits unique numbers...");
                            }
                            Session session = factory.openSession();
                            AddBooks existingId = session.get(AddBooks.class, id);
                            session.close();

                            if (existingId == null) {
                                System.out.println("Student Found: " + existingId.getAuthorName() + " | ID: " + id);
                                continue;
                            }
                            System.out.println("Student Found: " + existingId.getAuthorName() + " | ID: " + id);
                            break;

                        } catch (InvalidStudentIdException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    Session session = factory.openSession();
                    Transaction tx = session.beginTransaction();

                    while (true) {
                        try {
                            System.out.print("Enter update book publisher name: ");
                            String updatePublisherName = input.nextLine();
                            if (updatePublisherName.isEmpty() || !updatePublisherName.matches("[a-zA-Z ]+")) {
                                throw new InvalidPublisherNameException("Invalid Update Author Name! Author name does not contain any number and special charcater...");
                            } else {
                                session.createQuery("update AddBooks set publisherName=:publisher_name where bookId=:id")
                                        .setParameter("publisher_name", updatePublisherName)
                                        .setParameter("id", id)
                                        .executeUpdate();
                                tx.commit();
                                System.out.println("Book Publisher Name Updated Successfully!");
                                break;
                            }
                        } catch (InvalidPublisherNameException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } else if (choice == 4) {
                    String id;
                    System.out.println("\n====Update Book Publisher Year====");

                    while (true) {
                        try {
                            System.out.print("Enter book id: ");
                            id = input.nextLine().trim();
                            if (!id.matches("B\\d{3}") || id.isEmpty()) {
                                throw new InvalidStudentIdException("Invalid Book Id! Book Id must start with capital 'B' with followed by 3-digits unique numbers...");
                            }
                            Session session = factory.openSession();
                            AddBooks existingId = session.get(AddBooks.class, id);
                            session.close();

                            if (existingId == null) {
                                System.out.println("Student Found: " + existingId.getAuthorName() + " | ID: " + id);
                                continue;
                            }
                            System.out.println("Student Found: " + existingId.getAuthorName() + " | ID: " + id);
                            break;

                        } catch (InvalidStudentIdException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    Session session = factory.openSession();
                    Transaction tx = session.beginTransaction();

                    while (true) {
                        try {
                            System.out.print("Enter update publish year: ");
                            if (input.hasNextInt()) {
                                int updatePublishYear = input.nextInt();
                                input.nextLine();
                                if (String.valueOf(updatePublishYear).length() != 4) {
                                    throw new InvalidPublishYearException("Invalid Update Publish Year! Length of publish year should be 4...");
                                } else {
                                    session.createQuery("update AddBooks set publishYear=:year where bookId=:id")
                                            .setParameter("year", updatePublishYear)
                                            .setParameter("id", id)
                                            .executeUpdate();
                                }
                            } else {
                                System.out.println("Invalid Input! Try Again...");
                                input.nextLine();
                            }
                            tx.commit();
                            System.out.println("Book Publish Year Updated Successfully!");
                            break;

                        } catch (InvalidPublishYearException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } else if (choice == 5) {
                    String id;
                    System.out.println("\n====Update Book Price====");

                    while (true) {
                        try {
                            System.out.print("Enter book id: ");
                            id = input.nextLine().trim();
                            if (!id.matches("B\\d{3}") || id.isEmpty()) {
                                throw new InvalidStudentIdException("Invalid Book Id! Book Id must start with capital 'B' with followed by 3-digits unique numbers...");
                            }
                            Session session = factory.openSession();
                            AddBooks existingId = session.get(AddBooks.class, id);
                            session.close();

                            if (existingId == null) {
                                System.out.println("Student Found: " + existingId.getAuthorName() + " | ID: " + id);
                                continue;
                            }
                            System.out.println("Student Found: " + existingId.getAuthorName() + " | ID: " + id);
                            break;

                        } catch (InvalidStudentIdException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    Session session = factory.openSession();
                    Transaction tx = session.beginTransaction();

                    while (true) {
                        try {
                            System.out.print("Enter update book price: ");
                            if (input.hasNextDouble()) {
                                double updateBookPrice = input.nextDouble();
                                input.nextLine();
                                if (updateBookPrice <= 0.0) {
                                    throw new InvalidBookPriceException("Invalid Update Publish Price! Book Price does not in negative price of book...");
                                } else {
                                    session.createQuery("update AddBooks set bookPrice=:price where bookId=:id")
                                            .setParameter("price", updateBookPrice)
                                            .setParameter("id", id)
                                            .executeUpdate();
                                }
                            } else {
                                System.out.println("Invalid Input! Try Again...");
                                input.nextLine();
                            }
                            tx.commit();
                            System.out.println("Book Price Updated Successfully!");
                            break;

                        } catch (InvalidBookPriceException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } else if (choice == 6) {
                    String id;
                    System.out.println("\n====Update Total Number of Copies of Book====");

                    while (true) {
                        try {
                            System.out.print("Enter book id: ");
                            id = input.nextLine().trim();
                            if (!id.matches("B\\d{3}") || id.isEmpty()) {
                                throw new InvalidStudentIdException("Invalid Book Id! Book Id must start with capital 'B' with followed by 3-digits unique numbers...");
                            }
                            Session session = factory.openSession();
                            AddBooks existingId = session.get(AddBooks.class, id);
                            session.close();

                            if (existingId == null) {
                                System.out.println("Student Found: " + existingId.getAuthorName() + " | ID: " + id);
                                continue;
                            }
                            System.out.println("Student Found: " + existingId.getAuthorName() + " | ID: " + id);
                            break;

                        } catch (InvalidStudentIdException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    Session session = factory.openSession();
                    Transaction tx = session.beginTransaction();

                    while (true) {
                        try {
                            System.out.print("Enter update total number of copies of book: ");
                            if (input.hasNextInt()) {
                                int updateTotalNumberOfCopiesofBook = input.nextInt();
                                input.nextLine();
                                if (updateTotalNumberOfCopiesofBook <= 0) {
                                    throw new InvalidNegativeNumberOfCopiesException("Invalid Update Total Number of Copies of Book! Total Number of Copies of Book will not be negative...");
                                } else {
                                    session.createQuery("update AddBooks set totalNumberOfCopiesOfBooks=:copiesOfBook where bookId=:id")
                                            .setParameter("copiesOfBook", updateTotalNumberOfCopiesofBook)
                                            .setParameter("id", id)
                                            .executeUpdate();
                                }
                            } else {
                                System.out.println("Invalid Input! Try Again...");
                                input.nextLine();
                            }
                            tx.commit();
                            System.out.println("Total Number of Copies of Book Updated Successfully!");
                            break;

                        } catch (InvalidNegativeNumberOfCopiesException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } else if (choice == 7) {
                    String id;
                    System.out.println("\n====Update Available number of copies of book====");

                    while (true) {
                        try {
                            System.out.print("Enter book id: ");
                            id = input.nextLine().trim();
                            if (!id.matches("B\\d{3}") || id.isEmpty()) {
                                throw new InvalidStudentIdException("Invalid Book Id! Book Id must start with capital 'B' with followed by 3-digits unique numbers...");
                            }
                            Session session = factory.openSession();
                            AddBooks existingId = session.get(AddBooks.class, id);
                            session.close();

                            if (existingId == null) {
                                System.out.println("Student Found: " + existingId.getAuthorName() + " | ID: " + id);
                                continue;
                            }
                            System.out.println("Student Found: " + existingId.getAuthorName() + " | ID: " + id);
                            break;

                        } catch (InvalidStudentIdException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    Session session = factory.openSession();
                    Transaction tx = session.beginTransaction();

                    while (true) {
                        try {
                            System.out.print("Enter update available number of copies of book: ");
                            if (input.hasNextInt()) {
                                int updateAvailableNumberOfCopiesOfBook = input.nextInt();
                                input.nextLine();
                                if (updateAvailableNumberOfCopiesOfBook <= 0) {
                                    throw new InvalidNegativeNumberOfCopiesException("Invalid Update Available Number oOf Copies Of Book! Negative number of available number of copies of book...");
                                } else {
                                    session.createQuery("update AddBooks set availableNumberOfCopiesOfBooks=:copiesOfBooks where bookId=:id")
                                            .setParameter("copiesOfBooks", updateAvailableNumberOfCopiesOfBook)
                                            .setParameter("id", id)
                                            .executeUpdate();
                                }
                            } else {
                                System.out.println("Invalid Input! Try Again...");
                                input.nextLine();
                            }
                            tx.commit();
                            System.out.println("Available Number Of Copies Of Book Updated Successfully!");
                            break;

                        } catch (InvalidNegativeNumberOfCopiesException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } else {
                    System.out.println("Invalid Choice! Try Again...");
                    input.nextLine();
                    break;
                }
            }
        }
    }
}