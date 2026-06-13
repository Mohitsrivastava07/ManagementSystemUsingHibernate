package com.entities;

import com.Exception.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Scanner;

@Entity
public class AddStudents {
    public enum Course {
        BCA, MCA, BTech, MTech, BCom, MCom
    }
    public enum Semester {
        SEM1, SEM2, SEM3, SEM4, SEM5, SEM6, SEM7, SEM8
    }
    public enum Gender {
        Male, Female, Other
    }

    @Id
    @Column(name="Student_ID", nullable = false)
    private String studentId;

    @Column(name="Student_name", length=95, nullable = false)
    private String studentName;

    @Column(name="Course", nullable = false)
    @Enumerated(EnumType.STRING)
    private Course course;

    @Column(name="Semester", nullable = false)
    @Enumerated(EnumType.STRING)
    private Semester semester;

    @Column(name="Gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name="Mobile_number", length=10, nullable = false)
    private String mobileNumber;

    @Column(name="Email", length=115, unique = true, nullable = false)
    private String email;

    @Column(name="Address", nullable = false)
    private String address;

    @Column(name="Date_Of_Added_Student", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfAdded;

    public AddStudents() {
        super();
    }
    public AddStudents(String studentId, String studentName, Course course, Semester semester, Gender gender, String mobileNumber, String email, String address, Date dateOfAdded) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.course = course;
        this.semester = semester;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.address = address;
        this.dateOfAdded = dateOfAdded;
    }

    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Semester getSemester() {
        return semester;
    }
    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }

    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfAdded() {
        return dateOfAdded;
    }
    public void setDateOfAdded(Date dateOfAdded) {
        this.dateOfAdded = dateOfAdded;
    }

    public static AddStudents addStudentsInput() {
        Scanner input = new Scanner(System.in);
        AddStudents addStudents = new AddStudents();

        while (true) {
            try {
                System.out.print("Enter student id: ");
                String id = input.nextLine();
                if (!id.matches("K\\d{5}") || id.isEmpty()) {
                    throw new InvalidStudentIdException("Invalid Student Id! Student Id must start with capital 'K' with followed by 5-digits uniqu numbers...");
                } else {
                    addStudents.setStudentId(id);
                    while (true) {
                        try {
                            System.out.print("Enter student name: ");
                            String name = input.nextLine();
                            if (name.isEmpty() || !name.matches("[a-zA-Z ]+")) {
                                throw new InvalidStudentNameException("Invalid Student Name! Student name must have only alphabets not any number nor special character...");
                            } else {
                                addStudents.setStudentName(name);
                                while (true) {
                                    try {
                                        System.out.println("Student Course: ");
                                        for (Course c : Course.values()) {
                                            System.out.println("  " + c.ordinal() + " -> " + c);
                                        }
                                        System.out.print("Enter choice of student course: ");
                                        if (input.hasNextInt()) {
                                            int courseChoice = input.nextInt();
                                            if (courseChoice < 0 || courseChoice >= Course.values().length) {
                                                throw new InvalidCourseException("Invalid Course Choice! Course Choice under taken from 0 to length of Course...");
                                            } else {
                                                addStudents.setCourse(Course.values()[courseChoice]);
                                                while (true) {
                                                    try {
                                                        System.out.println("Student Course Semester: ");
                                                        for (Semester s : Semester.values()) {
                                                            System.out.println("  " + s.ordinal() + " -> " + s);
                                                        }
                                                        System.out.print("Enter choice of current student semester: ");
                                                        if (input.hasNextInt()) {
                                                            int semChoice = input.nextInt();
                                                            if (semChoice < 0 || semChoice >= Semester.values().length) {
                                                                throw new InvalidSemesterException("Invalid Semester Choice! Semester Choice under taken from 0 to length of Semester...");
                                                            } else {
                                                                addStudents.setSemester(Semester.values()[semChoice]);
                                                                while (true) {
                                                                    try {
                                                                        System.out.println("Student Gender: ");
                                                                        for (Gender g : Gender.values()) {
                                                                            System.out.println("  " + g.ordinal() + " -> " + g);
                                                                        }
                                                                        System.out.print("Enter choice of student gender: ");
                                                                        if (input.hasNextInt()) {
                                                                            int genderChoice = input.nextInt();
                                                                            input.nextLine();
                                                                            if (genderChoice < 0 || genderChoice >= Gender.values().length) {
                                                                                throw new InvalidGenderException("Invalid Gender Choice! Gender Choice under taken from 0 to length of Gender...");
                                                                            } else {
                                                                                addStudents.setGender(Gender.values()[genderChoice]);
                                                                                while (true) {
                                                                                    try {
                                                                                        System.out.print("Enter student mobile number: ");
                                                                                        String mobileNumber = input.nextLine();
                                                                                        if (mobileNumber.length() != 10 || mobileNumber.isEmpty()) {
                                                                                            throw new InvalidMobileNumberException("Invalid Mobile Number! Mobile Number Must be length of only 10...");
                                                                                        } else {
                                                                                            addStudents.setMobileNumber(mobileNumber);
                                                                                            while (true) {
                                                                                                try {
                                                                                                    System.out.print("Enter student email id: ");
                                                                                                    String emailId = input.nextLine();
                                                                                                    if (emailId.isEmpty() || !emailId.endsWith("@gmail.com")) {
                                                                                                        throw new InvalidEmailIdException("Invalid Email Id! Email Id ends-with '@gmail.com'...");
                                                                                                    } else {
                                                                                                        addStudents.setEmail(emailId);
                                                                                                        while (true) {
                                                                                                            try {
                                                                                                                System.out.print("Enter student address: ");
                                                                                                                String address = input.nextLine();
                                                                                                                if (!address.matches("[a-zA-Z0-9,() ]+")) {
                                                                                                                    throw new InvalidAddressException("Invalid Address! Address have not contains only number...");
                                                                                                                } else {
                                                                                                                    addStudents.setAddress(address);

                                                                                                                    //Date automatically filled in database
                                                                                                                    addStudents.setDateOfAdded(new Date());
                                                                                                                    break;
                                                                                                                }
                                                                                                            } catch (InvalidAddressException e) {
                                                                                                                System.out.println(e.getMessage());
                                                                                                            }
                                                                                                        }
                                                                                                        break;
                                                                                                    }
                                                                                                } catch (InvalidEmailIdException e) {
                                                                                                    System.out.println(e.getMessage());
                                                                                                }
                                                                                            }
                                                                                            break;
                                                                                        }
                                                                                    } catch (InvalidMobileNumberException e) {
                                                                                        System.out.println(e.getMessage());
                                                                                    }
                                                                                }
                                                                                break;
                                                                            }
                                                                        }
                                                                    } catch (InvalidGenderException e) {
                                                                        System.out.println(e.getMessage());
                                                                    }
                                                                }
                                                                break;
                                                            }
                                                        }
                                                    } catch (InvalidSemesterException e) {
                                                        System.out.println(e.getMessage());
                                                    }
                                                }
                                                break;
                                            }
                                        }
                                    } catch (InvalidCourseException e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                                break;
                            }
                        } catch (InvalidStudentNameException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                }
            } catch (InvalidStudentIdException e) {
                System.out.println(e.getMessage());
            }
        }

        return addStudents;
    }
}
