package main.documents.session;

import java.util.List;

public class session {
    private String courseId;
    private String id;
    private String facultyId;
    private String startDate;
    private String endDate;
    private int minStudents;
    private int maxStudents;
    private List<String> enrolledStudentIds;

    public session(String courseId, String id, String facultyId, String startDate, String endDate, int minStudents, int maxStudents, List<String> students) {
        this.courseId = courseId;
        this.id = id;
        this.facultyId = facultyId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.minStudents = minStudents;
        this.maxStudents = maxStudents;
        this.enrolledStudentIds = students;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<String> getEnrolledStudentIds() {
        return enrolledStudentIds;
    }

    public void setEnrolledStudentIds(List<String> enrolledStudentIds) {
        this.enrolledStudentIds = enrolledStudentIds;
    }
}
