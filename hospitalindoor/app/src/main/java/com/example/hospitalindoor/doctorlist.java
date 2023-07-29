package com.example.hospitalindoor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class doctorlist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorlist);
        ImageButton r1=findViewById(R.id.r_arrow1);
        ImageButton r2=findViewById(R.id.r_arrow2);
        ImageButton r3=findViewById(R.id.r_arrow3);
        ImageButton r4=findViewById(R.id.r_arrow4);
        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent u = new Intent(doctorlist.this,doctordetail.class);
                startActivity(u);
            }
        });
        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent u = new Intent(doctorlist.this,doctordetail.class);
                startActivity(u);
            }
        });
        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent u = new Intent(doctorlist.this,doctordetail.class);
                startActivity(u);
            }
        });
        r4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent u = new Intent(doctorlist.this,doctordetail.class);
                startActivity(u);
            }
        });
    }
}