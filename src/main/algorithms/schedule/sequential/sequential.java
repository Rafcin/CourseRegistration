package main.algorithms.schedule.sequential;

import main.algorithms.schedule.base.schedulingAlgorithm;
import main.documents.clients.faculty.faculty;
import main.documents.clients.student.student;
import main.documents.course.course;
import main.documents.session.session;

import java.util.List;

public class sequential extends schedulingAlgorithm {
    @Override
    public void schedule(List<course> courses, List<session> sessions, List<student> students, List<faculty> faculty) {
        for (course course : courses) {
            for (session session : sessions) {
                if (session.getCourseId().equals(course.getId())) {
                    for (student student : students) {
                        if (session.getStudents().size() < session.getMaxStudents()) {
                            session.getStudents().add(student.getId());
                        }
                    }
                }
            }
        }
    }
}
