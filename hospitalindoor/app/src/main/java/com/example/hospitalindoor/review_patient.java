package com.example.hospitalindoor;

public class review_patient {
    String review,uid;

    public review_patient() {

    }
    public review_patient(String review, String uid) {
        this.review = review;
        this.uid = uid;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
