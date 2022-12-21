package main.documents.course;

import main.constants.constants;

import java.util.ArrayList;
import java.util.List;

public class course {
    private String department;
    private String code;
    private String description;
    private int minStudents;
    private int maxStudents;
    private String facultyId;
    private List<String> sessionIds;
    private String startDate;
    private String endDate;
    private constants.COURSE_STATUS status;
    private String id;

    public course(String department, String code, String description, int minStudents, int maxStudents, String id, String startDate, String endDate) {
        this.department = department;
        this.code = code;
        this.description = description;
        this.minStudents = minStudents;
        this.maxStudents = maxStudents;
        this.facultyId = null;
        this.sessionIds = new ArrayList<>();
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = constants.COURSE_STATUS.INACTIVE;
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<String> getSessionIds() {
        return sessionIds;
    }

    public void setSessionIds(List<String> sessionIds) {
        this.sessionIds = sessionIds;
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

    public constants.COURSE_STATUS getStatus() {
        return status;
    }

    public void setStatus(constants.COURSE_STATUS status) {
        this.status = status;
    }
}
