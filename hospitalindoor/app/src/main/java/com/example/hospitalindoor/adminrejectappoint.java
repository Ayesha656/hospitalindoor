package com.example.hospitalindoor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class adminrejectappoint extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminrejectappoint);
        Intent x = new Intent(adminrejectappoint.this,admindashboard.class);
        startActivity(x);
        finish();
    }
}