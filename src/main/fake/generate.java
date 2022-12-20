package main.fake;

import com.github.javafaker.Faker;
import main.constants.constants;
import main.documents.clients.faculty.faculty;
import main.documents.clients.student.student;
import main.documents.course.course;
import main.registrar.registrar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Generate fake data for testing purposes.
 */
public class generate {
    Faker faker = new Faker();
    private static final List<course> courses = new ArrayList<course>(
            Arrays.asList(
                    new course("CS", "1A", "Introduction to Computer Science", 10, 20, "CS1A"),
                    new course("CS", "1B", "Introduction to Computer Science II", 10, 20, "CS1B"),
                    new course("CS", "1C", "Introduction to Computer Science III", 10, 20, "CS1C"),
                    new course("CS", "1D", "Data Structures and Algorithms", 10, 20, "CS1D"),
                    new course("CS", "3A", "COMPUTER ORGANIZATION AND MACHINE LANGUAGE", 10, 20, "CS3A"),
                    new course("CS", "3B", "COMPUTER ORGANIZATION AND MACHINE LANGUAGE II", 10, 20, "CS3B"),
                    new course("CS", "4A", "INTRODUCTION TO JAVA FOR COMPUTER SCIENCE", 10, 20, "CS4A"),
                    new course("CS", "4B", "ADVANCED TOPICS IN JAVA FOR COMPUTER SCIENCE", 10, 20, "CS4B"),
                    new course("CS", "30A", "COMPUTER DISCRETE MATHEMATICS I", 10, 20, "CS30A"),
                    new course("CS", "30B", "COMPUTER DISCRETE MATHEMATICS II", 10, 20, "CS30B"))
    );

    public void students(int size) {
        IntStream.range(0, size).forEach(i -> {
            List<String> requiredCourses = new ArrayList<String>();
            IntStream.range(0, faker.number().numberBetween(1, 5)).forEach(j -> {
                requiredCourses.add(courses.get(faker.number().numberBetween(0, courses.size() - 1)).getId());
            });
            student student = new student(
                    constants.CLIENT_TYPE.STUDENT,
                    faker.name().firstName(),
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.internet().emailAddress(),
                    faker.phoneNumber().phoneNumber(),
                    faker.address().streetAddress(),
                    faker.address().city(),
                    faker.address().state(),
                    faker.address().zipCode(),
                    faker.address().country(),
                    faker.date().birthday().toString(),
                    4.0,
                    faker.date().between(new Date("12/01/2022"), new Date("12/11/2022")).toString(),
                    4,
                    //Dedupe courses
                    requiredCourses.stream().distinct().collect(Collectors.toList())
            );
            registrar.registerStudent(student);
        });
    }

    public void faculty(int size) {
        IntStream.range(0, size).forEach(i -> {
            faculty faculty = new faculty(
                    constants.CLIENT_TYPE.STUDENT,
                    faker.name().firstName(),
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.internet().emailAddress(),
                    faker.phoneNumber().phoneNumber(),
                    faker.address().streetAddress(),
                    faker.address().city(),
                    faker.address().state(),
                    faker.address().zipCode(),
                    faker.address().country(),
                    faker.date().birthday().toString(),
                    true,
                    faker.date().between(new Date("02/01/2020"), new Date("12/11/2022")).toString()
            );
            registrar.registerFaculty(faculty);
        });
    }

    public void courses() {
        courses.forEach(course -> registrar.createCourse(course));
    }
}
