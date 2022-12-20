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

public class Main {
    public static void main(String[] args) {
        registrar.deleteAllCourses();
        registrar.deleteAllStudents();
        registrar.deleteAllFaculty();

        generate generate = new generate();
        generate.students(100);
        generate.faculty(20);
        generate.courses();
    }
}