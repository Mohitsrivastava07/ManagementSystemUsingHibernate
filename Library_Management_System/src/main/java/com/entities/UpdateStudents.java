package com.entities;

import com.Exception.InvalidEmailIdException;
import com.Exception.InvalidMobileNumberException;
import com.Exception.InvalidStudentIdException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Entity;
import java.util.Scanner;

public class UpdateStudents {
    public static void updateStudentsInput(SessionFactory factory) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\n=====Update Students=====");
            System.out.println("1. Student Course");
            System.out.println("2. Student Current Semester");
            System.out.println("3. Mobile Number");
            System.out.println("4. Student Email Id");
            System.out.println("5. Student Address");
            System.out.println("0. Back to Main Menu");

            System.out.print("\nChoice any option to update: ");
            if (input.hasNextInt()) {
                int choice = input.nextInt();
                input.nextLine();

                if (choice == 0) {
                    System.out.println("Returning to Main Menu...");
                    break;
                }

                if (choice == 1) {
                    String id;
                    System.out.println("\n====Update Student Course====");

                    while (true) {
                        try {
                            System.out.print("Enter student id: ");
                            id = input.nextLine().trim();
                            if (!id.matches("K\\d{5}") || id.isEmpty()) {
                                throw new InvalidStudentIdException("Invalid Student Id! Student Id must start with capital 'K' with followed by 5-digits uniqu numbers...");
                            }
                            Session session = factory.openSession();
                            AddStudents existingId = session.get(AddStudents.class, id);
                            session.close();

                            if (existingId == null) {
                                System.out.println("Student Found: " + existingId.getStudentName() + " | ID: " + id);
                                continue;
                            }
                            System.out.println("Student Found: " + existingId.getStudentName() + " | ID: " + id);
                            break;

                        } catch (InvalidStudentIdException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    Session session = factory.openSession();
                    Transaction tx = session.beginTransaction();

                    System.out.println("Student Course: ");
                    for (AddStudents.Course c : AddStudents.Course.values()) {
                        System.out.println("  " + c.ordinal() + " -> " + c);
                    }
                    System.out.print("Enter update your course: ");
                    if (input.hasNextInt()) {
                        int courseChoice = input.nextInt();
                        input.nextLine();
                        if (courseChoice < 0 || courseChoice > AddStudents.Course.values().length) {
                            System.out.println("Invalid Course Choice!");
                            break;
                        } else {
                            AddStudents.Course updateCourse = AddStudents.Course.values()[courseChoice];
                            session.createQuery("update AddStudents set course=:course where studentId=:id")
                                    .setParameter("course", updateCourse)
                                    .setParameter("id", id)
                                    .executeUpdate();
                        }
                    } else {
                        System.out.println("Invalid Input! Try Again...");
                        input.nextLine();
                    }
                    tx.commit();
                    System.out.println("Student Course Updated Successfully!");

                } else if (choice == 2) {
                    String id;
                    System.out.println("\n====Update Student Semester====");

                    while (true) {
                        try {
                            System.out.print("Enter student id: ");
                            id = input.nextLine().trim();
                            if (!id.matches("K\\d{5}") || id.isEmpty()) {
                                throw new InvalidStudentIdException("Invalid Student Id! Student Id must start with capital 'K' with followed by 5-digits unique numbers...");
                            }
                            Session session = factory.openSession();
                            AddStudents existingId = session.get(AddStudents.class, id);
                            session.close();

                            if (existingId == null) {
                                System.out.println("Student Found: " + existingId.getStudentName() + " | ID: " + id);
                                continue;
                            }
                            System.out.println("Student Found: " + existingId.getStudentName() + " | ID: " + id);
                            break;

                        } catch (InvalidStudentIdException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    Session session = factory.openSession();
                    Transaction tx = session.beginTransaction();

                    System.out.println("Student Semester: ");
                    for (AddStudents.Semester c : AddStudents.Semester.values()) {
                        System.out.println("  " + c.ordinal() + " -> " + c);
                    }
                    System.out.print("Enter update your semester: ");
                    if (input.hasNextInt()) {
                        int semesterChoice = input.nextInt();
                        input.nextLine();
                        if (semesterChoice < 0 || semesterChoice > AddStudents.Semester.values().length) {
                            System.out.println("Invalid Semester Choice!");
                            break;
                        } else {
                            AddStudents.Semester updateSemester = AddStudents.Semester.values()[semesterChoice];
                            session.createQuery("update AddStudents set semester=:semester where studentId=:id")
                                    .setParameter("semester", updateSemester)
                                    .setParameter("id", id)
                                    .executeUpdate();
                        }
                    } else {
                        System.out.println("Invalid Input! Try Again...");
                        input.nextLine();
                    }
                    tx.commit();
                    System.out.println("Student Semester Updated Successfully!");

                } else if (choice == 3) {
                    String id;
                    System.out.println("\n====Update Student Mobile number====");

                    while (true) {
                        try {
                            System.out.print("Enter student id: ");
                            id = input.nextLine().trim();
                            if (!id.matches("K\\d{5}") || id.isEmpty()) {
                                throw new InvalidStudentIdException("Invalid Student Id! Student Id must start with capital 'K' with followed by 5-digits uniqu numbers...");
                            }
                            Session session = factory.openSession();
                            AddStudents existingId = session.get(AddStudents.class, id);
                            session.close();

                            if (existingId == null) {
                                System.out.println("Student Found: " + existingId.getStudentName() + " | ID: " + id);
                                continue;
                            }
                            System.out.println("Student Found: " + existingId.getStudentName() + " | ID: " + id);
                            break;

                        } catch (InvalidStudentIdException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    Session session = factory.openSession();
                    Transaction tx = session.beginTransaction();

                    while (true) {
                        try {
                            System.out.print("Enter update your mobile number: ");
                            String updateMobileNumber = input.nextLine();
                            if (updateMobileNumber.isEmpty() || updateMobileNumber.length() != 10) {
                                throw new InvalidMobileNumberException("Invalid Updated Mobile Number! Length of updated mobile number must be 10");
                            } else {
                                session.createQuery("update AddStudents set mobileNumber=:mobile where studentId=:id")
                                        .setParameter("mobile", updateMobileNumber)
                                        .setParameter("id", id)
                                        .executeUpdate();

                                tx.commit();
                                System.out.println("Student Mobile Number Updated Successfully!");
                                break;
                            }
                        } catch (InvalidMobileNumberException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                } else if (choice == 4) {
                    String id;
                    System.out.println("\n====Update Student Email Id====");

                    while (true) {
                        try {
                            System.out.print("Enter student id: ");
                            id = input.nextLine().trim();
                            if (!id.matches("K\\d{5}") || id.isEmpty()) {
                                throw new InvalidStudentIdException("Invalid Student Id! Student Id must start with capital 'K' with followed by 5-digits uniqu numbers...");
                            }
                            Session session = factory.openSession();
                            AddStudents existingId = session.get(AddStudents.class, id);
                            session.close();

                            if (existingId == null) {
                                System.out.println("Student Found: " + existingId.getStudentName() + " | ID: " + id);
                                continue;
                            }
                            System.out.println("Student Found: " + existingId.getStudentName() + " | ID: " + id);
                            break;

                        } catch (InvalidStudentIdException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    Session session = factory.openSession();
                    Transaction tx = session.beginTransaction();

                    while (true) {
                        try {
                            System.out.print("Enter update your email id: ");
                            String email = input.nextLine();
                            if (email.isEmpty() || !email.endsWith("@gmail.com")) {
                                throw new InvalidEmailIdException("Invalid Email Id! Email Id must ends-with '@gmail.com'...");
                            } else {
                                session.createQuery("update AddStudents set email=:email where studentId=:id")
                                        .setParameter("email", email)
                                        .setParameter("id", id)
                                        .executeUpdate();
                            }
                            tx.commit();
                            System.out.println("Student Email Id Updated Successfully!");
                            break;

                        } catch (InvalidEmailIdException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } else if (choice == 5) {
                    String id;
                    System.out.println("\n====Update Student Address====");

                    while (true) {
                        try {
                            System.out.print("Enter student id: ");
                            id = input.nextLine().trim();
                            if (!id.matches("K\\d{5}") || id.isEmpty()) {
                                throw new InvalidStudentIdException("Invalid Student Id! Student Id must start with capital 'K' with followed by 5-digits uniqu numbers...");
                            }
                            Session session = factory.openSession();
                            AddStudents existingId = session.get(AddStudents.class, id);
                            session.close();

                            if (existingId == null) {
                                System.out.println("Student Found: " + existingId.getStudentName() + " | ID: " + id);
                                continue;
                            }
                            System.out.println("Student Found: " + existingId.getStudentName() + " | ID: " + id);
                            break;

                        } catch (InvalidStudentIdException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    Session session = factory.openSession();
                    Transaction tx = session.beginTransaction();

                    System.out.print("Enter update address: ");
                    String updateAddress = input.nextLine();

                    session.createQuery("update AddStudents set address=:address where studentId=:id")
                            .setParameter("address", updateAddress)
                            .setParameter("id", id)
                            .executeUpdate();

                    tx.commit();
                    System.out.println("Student Address Updated Successfully!");
                }

            } else {
                System.out.println("Invalid Choice! Try Again...");
                input.nextLine();
                break;
            }
        }
    }
}