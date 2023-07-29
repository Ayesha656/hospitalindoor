package com.example.hospitalindoor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class appointmenta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointmenta);
        CardView card1,card2,card3,card4,card5,card6,card7,card8,card9;
        card1=(CardView) findViewById(R.id.heart1);
        card2=(CardView) findViewById(R.id.lung);
        card3=(CardView) findViewById(R.id.dental);
        card4=(CardView) findViewById(R.id.neuro);
        card5=(CardView) findViewById(R.id.brain);
        card6=(CardView) findViewById(R.id.doctor1);
        card7=(CardView) findViewById(R.id.doctor2);
        card8=(CardView) findViewById(R.id.doctor3);
        card9=(CardView) findViewById(R.id.doctor4);
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(appointmenta.this,appointmentb.class);
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(appointmenta.this,appointmentb.class);
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(appointmenta.this,appointmentb.class);
            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(appointmenta.this,appointmentb.class);
            }
        });
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(appointmenta.this,appointmentb.class);
            }
        });
        card6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(appointmenta.this,appointmentb.class);
            }
        });
        card7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(appointmenta.this,appointmentb.class);
            }
        });
        card8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(appointmenta.this,appointmentb.class);
            }
        });
        card9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(appointmenta.this,appointmentb.class);
            }
        });


    }
}