package main.algorithms.schedule.base;
import main.services.mongodb.mongodb;

import main.documents.clients.faculty.faculty;
import main.documents.clients.student.student;
import main.documents.course.course;
import main.documents.session.session;

import java.util.List;

/**
 * Base algorithm class
 */
public abstract class schedulingAlgorithm {
    protected mongodb mongodb;
    public schedulingAlgorithm() {
        // Initialize the MongoDB instance
        mongodb = new mongodb();
    }

    public abstract void schedule();
}
