package main.registrar;

import com.mongodb.client.model.Filters;
import main.fake.generate;
import main.services.table.table;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class registrarTest {

    @Test
    @Order(1)
    void deleteAllStudents() {
        registrar.deleteAllStudents();
    }

    @Test
    @Order(2)
    void deleteAllFaculty() {
        registrar.deleteAllFaculty();
    }

    @Test
    @Order(3)
    void deleteAllCourses() {
        registrar.deleteAllCourses();
    }

    @Test
    @Order(4)
    void deleteAllSessions() {
        registrar.deleteAllSessions();
    }

    @Test
    @Order(5)
    void registerStudent() {
        generate generate = new generate();
        generate.students(10);
    }

    @Test
    @Order(6)
    void registerFaculty() {
        generate generate = new generate();
        generate.faculty(10);
    }

    @Test
    @Order(7)
    void createCourse() {
        generate generate = new generate();
        generate.courses();
    }

    @Test
    @Order(8)
    void registerSession() {
    }

    @Test
    @Order(9)
    void schedule() {
        registrar.schedule();
    }

    @Test
    @Order(10)
    void queryStudents() {
        table table = new table();
        table.print(registrar.queryStudents());
    }

    @Test
    @Order(11)
    void testQueryStudents() {
        table table = new table();
        table.print(registrar.queryStudents(Filters.eq("gpa", 4.0)));
    }

    @Test
    @Order(12)
    void queryFaculty() {
        table table = new table();
        table.print(registrar.queryFaculty());
    }

    @Test
    @Order(13)
    void testQueryFaculty() {
        table table = new table();
        table.print(registrar.queryFaculty(Filters.eq("isTenured", true)));
    }

    @Test
    @Order(14)
    void queryCourses() {
        table table = new table();
        table.print(registrar.queryCourses());
    }

    @Test
    @Order(15)
    void testQueryCourses() {
        table table = new table();
        table.print(registrar.queryCourses(Filters.eq("name", "CS")));
    }

    @Test
    @Order(16)
    void querySessions() {
        table table = new table();
        table.print(registrar.querySessions());
    }

    @Test
    @Order(17)
    void testQuerySessions() {
        table table = new table();
        table.print(registrar.querySessions(Filters.eq("maxStudents", 10)));
    }

    @Test
    void query() {
    }

    @Test
    void print() {
    }

    @Test
    void testPrint() {
    }
}