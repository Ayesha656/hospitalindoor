package com.example.hospitalindoor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class optionselection extends AppCompatActivity {
Button btn1,btn2,btn3,btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optionselection);
        btn1=(Button) findViewById(R.id.patient);
        btn2=(Button) findViewById(R.id.doctor);
        btn3=(Button) findViewById(R.id.admin);
        btn4=(Button) findViewById(R.id.visitor);
btn1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i = new Intent(optionselection.this,login.class);
        startActivity(i);
    }
});
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(optionselection.this,doctorlogin.class);
                startActivity(i);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(optionselection.this,admin_login.class);
                startActivity(i);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(optionselection.this,visitor.class);
                startActivity(i);
            }
        });
    }
}