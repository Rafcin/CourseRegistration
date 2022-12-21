package main.fake;

import com.github.javafaker.Faker;
import main.constants.constants;
import main.documents.clients.faculty.faculty;
import main.documents.clients.student.student;
import main.documents.course.course;
import main.registrar.registrar;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Generate fake data for testing purposes.
 */
public class generate {
    Faker faker = new Faker();

    public static float randomGpa() {
        Random random = new Random();
        return (float) (random.nextDouble() * 4.0);
    }
    private static final List<course> courses = new ArrayList<course>(
            Arrays.asList(
                    new course("CS", "1A", "Introduction to Computer Science", 10, 20, "CS1A", "01/01/2023", "05/10/2023"),
                    new course("CS", "1B", "Introduction to Computer Science II", 10, 20, "CS1B", "01/01/2023", "05/10/2023"),
                    new course("CS", "1C", "Introduction to Computer Science III", 10, 20, "CS1C", "01/01/2023", "05/10/2023"),
                    new course("CS", "1D", "Data Structures and Algorithms", 10, 20, "CS1D", "01/01/2023", "05/10/2023"),
                    new course("CS", "3A", "COMPUTER ORGANIZATION AND MACHINE LANGUAGE", 10, 20, "CS3A", "01/01/2023", "05/10/2023"),
                    new course("CS", "3B", "COMPUTER ORGANIZATION AND MACHINE LANGUAGE II", 10, 20, "CS3B", "01/01/2023", "05/10/2023"),
                    new course("CS", "4A", "INTRODUCTION TO JAVA FOR COMPUTER SCIENCE", 10, 20, "CS4A", "01/01/2023", "05/10/2023"),
                    new course("CS", "4B", "ADVANCED TOPICS IN JAVA FOR COMPUTER SCIENCE", 10, 20, "CS4B", "01/01/2023", "05/10/2023"),
                    new course("CS", "5A", "INTRODUCTION TO COMPUTER NETWORKS", 10, 20, "CS5A", "01/01/2023", "05/10/2023"),
                    new course("CS", "5B", "ADVANCED TOPICS IN COMPUTER NETWORKS", 10, 20, "CS5B", "01/01/2023", "05/10/2023"),
                    new course("CS", "6A", "INTRODUCTION TO DATABASES", 10, 20, "CS6A", "01/01/2023", "05/10/2023"),
                    new course("CS", "6B", "ADVANCED TOPICS IN DATABASES", 10, 20, "CS6B", "01/01/2023", "05/10/2023"),
                    new course("CS", "7A", "INTRODUCTION TO OPERATING SYSTEMS", 10, 20, "CS7A", "01/01/2023", "05/10/2023"),
                    new course("CS", "7B", "ADVANCED TOPICS IN OPERATING SYSTEMS", 10, 20, "CS7B", "01/01/2023", "05/10/2023"),
                    new course("CS", "8A", "INTRODUCTION TO ARTIFICIAL INTELLIGENCE", 10, 20, "CS8A", "01/01/2023", "05/10/2023"),
                    new course("CS", "8B", "ADVANCED TOPICS IN ARTIFICIAL INTELLIGENCE", 10, 20, "CS8B", "01/01/2023", "05/10/2023"),
                    new course("CS", "9A", "INTRODUCTION TO SOFTWARE ENGINEERING", 10, 20, "CS9A", "01/01/2023", "05/10/2023"),
                    new course("CS", "9B", "ADVANCED TOPICS IN SOFTWARE ENGINEERING", 10, 20, "CS9B", "01/01/2023", "05/10/2023"),
                    new course("CS", "10A", "INTRODUCTION TO COMPUTER SECURITY", 10, 20, "CS10A", "01/01/2023", "05/10/2023"),
                    new course("CS", "10B", "ADVANCED TOPICS IN COMPUTER SECURITY", 10, 20, "CS10B", "01/01/2023", "05/10/2023"),
                    new course("CS", "11A", "INTRODUCTION TO COMPUTER GRAPHICS", 10, 20, "CS11A", "01/01/2023", "05/10/2023"))
    );

    public student createStudent() {
        List<String> requiredCourses = new ArrayList<String>();
        IntStream.range(0, faker.number().numberBetween(1, 5)).forEach(j -> {
            requiredCourses.add(courses.get(faker.number().numberBetween(0, courses.size() - 1)).getId());
        });
        return new student(
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
                faker.number().numberBetween(1, 5),
                faker.date().between(new Date("12/01/2022"), new Date("12/11/2022")).toString(),
                4,
                //Dedupe courses
                requiredCourses.stream().distinct().collect(Collectors.toList())
        );
    }

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
                    randomGpa(),
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
    //Limit the number of courses created
    public void courses(int size) {
        courses.stream().limit(size).forEach(course -> registrar.createCourse(course));
    }
}
