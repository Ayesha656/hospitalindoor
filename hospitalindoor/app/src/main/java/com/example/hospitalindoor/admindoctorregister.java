package com.example.hospitalindoor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class admindoctorregister extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    Map<String, Object> adddoc = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admindoctorregister);

        EditText ed1=findViewById(R.id.admin_name);
        EditText ed2=findViewById(R.id.admin_email);
        EditText ed3=findViewById(R.id.admin_password);
        EditText ed4=findViewById(R.id.admin_cnic);
        EditText ed5=findViewById(R.id.admin_phone);
        EditText ed6=findViewById(R.id.admin_salary);
        EditText ed7=findViewById(R.id.admin_special);
        EditText ed8=findViewById(R.id.admin_day);
        EditText ed9=findViewById(R.id.admin_time);
        EditText ed10=findViewById(R.id.admin_exten);
        Button btn=findViewById(R.id.signupbtn2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String doctorname = ed1.getText().toString();
                String doctoremail = ed2.getText().toString();
                String doctorpassword = ed3.getText().toString();
                String doctorcnic = ed4.getText().toString();
                String doctorphone = ed5.getText().toString();
                String doctorsalary = ed6.getText().toString();
                String doctorspeciality = ed7.getText().toString();
                String doctortime = ed8.getText().toString();
                String doctorday = ed9.getText().toString();
                String doctorextension = ed10.getText().toString();
                if(TextUtils.isEmpty(ed1.getText().toString()) ||TextUtils.isEmpty(ed2.getText().toString()) || TextUtils.isEmpty(ed3.getText().toString())||
                        TextUtils.isEmpty(ed4.getText().toString()) || TextUtils.isEmpty(ed5.getText().toString()) ||TextUtils.isEmpty(ed6.getText().toString()) ||
                        TextUtils.isEmpty(ed7.getText().toString()) ||  TextUtils.isEmpty(ed8.getText().toString()) ||  TextUtils.isEmpty(ed8.getText().toString())||TextUtils.isEmpty(ed10.getText().toString()) || TextUtils.isEmpty( ed1.getText().toString()) && TextUtils.isEmpty(ed2.getText().toString()) && TextUtils.isEmpty(ed3.getText().toString()) &&
                        TextUtils.isEmpty(ed4.getText().toString()) && TextUtils.isEmpty(ed5.getText().toString()) &&TextUtils.isEmpty(ed6.getText().toString()) &&
                        TextUtils.isEmpty(ed7.getText().toString()) && TextUtils.isEmpty(ed8.getText().toString()) &&   TextUtils.isEmpty(ed9.getText().toString()) &&   TextUtils.isEmpty(ed10.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Enter the above required field", Toast.LENGTH_SHORT).show();

                }
                if(ed3.getText().toString().length()<8){
                    Toast.makeText(getApplicationContext(), "Length of Password is short", Toast.LENGTH_SHORT).show();
                }
                if(ed6.getText().toString().length()<3){
                    Toast.makeText(getApplicationContext(), "Salary is less than 9999", Toast.LENGTH_SHORT).show();
                }
                else{

                    auth.createUserWithEmailAndPassword(doctoremail,doctorpassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            String uid;
                            uid = auth.getUid();
                            System.out.println("Successful  "+uid);

                            adddoc.put("uid",uid);
                            adddoc.put("name",doctorname);
                            adddoc.put("Email",doctoremail);
                            adddoc.put("password",doctorpassword);
                            adddoc.put("cnic",doctorcnic);
                            adddoc.put("phone",doctorphone);
                            adddoc.put("salary",doctorsalary);
                            adddoc.put("Specialization",doctorspeciality);
                            adddoc.put("Time",doctortime);
                            adddoc.put("Day",doctorday);
                            adddoc.put("Extension",doctorextension);
                            db.collection("doctordetail")
                                    .add(adddoc)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            Log.d( "dd", "DocumentSnapshot added with ID: " + documentReference.getId());
                                            Toast.makeText(getApplicationContext(), "Doctor Added Successfully!", Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(admindoctorregister.this, admindashboard.class);
                                            startActivity(i);
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w("mb","Error adding document", e);
                                        }
                                    });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            System.out.println("error "+e.toString());
                        }
                    });


                }
            }
        });


    }
}