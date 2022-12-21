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

/**
 * This class is responsible for scheduling the faculty. It will take the faculty and courses and link them correctly. Any connected queries that need to be made to the db
 * can be queried with the courseId and facultyId to find linked sessions, faculty and courses.
 */
public class facultySchedulingAlgorithm {
    /**
     * Schedule faculty into courses. Does not require an id, automagical.
     */
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

    /**
     * Assigns a faculty member to a course
     * @param faculty
     * @param course
     */
    private void assignFaculty(faculty faculty, course course) {
        // Update the course with the faculty member's ID
        course.setFacultyId(faculty.getId());
        // Update the faculty member's course ID
        faculty.setCourseId(course.getId());
        // Update the course and faculty member in the database
        updateCourse(course, faculty);
        updateFaculty(course, faculty);
    }

    /**
     * Updates a course
     * @param course
     * @param faculty
     */
    private void updateCourse(course course,  faculty faculty) {
        // Use the MongoDB class to update the course in the database
        mongodb.updateOne(COLLECTION_COURSES, Filters.eq("id", course.getId()), new Document("$set", new Document("facultyId", faculty.getId())));
    }

    /**
     * Updates a faculty member
     * @param course
     * @param faculty
     */
    private void updateFaculty(course course, faculty faculty) {
        // Use the MongoDB class to update the faculty member in the database
        mongodb.updateOne(COLLECTION_FACULTY, Filters.eq("id", faculty.getId()), new Document("$set", new Document("courseId", course.getId())));
    }
}
