package com.example.hospitalindoor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

public class acceptorrejectappointment extends AppCompatActivity {
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceptorrejectappointment);
        Button f =findViewById(R.id.accept);
        Button g =findViewById(R.id.shift);
        Button h =findViewById(R.id.reject);
        Intent i = new Intent();
        Bundle bundle = getIntent().getExtras();
        String x=getIntent().getStringExtra("doctoname");
        String y=getIntent().getStringExtra("name");
        String z=getIntent().getStringExtra("status");
        String w=getIntent().getStringExtra("dat");
        db=FirebaseFirestore.getInstance();
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appointment_is_accepted( x, y, z, w);

            }
        });
        g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(acceptorrejectappointment.this,adminshiftappointment.class);
                i.putExtra("doctoname",x);
                i.putExtra("name",y);
                i.putExtra("stat","shifted");
                startActivity(i);

            }
        });
        h.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                appointment_is_rejected( x, y, z, w);
            }
        });

    }



    private void appointment_is_rejected(String x, String y, String z, String w) {
        Map<String,Object> userDetail=new HashMap<>();
        userDetail.put("doctorname",x);
        userDetail.put("name",y);
        userDetail.put("status","rejected");
        userDetail.put("date",w);
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
                                    Toast.makeText(acceptorrejectappointment.this, "Data Updated Successfully", Toast.LENGTH_SHORT);
                                    Intent b = new Intent(acceptorrejectappointment.this,adminrejectappoint.class);
                                    startActivity(b);
                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(acceptorrejectappointment.this, "Some Error Occured", Toast.LENGTH_SHORT);

                        }
                    });


                }
                else{
                    Toast.makeText(acceptorrejectappointment.this,"Failed",Toast.LENGTH_SHORT);

                }
            }
        });

    }

    private void appointment_is_accepted(String x,String y,String z,String w) {
        Map<String,Object> userDetail=new HashMap<>();
        userDetail.put("doctorname",x);
        userDetail.put("name",y);
        userDetail.put("status","confirmed");
        userDetail.put("date",w);
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
                                    Toast.makeText(acceptorrejectappointment.this, "Data Updated Successfully", Toast.LENGTH_SHORT);
                                    Intent b = new Intent(acceptorrejectappointment.this, adminacceptappoin.class);
                                    startActivity(b);
                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(acceptorrejectappointment.this, "Some Error Occured", Toast.LENGTH_SHORT);

                        }
                    });


                }
                else{
                    Toast.makeText(acceptorrejectappointment.this,"Failed",Toast.LENGTH_SHORT);

                }
            }
        });
    }
}