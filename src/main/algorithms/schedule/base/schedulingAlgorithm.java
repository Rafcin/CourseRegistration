package main.algorithms.schedule.base;

import main.documents.clients.faculty.faculty;
import main.documents.clients.student.student;
import main.documents.course.course;
import main.documents.session.session;

import java.util.List;

public abstract class schedulingAlgorithm {
    public abstract void schedule(List<course> courses, List<session> sessions, List<student> students, List<faculty> faculty);
}
