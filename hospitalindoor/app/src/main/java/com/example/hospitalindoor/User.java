package com.example.hospitalindoor;

public class User {
    String name,Specialization;

    public User() {

    }
    public User(String name, String specialization) {
        this.name = name;
        Specialization = specialization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return Specialization;
    }

    public void setSpecialization(String specialization) {
        Specialization = specialization;
    }
}
