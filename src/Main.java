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

import static main.constants.constants.*;

/**
 * This is a course registration system built on MongoDB. I decided to be adventurous and use MongoDB and just try out some new things.
 * I'm not sure if I'll continue to use MongoDB, but I'm having fun with it.
 */
public class Main {
    public static void main(String[] args) {
        //Remove all the users, courses, and sessions for a clean test. Completely random users will be created each time.
        registrar.deleteAllCourses();
        registrar.deleteAllStudents();
        registrar.deleteAllFaculty();
        registrar.deleteAllSessions();
        //Create the new users and classes.
        generate generate = new generate();
        generate.students(150);
        generate.faculty(10);
        //Create only 5 courses of the set that exists. Pass no value for all courses.
        generate.courses(5);
        //Schedule the classes and faculty.
        registrar.schedule();
        registrar.print(COLLECTION_COURSES, course.class);
        registrar.print(COLLECTION_SESSIONS, session.class);
        registrar.print(COLLECTION_FACULTY, faculty.class);
        registrar.print(COLLECTION_STUDENTS, student.class);
    }
}