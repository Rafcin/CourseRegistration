package main.constants;

/**
 * Constants used throughout the application
 */
public class constants {
    public static enum CLIENT_TYPE { STUDENT, FACULTY };
    public static enum COURSE_STATUS { ACTIVE, INACTIVE, CANCELLED };

    public static final String CONNECTION_STRING = "mongodb://localhost:27017";
    public static final String DATABASE_NAME = "course_registration";
    public static final String COLLECTION_STUDENTS = "students";
    public static final String COLLECTION_FACULTY = "faculty";
    public static final String COLLECTION_COURSES = "courses";
    public static final String COLLECTION_SESSIONS = "sessions";
}
