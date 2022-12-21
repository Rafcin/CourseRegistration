import com.github.javafaker.Faker;
import main.constants.constants;
import main.documents.clients.faculty.faculty;
import main.documents.clients.student.student;
import main.documents.course.course;
import main.documents.session.session;
import main.fake.generate;
import main.registrar.registrar;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import static main.constants.constants.COLLECTION_COURSES;

public class Main {
    public static void main(String[] args) {
        //Remove all the users, courses, and sessions for a clean test. Completely random users will be created each time.
        registrar.deleteAllCourses();
        registrar.deleteAllStudents();
        registrar.deleteAllFaculty();
        registrar.deleteAllSessions();
        //Create the new users and classes.
        generate generate = new generate();
        generate.students(100);
        generate.faculty(20);
        generate.courses();
        //Schedule the classes and faculty.
        registrar.schedule();
        registrar.print(COLLECTION_COURSES, course.class);
    }
}