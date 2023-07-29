package com.example.hospitalindoor;

public class appoint_confirm {

    String doctorname;
    String date;
    String name;
    String status;

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public appoint_confirm(String doctorname, String date, String name, String status) {
        this.doctorname = doctorname;
        this.date = date;
        this.name = name;
        this.status = status;
    }
    public appoint_confirm() {

    }



}
