package com.example.hospitalindoor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class adminshiftappointment extends AppCompatActivity {
CalendarView cal;
TextView tx;
    Button b;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Map<String, Object> opt = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminshiftappointment);


        Bundle bundle = getIntent().getExtras();
        String x=getIntent().getStringExtra("doctoname");
        String y=getIntent().getStringExtra("name");
        String z=getIntent().getStringExtra("status");

        cal=findViewById(R.id.calendar);
        tx=findViewById(R.id.tex);
        Button b= findViewById(R.id.confirm_shift);
        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                String date=dayOfMonth+"/"+month+"/"+year;
                tx.setText(date);
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
                String cal_dar=tx.getText().toString();
                System.out.println(cal_dar);
                System.out.println(x);
                System.out.println(y);
                System.out.println(z);
                if(cal_dar==null){
                    Toast.makeText(adminshiftappointment.this, "please shift the date", Toast.LENGTH_SHORT).show();
                }
                else{
                    changed_date_appointment(x,y,z,cal_dar);
                }
            }
        });
    }

    private void changed_date_appointment(String x, String y, String z, String cal_dar) {
        Map<String,Object> userDetail=new HashMap<>();
        userDetail.put("doctorname",x);
        userDetail.put("name",y);
        userDetail.put("status","shifted");
        userDetail.put("date",cal_dar);
        db.collection("appointment").whereEqualTo("name",y)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful() && !task.getResult().isEmpty()) {

                    DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                    String documentID = documentSnapshot.getId();
                    db.collection("appointment")
                            .document(documentID)
                            .update(userDetail)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(adminshiftappointment.this, "Data Updated Successfully", Toast.LENGTH_SHORT);
                                    Intent b = new Intent(adminshiftappointment.this,admindashboard.class);
                                    startActivity(b);
                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(adminshiftappointment.this, "Some Error Occured", Toast.LENGTH_SHORT);

                        }
                    });


                }
                else{
                    Toast.makeText(adminshiftappointment.this,"Failed",Toast.LENGTH_SHORT);

                }
            }
        });

    }
}