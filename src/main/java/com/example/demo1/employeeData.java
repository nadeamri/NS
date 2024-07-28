package com.example.demo1;

import java.sql.Date;

public class employeeData {
    private Integer employeeId;
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNum;
    private String position;
    private Date date;
    private String image;
    private  double salary ;

    public employeeData(Integer employeeId, String firstName, String lastName, String position, double salary) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.salary = salary;
    }

    public employeeData(Integer employeeId, String firstName, String lastName, String gender, String phoneNum, String position, String image, Date date) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.phoneNum = phoneNum;
        this.position = position;
        this.date = date;
        this.image = image;
    }

    // Getters
    public Integer getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    public Date getDate() {
        return date;
    }

    public String getImage() {
        return image;
    }
}
