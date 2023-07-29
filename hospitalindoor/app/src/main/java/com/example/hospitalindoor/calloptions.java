package com.example.hospitalindoor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class calloptions extends AppCompatActivity {
Button btn1,btn2,btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calloptions);
        btn1=(Button) findViewById(R.id.appointmnent);
        btn2=(Button) findViewById(R.id.emergency);
        btn3=(Button) findViewById(R.id.complain);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone="123456";
                String x="tel:"+phone;
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse(x));
                startActivity(i);

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone="456789";
                String x="tel:"+phone;
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse(x));
                startActivity(i);

            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone="789456";
                String x="tel:"+phone;
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse(x));
                startActivity(i);

            }
        });
    }
}