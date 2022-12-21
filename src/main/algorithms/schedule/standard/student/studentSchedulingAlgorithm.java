package main.algorithms.schedule.standard.student;

import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import main.services.mongodb.mongodb;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static main.constants.constants.*;

import main.documents.clients.faculty.faculty;
import main.documents.clients.student.student;
import main.documents.course.course;
import main.documents.session.session;

/**
 * Student Scheduling Algorithm, handles scheduling students into courses and cancelling classes if not enough students are required.
 * A majority of the logic can be controlled by simple aggregation queries to mongo.
 */

public class studentSchedulingAlgorithm {
    /**
     * Schedule students into courses and takes a courseId to do it.
     * @param courseId
     */
    public void schedule(String courseId) {
        course course = mongodb.toList(mongodb.getCollection(COLLECTION_COURSES), Filters.eq("id", courseId), course.class).get(0);
        if (course == null) {
            throw new IllegalArgumentException("Course with ID " + courseId + " not found");
        }

        //List<student> students = mongodb.toList(studentsIterable, student.class);
        List<Document> sorted = new ArrayList<>();
        mongodb.getCollection(COLLECTION_STUDENTS).find(Filters.in("requiredCoursesIds", courseId)).sort(Sorts.descending("gpa")).into(sorted);
        List<student> interestedStudents = mongodb.toList(sorted, student.class);

        // Assign students to the course until either all students have been assigned
        // or the maximum number of students for the course has been reached
        List<session> sessions = new ArrayList<>();
        // Assign students to sessions
        for (student student : interestedStudents) {
            if (student.getEnrolledCoursesIds().size() >= student.getMaximumCourses()) {
                // The student has reached their maximum number of courses, skip them
                continue;
            }

            // Find the current session for the student
            session session = getCurrentSession(sessions, course);
            if (session == null) {
                // No available sessions, create a new one
                session = new session(courseId, course.getFacultyId());
                session.setCourseId(courseId);
                session.setFacultyId(course.getFacultyId());
                session.setStartDate(course.getStartDate());
                session.setEndDate(course.getEndDate());
                session.setMinStudents(course.getMinStudents());
                session.setMaxStudents(course.getMaxStudents());
                session.setEnrolledStudentIds(new ArrayList<>());
                sessions.add(session);
            }

            // Enroll the student in the session
            session.getEnrolledStudentIds().add(student.getId());
            student.getEnrolledCoursesIds().add(courseId);
            student.getRequiredCoursesIds().remove(courseId);

            // Update the student in the database
            //mongodb.updateOne(COLLECTION_STUDENTS, Filters.eq("id", student.getId()), mongodb.toDocument(student));
            mongodb.updateOne(COLLECTION_STUDENTS, Filters.eq("id", student.getId()), new Document("$set", mongodb.toDocument(student)));
        }

        // Save the updated sessions to the database
        saveSessions(sessions, course);
    }

    /**
     * Get current session
     * @param sessions
     * @param course
     * @return
     */

    private session getCurrentSession(List<session> sessions, course course) {
        for (session session : sessions) {
            if (session.getEnrolledStudentIds().size() < session.getMaxStudents()) {
                return session;
            }
        }
        return null;
    }

    /**
     * Save sessions
     * @param sessions
     * @param course
     */

    private void saveSessions(List<session> sessions, course course) {
        // Delete all existing sessions
        mongodb.deleteMany(COLLECTION_SESSIONS, Filters.eq("courseId", course.getId()));

        // Save the updated sessions to the database
        for (session session : sessions) {
            mongodb.insertOne(COLLECTION_SESSIONS, mongodb.toDocument(session));
        }

        // Update the course status based on the number of students enrolled in all sessions
        int totalEnrolled = sessions.stream().mapToInt(s -> s.getEnrolledStudentIds().size()).sum();
        if (totalEnrolled < course.getMinStudents()) {
            course.setStatus(COURSE_STATUS.CANCELLED);
        } else {
            course.setStatus(COURSE_STATUS.ACTIVE);
            course.setSessionIds(sessions.stream().map(s -> s.getId()).collect(Collectors.toList()));
        }

        // Save the updated course to the database
        //mongodb.updateOne(COLLECTION_COURSES, Filters.eq("id", course.getId()), mongodb.toDocument(course));
        mongodb.updateOne(COLLECTION_COURSES, Filters.eq("id", course.getId()), new Document("$set", mongodb.toDocument(course)));
    }

}