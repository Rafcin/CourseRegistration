package main.documents.session;

import java.util.List;

public class session {
    private String courseId;
    private String sessionId;
    private String facultyId;
    private String startDate;
    private String endDate;
    private int minStudents;
    private int maxStudents;
    private List<String> students;

    public session(String courseId, String sessionId, String facultyId, String startDate, String endDate, int minStudents, int maxStudents, List<String> students) {
        this.courseId = courseId;
        this.sessionId = sessionId;
        this.facultyId = facultyId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.minStudents = minStudents;
        this.maxStudents = maxStudents;
        this.students = students;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getMinStudents() {
        return minStudents;
    }

    public void setMinStudents(int minStudents) {
        this.minStudents = minStudents;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    public List<String> getStudents() {
        return students;
    }

    public void setStudents(List<String> students) {
        this.students = students;
    }
}
