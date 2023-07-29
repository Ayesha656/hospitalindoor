package com.example.hospitalindoor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class doctordashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctordashboard);
        CardView card1=findViewById(R.id.map);
        CardView card2=findViewById(R.id.viewappointmnet);
        CardView card3=findViewById(R.id.report2);
        CardView card5= findViewById(R.id.emergency4);
        CardView card6= findViewById(R.id.help);
        CardView card7= findViewById(R.id.editprofile2);
        CardView card8= findViewById(R.id.privacy);
        CardView card9=findViewById(R.id.viewappointmnet);
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(doctordashboard.this,i_map.class);
                startActivity(i);
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(doctordashboard.this,viewappointment.class);
                startActivity(i);
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(doctordashboard.this,viewreport.class);
                startActivity(i);
            }
        });
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(doctordashboard.this,emergencycall.class);
                startActivity(i);
            }
        });
        card6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(doctordashboard.this,calloptions.class);
                startActivity(i);
            }
        });
        card7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(doctordashboard.this,editprofilea.class);
                startActivity(i);
            }
        });
        card8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(doctordashboard.this,privacyandpolicya.class);
                startActivity(i);
            }
        });
        card9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(doctordashboard.this,viewappointment.class);
                startActivity(i);
            }
        });
    }
}