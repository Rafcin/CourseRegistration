package main.documents.course;

public class course {
    private String department;
    private String code;
    private String description;
    private int minStudents;
    private int maxStudents;
    private String id;

    public course(String department, String code, String description, int minStudents, int maxStudents, String id) {
        this.department = department;
        this.code = code;
        this.description = description;
        this.minStudents = minStudents;
        this.maxStudents = maxStudents;
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
}
