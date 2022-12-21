package main.registrar;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import main.algorithms.schedule.standard.faculty.facultySchedulingAlgorithm;
import main.algorithms.schedule.standard.student.studentSchedulingAlgorithm;
import main.documents.clients.faculty.faculty;
import main.documents.clients.student.student;
import main.documents.course.course;
import main.documents.session.session;
import main.services.table.table;
import org.bson.Document;
import main.services.mongodb.mongodb;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static main.constants.constants.*;

public class registrar {

    private static mongodb mongodb;

    static {
        mongodb = new mongodb();
    }

    private registrar() {
    }

    public static void deleteAllStudents() {
        mongodb.deleteAll(COLLECTION_STUDENTS);
    }

    public static void deleteAllFaculty() {
        mongodb.deleteAll(COLLECTION_FACULTY);
    }

    public static void deleteAllCourses() {
        mongodb.deleteAll(COLLECTION_COURSES);
    }

    public static void deleteAllSessions() {
        mongodb.deleteAll(COLLECTION_SESSIONS);
    }

    /**
     * Mutations
     */

    /**
     * Register a student
     * @param student
     */
    public static void registerStudent(student student) {
        mongodb.insertOne(COLLECTION_STUDENTS, mongodb.toDocument(student));
    }

    /**
     * Register a faculty member
     * @param faculty
     */
    public static void registerFaculty(faculty faculty) {
        mongodb.insertOne(COLLECTION_FACULTY, mongodb.toDocument(faculty));
    }

    /**
     * Create a course
     * @param course
     */
    public static void createCourse(course course) {
        mongodb.insertOne(COLLECTION_COURSES, mongodb.toDocument(course));
    }

    /**
     * Register a session
     * @param session
     */
    public static void registerSession(session session) {
        mongodb.insertOne(COLLECTION_SESSIONS, mongodb.toDocument(session));
    }

    /**
     * Queries
     */

    public static List<student> queryStudents() {
        return mongodb.toList(mongodb.getCollection(COLLECTION_STUDENTS), student.class);
    }

    public static List<student> queryStudents(Bson filter) {
        return mongodb.toList(mongodb.getCollection(COLLECTION_STUDENTS), filter, student.class);
    }

    public static List<faculty> queryFaculty() {
        return mongodb.toList(mongodb.getCollection(COLLECTION_FACULTY), faculty.class);
    }

    public static List<faculty> queryFaculty(Bson filter) {
        return mongodb.toList(mongodb.getCollection(COLLECTION_FACULTY), filter, faculty.class);
    }

    public static List<course> queryCourses() {
        return mongodb.toList(mongodb.getCollection(COLLECTION_COURSES), course.class);
    }

    public static List<course> queryCourses(Bson filter) {
        return mongodb.toList(mongodb.getCollection(COLLECTION_COURSES), filter, course.class);
    }

    public static List<session> querySessions() {
        return mongodb.toList(mongodb.getCollection(COLLECTION_SESSIONS), session.class);
    }

    public static List<session> querySessions(Bson filter) {
        return mongodb.toList(mongodb.getCollection(COLLECTION_SESSIONS), filter, session.class);
    }

    /**
     * Schedule will automatically get all users in the database, assign all faculty to the proper classes and assign students to proper classes.
     */
    public static void schedule() {
        //Fetch all the courses
        List<course> courses = mongodb.toList(mongodb.getCollection(COLLECTION_COURSES), course.class);
        //Assign the teachers to classes and handle all the linking
        facultySchedulingAlgorithm facultySchedulingAlgorithm = new facultySchedulingAlgorithm();
        facultySchedulingAlgorithm.schedule();
        //Create student scheduling algo and start assigning users to classes
        studentSchedulingAlgorithm studentSchedulingAlgorithm = new studentSchedulingAlgorithm();
        for(course course : courses) {
            studentSchedulingAlgorithm.schedule(course.getId());
        }
    }

    public static <T> List<T> query(List<Document> documents, Class<T> clazz) {
        return mongodb.toList(documents, clazz);
    }

    public static <T> void print(Class<T> clazz) {
        table table = new table();
        table.print(clazz);
    }

    public static <T> void print(String collection, Class<T> clazz) {
        table table = new table();
        for(T obj : mongodb.toList(mongodb.getCollection(collection), clazz)) {
            table.print(obj);
        }
    }

}
