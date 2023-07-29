package com.example.hospitalindoor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class adminremovedoctor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminremovedoctor);
        Button d = findViewById(R.id.removeaacount);
        EditText ed1 = findViewById(R.id.removeusername);
        EditText ed2 = findViewById(R.id.removeemail);
        EditText ed3 = findViewById(R.id.specialization);
    d.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(TextUtils.isEmpty(ed1.getText().toString()) || TextUtils.isEmpty(ed2.getText().toString())||
                    TextUtils.isEmpty(ed3.getText().toString()) ||
                    TextUtils.isEmpty(ed1.getText().toString()) && TextUtils.isEmpty(ed2.getText().toString()) && TextUtils.isEmpty(ed3.getText().toString())){
                Toast.makeText(getApplicationContext(), "Enter all Feilds", Toast.LENGTH_SHORT).show();


            }
            else{
                Intent i = new Intent(adminremovedoctor.this,admindashboard.class);
                startActivity(i);
            }
        }
    });

    }}