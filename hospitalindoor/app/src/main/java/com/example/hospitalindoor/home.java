package com.example.hospitalindoor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        CardView card1= findViewById(R.id.map);
        CardView card2=findViewById(R.id.doctorlist2);
        CardView card3=findViewById(R.id.report);
        CardView card4= findViewById(R.id.appointmnent);
        CardView card5= findViewById(R.id.emergency2);
        CardView card6= findViewById(R.id.helpline);
        CardView card7= findViewById(R.id.editprofile);
        CardView card8= findViewById(R.id.privacyandpolicy);
        CardView card9= findViewById(R.id.review);
        CardView card10= findViewById(R.id.appointmnentcon);

        Bundle bun= getIntent().getExtras();
        String x=getIntent().getStringExtra("uid");
        System.out.println(x);
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(home.this,i_map.class);
                startActivity(i);
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(home.this,doctordetail.class);
                startActivity(i);
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(home.this,patientreport.class);
                i.putExtra("uid",x);
                startActivity(i);
            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(home.this,appointmentb.class);
                i.putExtra("uid",x);
                startActivity(i);
            }
        });
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(home.this,emergencycall.class);
                startActivity(i);
            }
        });
        card6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(home.this,calloptions.class);
                startActivity(i);
            }
        });
        card7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(home.this,editprofilea.class);
                startActivity(i);
            }
        });
        card8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(home.this,privacyandpolicya.class);
                startActivity(i);
            }
        });

        card9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(home.this,viewreport2.class);
                i.putExtra("uid",x);
                startActivity(i);
            }
        });
        card10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(home.this,user_appointment_notification.class);
                i.putExtra("uid",x);
                startActivity(i);
            }
        });
    }
}