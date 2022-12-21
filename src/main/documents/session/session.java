package main.documents.session;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Session class contains session info for courses.
 */
public class session {
    private String courseId;
    private String id;
    private String facultyId;
    private List<String> enrolledStudentIds;
    private String startDate;
    private String endDate;
    private int minStudents;
    private int maxStudents;

    public session(String courseId, String facultyId) {
        this.courseId = courseId;
        this.id = UUID.randomUUID().toString();
        this.facultyId = facultyId;
        this.enrolledStudentIds = new ArrayList<>();
        this.minStudents = 0;
        this.maxStudents = 0;
        this.startDate = null;
        this.endDate = null;
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

    public List<String> getEnrolledStudentIds() {
        return enrolledStudentIds;
    }

    public void setEnrolledStudentIds(List<String> enrolledStudentIds) {
        this.enrolledStudentIds = enrolledStudentIds;
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
}
