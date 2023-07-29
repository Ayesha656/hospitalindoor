package com.example.hospitalindoor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class admindashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admindashboard);
        CardView a = findViewById(R.id.appointmnent6);
        CardView b = findViewById(R.id.appointmnent7);
        CardView c = findViewById(R.id.staff_register);
        CardView d= findViewById(R.id.helpline2);
        CardView e= findViewById(R.id.staff_remove);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admindashboard.this,adminappointment.class);
                startActivity(intent);
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admindashboard.this,adminacceptappoint.class);
                startActivity(intent);
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admindashboard.this,admindoctorregister.class);
                startActivity(intent);
            }
        });
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admindashboard.this,emergencycall.class);
                startActivity(intent);
            }
        });
        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admindashboard.this,admindoctorremove.class);
                startActivity(intent);
            }
        });
    }
}