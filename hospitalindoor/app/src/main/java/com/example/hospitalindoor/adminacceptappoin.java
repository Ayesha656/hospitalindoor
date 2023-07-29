package com.example.hospitalindoor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class adminacceptappoin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminacceptappoin);
        Intent x = new Intent(adminacceptappoin.this,admindashboard.class);
        startActivity(x);
        finish();




    }
}