package com.example.hospitalindoor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class admindoctorremove extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Map<String, Object> user = new HashMap<>();
    EditText ed1,ed2,ed3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admindoctorremove);
        Button removebutton = findViewById(R.id.remove);
         ed1 = findViewById(R.id.r_name);
         ed2 = findViewById(R.id.r_email);
         ed3 = findViewById(R.id.r_cnic);
        db= FirebaseFirestore.getInstance();
        removebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  r1=ed1.getText().toString();
                String r2= ed2.getText().toString();
                String r3= ed3.getText().toString();

                if(TextUtils.isEmpty(r1)||TextUtils.isEmpty(r2)|| TextUtils.isEmpty(r3) || TextUtils.isEmpty(r1) && TextUtils.isEmpty(r2) && TextUtils.isEmpty(r3)){
                    Toast.makeText(getApplicationContext(), "please enter the above feilds", Toast.LENGTH_SHORT).show();
                }
                else{
                    ed1.setText("");
                    ed2.setText("");
                    ed3.setText("");
                    admindoctorremove(r1,r2,r3);

                }
            }
        });


    }

    private void admindoctorremove( String r1,String r2,String r3) {
        db.collection("doctordetail")
                .whereEqualTo("name",r1) .whereEqualTo("Email",r2).whereEqualTo("cnic",r3)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful() && !task.getResult().isEmpty()){
                    DocumentSnapshot documentSnapshot=task.getResult().getDocuments().get(0);
                    String DocumentId= documentSnapshot.getId();
                    db.collection("doctordetail")
                            .document(DocumentId)
                            .delete()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(admindoctorremove.this,"Doctor Removed Successfully",Toast.LENGTH_SHORT).show();

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(admindoctorremove.this,"Failed! Please Try Again",Toast.LENGTH_SHORT).show();

                                }
                            });
                }
                else{
                    Toast.makeText(admindoctorremove.this,"Sorry! Not Working!",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}