import main.documents.course.course;
import main.fake.generate;
import main.registrar.registrar;
import org.junit.jupiter.api.Test;

import static main.constants.constants.COLLECTION_COURSES;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
        @Test
        void main() {
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