package main.algorithms.schedule.standard.faculty;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import main.documents.clients.faculty.faculty;
import main.documents.course.course;
import main.services.mongodb.mongodb;
import org.bson.Document;

import java.util.List;
import java.util.Optional;

import static main.constants.constants.COLLECTION_COURSES;
import static main.constants.constants.COLLECTION_FACULTY;

public class facultySchedulingAlgorithm {
    public void schedule() {
        // Get the list of available faculty members
        List<faculty> faculty = mongodb.toList(mongodb.getCollection(COLLECTION_FACULTY), faculty.class);

        // Get the list of unassigned courses
        List<course> courses = mongodb.toList(mongodb.getCollection(COLLECTION_COURSES), "faculty", null, course.class);

        // Assign a faculty member to each unassigned course
        for (course course : courses) {
            // Find the first available faculty member
            Optional<faculty> facultyMember = faculty.stream()
                    .filter(f -> f.getCourseId() == null)
                    .findFirst();

            if (facultyMember.isPresent()) {
                // Assign the faculty member to the course
                assignFaculty(facultyMember.get(), course);
            }
        }
    }

    private void assignFaculty(faculty faculty, course course) {
        // Update the course with the faculty member's ID
        course.setFacultyId(faculty.getId());
        // Update the faculty member's course ID
        faculty.setCourseId(course.getId());
        // Update the course and faculty member in the database
        updateCourse(course, faculty);
        updateFaculty(course, faculty);
    }

    private void updateCourse(course course,  faculty faculty) {
        // Use the MongoDB class to update the course in the database
        mongodb.updateOne(COLLECTION_COURSES, Filters.eq("id", course.getId()), new Document("$set", new Document("facultyId", faculty.getId())));
    }

    private void updateFaculty(course course, faculty faculty) {
        // Use the MongoDB class to update the faculty member in the database
        mongodb.updateOne(COLLECTION_FACULTY, Filters.eq("id", faculty.getId()), new Document("$set", new Document("courseId", course.getId())));
    }
}
