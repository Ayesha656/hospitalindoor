package com.example.hospitalindoor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class otpa extends AppCompatActivity {
Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpa);
        btn1=(Button) findViewById(R.id.otp);
        TextView otp_1=findViewById(R.id.ot);
        Bundle bundle=null;
        bundle=getIntent().getExtras();
        if(bundle!=null) {
            String uphone = bundle.getString("phone");
            otp_1.setText("An OTP will be Send to:" + uphone);
        }
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent i = new Intent(otpa.this, otpb.class);
                    startActivity(i);
                    finish();
                }

        });

    }
}