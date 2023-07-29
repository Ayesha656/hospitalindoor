package com.example.hospitalindoor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.*;
import java.util.Map;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class appointmentb extends AppCompatActivity {
CalendarView cal;
TextView t;
Button b;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Map<String, Object> opt = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointmentb);
        cal=findViewById(R.id.calendar);
        t=findViewById(R.id.txt2);
        EditText user=findViewById(R.id.username2);
        EditText doc=findViewById(R.id.doctorname);
        Button b=findViewById(R.id.request);
        Bundle bun=getIntent().getExtras();
        String uid=getIntent().getStringExtra("uid");

        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                String date=dayOfMonth+"/"+month+"/"+year;
                t.setText(date);
                Context context = getApplicationContext();
                CharSequence text = "Date Has Been Changed!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String us= user.getText().toString();
                String doctor= doc.getText().toString();
                String cal_dar=t.getText().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                if(TextUtils.isEmpty(us) || TextUtils.isEmpty(doctor) || TextUtils.isEmpty(us) && TextUtils.isEmpty(doctor)){
                    Toast.makeText(getApplicationContext(), "Enter Correct Values for Appointment", Toast.LENGTH_SHORT).show();
                }
                else{
                    opt.put("name",us);
                    opt.put("doctorname",doctor);
                  opt.put("date",cal_dar);
                  opt.put("status","waiting");
                  opt.put("uid",uid);
                    db.collection("appointment")
                            .add(opt)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d( "aa", "DocumentSnapshot added with ID: " + documentReference.getId());
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("kssks","Error adding document", e);
                                }
                            });
                    Intent i = new Intent(appointmentb.this,waitingappointment.class);
                    startActivity(i);
                    finish();
                }
            }
        });

    }
}