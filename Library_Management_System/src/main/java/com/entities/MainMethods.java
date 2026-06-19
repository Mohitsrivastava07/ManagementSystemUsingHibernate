package com.entities;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.sql.Update;

import java.util.Date;
import java.util.Scanner;

public class MainMethods {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("\nProgram Started...");
        System.out.println("=====LIBRARY MANAGEMENT SYSTEM=====");

        Configuration cfg = new Configuration();
        cfg.configure("library.cfg.xml");

        SessionFactory factory = cfg.buildSessionFactory();

        while (true) {
            System.out.println("\n=====LIBRARY MANAGEMENT SYSTEM=====");
            System.out.println("1. Add Student");
            System.out.println("2. Add Book");
            System.out.println("3. Issued Book");
            System.out.println("4. Return Book");
            System.out.println("5. Update Student");
            System.out.println("6. Update Book");
            System.out.println("0. Exits");

            System.out.print("Choose any option: ");
            if (input.hasNextInt()) {
                int choice = input.nextInt();
                input.nextLine();

                if (choice == 1) {
                    //Creating the object of AddStudents Class and set tha values
                    AddStudents student = null;
                    student = AddStudents.addStudentsInput();
                    System.out.println("Student Added Successfully!!\n");

                    Session session = factory.openSession();
                    Transaction tx = session.beginTransaction();
                    session.save(student);

                    tx.commit();
                    session.close();

                } else if (choice == 0) {
                    System.out.println();
                    System.out.println("Thank you! for using Library Management System...");
                    System.out.println("Existing Program....");
                    System.out.println();
                    break;

                } else if (choice == 2) {
                    //Creating the object of AddBooks Class and set tha values
                    AddBooks addBooks = AddBooks.addBooksInput();
                    System.out.println("Book Added Successfully!!\n");

                    Session session = factory.openSession();
                    Transaction tx = session.beginTransaction();
                    session.save(addBooks);

                    tx.commit();
                    session.close();

                } else if (choice == 5) {
                    UpdateStudents.updateStudentsInput(factory);

                } else if (choice == 6) {
                    UpdateBooks.updateBooksInput(factory);

                } else if (choice == 3) {
                    IssuedBooks issuedBooks = IssuedBooks.issuedBooksInput(factory);
                    Session session = factory.openSession();

                    Transaction tx = null;
                    try {
                        tx = session.beginTransaction();

                        session.save(issuedBooks);

                        tx.commit();
                        System.out.println("Issued Book Successfully!!\n");
                        session.close();
                    } catch (Exception e) {
                        if (tx != null) {
                            tx.rollback();
                        }
                        System.out.println("Failed to issue book: " + e.getMessage());
                    } finally {
                        session.close();
                    }
                } else if (choice == 4) {
                    ReturnBooks returnBooks = ReturnBooks.returnBooksInput(factory);
                    Session session = factory.openSession();

                    Transaction tx = null;
                    try {
                        tx = session.beginTransaction();

                        session.save(returnBooks);

                        tx.commit();
                        System.out.println("Book Returned Successfully!!\n");
                    } catch (Exception e) {
                        if (tx != null) {
                            tx.rollback();
                        }
                        System.out.println("Failed to return book: " + e.getMessage());
                    } finally {
                        session.close();
                    }
                }
            } else {
                System.out.println("Invalid Choice of number! Try Again, Only for input by user as numeric...");
                input.nextLine();
            }
        }

        factory.close();
        input.close();
    }
}
