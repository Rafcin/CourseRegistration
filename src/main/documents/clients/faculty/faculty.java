package main.documents.clients.faculty;

import com.google.gson.Gson;
import main.constants.constants;
import main.documents.clients.client.client;

import java.io.Serializable;

public class faculty extends client implements Serializable {


    //Is the faculty tenured
    private boolean isTenured;
    //Hire date of the faculty
    private String hireDate;
    //Course faculty teaches
    private String courseId;

    public faculty(constants.CLIENT_TYPE clientType, String firstName, String middleName, String lastName, String email, String phoneNumber, String streetAddress, String city, String state, String zipCode, String country, String dateOfBirth, boolean isTenured, String hireDate) {
        super(clientType, firstName, middleName, lastName, email, phoneNumber, streetAddress, city, state, zipCode, country, dateOfBirth);
        this.isTenured = isTenured;
        this.hireDate = hireDate;
        this.courseId = null;
    }

    public boolean isTenured() {
        return isTenured;
    }

    public void setTenured(boolean tenured) {
        isTenured = tenured;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
