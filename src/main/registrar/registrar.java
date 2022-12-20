package main.registrar;

import main.documents.clients.faculty.faculty;
import main.documents.clients.student.student;
import main.documents.course.course;
import main.documents.session.session;
import org.bson.Document;
import main.services.mongodb.mongodb;

import java.util.UUID;

public class registrar {
    private static final String COLLECTION_STUDENTS = "students";
    private static final String COLLECTION_FACULTY = "faculty";
    private static final String COLLECTION_COURSES = "courses";
    private static final String COLLECTION_SESSIONS = "sessions";
    private static mongodb mongoDB;
    static {mongoDB = new mongodb();}
    private registrar() {}

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
                .append("startDate", student.getStartDate());
        mongoDB.insertOne(COLLECTION_STUDENTS, doc);
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
                .append("tenured", faculty.isTenured());
        mongoDB.insertOne(COLLECTION_FACULTY, doc);
    }

    public static void createCourse(course course) {
        Document doc = new Document("department", course.getDepartment())
                .append("code", course.getCode())
                .append("description", course.getDescription())
                .append("minStudents", course.getMinStudents())
                .append("maxStudents", course.getMaxStudents())
                .append("id", course.getDepartment() + course.getCode());
        mongoDB.insertOne(COLLECTION_COURSES, doc);
    }

    public static void scheduleSession(String courseId, session session) {
        Document doc = new Document("courseId", courseId)
                .append("sessionId", UUID.randomUUID().toString())
                .append("facultyId", session.getFacultyId())
                .append("startDate", session.getStartDate())
                .append("endDate", session.getEndDate())
                .append("minStudents", session.getMinStudents())
                .append("maxStudents", session.getMaxStudents())
                .append("students", session.getStudents());
        mongoDB.insertOne(COLLECTION_SESSIONS, doc);

        Document filter = new Document("id", courseId);
        long numSessions = mongoDB.countDocuments(COLLECTION_SESSIONS, filter);
        long minStudents = mongoDB.find(COLLECTION_COURSES, filter).get(0).getLong("minStudents");
        if (numSessions == 0 || numSessions * minStudents > session.getStudents().size()) {
            // If this is the first session for the course or if the total number of students across all sessions
            // is less than the minimum number of students required for the course, cancel the course
            mongoDB.deleteOne(COLLECTION_COURSES, filter);
        }
    }

    public static void deleteAllStudents() {
        mongoDB.deleteAll(COLLECTION_STUDENTS);
    }

    public static void deleteAllFaculty() {
        mongoDB.deleteAll(COLLECTION_FACULTY);
    }

    public static void deleteAllCourses() {
        mongoDB.deleteAll(COLLECTION_COURSES);
    }

}
