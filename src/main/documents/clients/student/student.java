package main.documents.clients.student;

import com.google.gson.Gson;
import main.constants.constants;
import main.documents.clients.client.client;

import java.io.Serializable;

public class student extends client implements Serializable {
    //User GPA
    private double gpa;
    //Start date of the client
    private String startDate;
    //Maximum number of courses a student can take in a semester
    private int maximumCourses;

    public student(constants.CLIENT_TYPE clientType, String firstName, String middleName, String lastName, String email, String phoneNumber, String streetAddress, String city, String state, String zipCode, String country, String dateOfBirth, double gpa, String startDate, int maximumCourses) {
        super(clientType, firstName, middleName, lastName, email, phoneNumber, streetAddress, city, state, zipCode, country, dateOfBirth);
        this.gpa = gpa;
        this.startDate = startDate;
        this.maximumCourses = maximumCourses;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getMaximumCourses() {
        return maximumCourses;
    }

    public void setMaximumCourses(int maximumCourses) {
        this.maximumCourses = maximumCourses;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
