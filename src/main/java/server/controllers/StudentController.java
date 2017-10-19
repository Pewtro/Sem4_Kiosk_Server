package server.controllers;

import com.google.gson.Gson;
import server.models.Event;
import server.models.Student;
import server.providers.StudentTable;

import java.util.ArrayList;

public class StudentController {

    Gson gson;
/*    DBmanager db = new DBmanager();

    public StudentController() {
        this.gson = new Gson();

    }

    public ArrayList<Student> getStudents() {
        DBmanager db = new DBmanager();
        ArrayList<Student> students = db.getStudents();
        db.close();
        return students;
    }

/*
    public Student getStudents(int id) {
        DBmanager db = new DBmanager();
        Student student = db.getStudents(id);
        db.close();
        return students;
    }*/

    public Student verifyStudentCreation(String firstName, String lastName, String password, String email) throws IllegalArgumentException {
        Student verifiedStudent = new Student(firstName, lastName, password, email);

        //nedenunder tjekkes der for diverse krav.
        // nedenunder viser at der skal være et "@".
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Email did not contain @");
            // Dette gør at serveren kræver at der ikke indgår tal i navn.
        } else if (firstName.length() < 2 || firstName.matches(".*\\d.*")) {
            throw new IllegalArgumentException("firstName must contain at least 2 characters, and can't contain numbers");
        } else if (lastName.length() < 2 || lastName.matches(".*\\d.*")) {
            throw new IllegalArgumentException("lastName must contain at least 2 characters, and can't contain numbers");

            // her tjekkes om det indtastede indeholder mindst et bogstaver samt mindst et tal.
        } else if (!password.matches(".*[a-zA-Z].*") || !password.matches(".*\\d.*")) {
            throw new IllegalArgumentException("password must contain atleast one char and one number");
        }
        //hvis alle tjeks godkendes så bliver studenten returneret
        return verifiedStudent;


    }
}

/*
    public boolean addStudent(Student student) throws Exception {

        final Student newStudent = new Student();
        newStudent.setFirstName(firstname);


        studentTable.addStudent(currentUser) /*, new AsyncCallback<Boolean>() {

    }
        /*String hashedPassword = Digester.hashWithSalt(student.getPassword());
        student.setPassword(hashedPassword);
        return DBmanager.addStudent(student);*/