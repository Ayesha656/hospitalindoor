package com.example.hospitalindoor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class emergencycall extends AppCompatActivity {
Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergencycall);
        btn1=(Button) findViewById(R.id.emergency3);
        btn2=(Button) findViewById(R.id.ambulance);
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
    }
}