package com.example.hospitalindoor;

public class notification {
    String date;
    String doctorname;
    String status;
    public notification() {

    }

    public notification(String date, String doctorname, String status) {
        this.date = date;
        this.doctorname = doctorname;
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}
