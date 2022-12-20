package main.registrar;

import main.algorithms.schedule.standard.faculty.facultySchedulingAlgorithm;
import main.documents.clients.faculty.faculty;
import main.documents.clients.student.student;
import main.documents.course.course;
import org.bson.Document;
import main.services.mongodb.mongodb;

import static main.constants.constants.*;

public class registrar {

    private static mongodb mongodb;

    static {mongodb = new mongodb();}

    private registrar() {}

    public static void deleteAllStudents() {
        mongodb.deleteAll(COLLECTION_STUDENTS);
    }

    public static void deleteAllFaculty() {
        mongodb.deleteAll(COLLECTION_FACULTY);
    }

    public static void deleteAllCourses() {
        mongodb.deleteAll(COLLECTION_COURSES);
    }

    public static void registerStudent(student student) {
        Document doc = new Document("firstName", student.getFirstName())
                .append("middleName", student.getMiddleName())
                .append("lastName", student.getLastName())
                .append("email", student.getEmail())
                .append("telephone", student.getPhoneNumber())
                .append("address", student.getStreetAddress())
                .append("city", student.getCity())
                .append("state", student.getState())
                .append("zip", student.getZipCode())
                .append("dob", student.getDateOfBirth())
                .append("gpa", student.getGpa())
                .append("startDate", student.getStartDate())
                .append("enrolledCourses", student.getEnrolledCoursesIds())
                .append("requiredCourses", student.getRequiredCoursesIds())
                .append("id", student.getId());
        mongodb.insertOne(COLLECTION_STUDENTS, doc);
    }

    public static void registerFaculty(faculty faculty) {
        Document doc = new Document("firstName", faculty.getFirstName())
                .append("middleName", faculty.getMiddleName())
                .append("lastName", faculty.getLastName())
                .append("email", faculty.getEmail())
                .append("telephone", faculty.getPhoneNumber())
                .append("address", faculty.getStreetAddress())
                .append("city", faculty.getCity())
                .append("state", faculty.getState())
                .append("zip", faculty.getZipCode())
                .append("dob", faculty.getDateOfBirth())
                .append("hireDate", faculty.getHireDate())
                .append("tenured", faculty.isTenured())
                .append("courseId", faculty.getCourseId())
                .append("id", faculty.getId());
        mongodb.insertOne(COLLECTION_FACULTY, doc);
    }

    public static void createCourse(course course) {
        Document doc = new Document("department", course.getDepartment())
                .append("code", course.getCode())
                .append("description", course.getDescription())
                .append("minStudents", course.getMinStudents())
                .append("maxStudents", course.getMaxStudents())
                .append("id", course.getDepartment() + course.getCode());
        mongodb.insertOne(COLLECTION_COURSES, doc);
    }

    public static void schedule() {
        facultySchedulingAlgorithm facultySchedulingAlgorithm = new facultySchedulingAlgorithm();
        facultySchedulingAlgorithm.schedule();
    }

}
