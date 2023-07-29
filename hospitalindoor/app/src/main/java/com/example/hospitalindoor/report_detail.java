package com.example.hospitalindoor;

public class report_detail {
    String reporturl;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    String uid;

    public report_detail() {

    }
    public report_detail(String reporturl,String uid) {
        this.reporturl = reporturl;
        this.uid=uid;
    }

    public String getReporturl() {
        return reporturl;
    }

    public void setReporturl(String reporturl) {
        this.reporturl = reporturl;
    }
}
