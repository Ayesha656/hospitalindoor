package com.example.hospitalindoor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Signup extends AppCompatActivity {
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
   FirebaseFirestore db = FirebaseFirestore.getInstance();
    Map<String, Object> user = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Button btn1 = findViewById(R.id.signupbtn2);
        EditText ed1 = findViewById(R.id.a_user);
        EditText ed2 = findViewById(R.id.a_email);
        EditText ed3 = findViewById(R.id.a_password);
        EditText ed4 = findViewById(R.id.a_password2);
        EditText ed5 = findViewById(R.id.a_cnic);
        EditText ed6 = findViewById(R.id.a_phone);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = ed1.getText().toString();
                String email = ed2.getText().toString();
                String password = ed3.getText().toString();
                String repassword = ed4.getText().toString();
                String u_cnic = ed5.getText().toString();
                String u_phone = ed6.getText().toString();
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(repassword) || TextUtils.isEmpty(u_cnic) || TextUtils.isEmpty(u_phone) ||
                        TextUtils.isEmpty(username) && TextUtils.isEmpty(email) && TextUtils.isEmpty(password) && TextUtils.isEmpty(repassword) && TextUtils.isEmpty(u_cnic) && TextUtils.isEmpty(u_phone)) {
                    Toast.makeText(getApplicationContext(), "Enter the above required field", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 8 && repassword.length() < 8) {
                    Toast.makeText(getApplicationContext(), "Length should be greater than 8 ", Toast.LENGTH_SHORT).show();
                }

                else if (!TextUtils.isEmpty(password) && !TextUtils.isEmpty(repassword) && password.equals(repassword)) {
                    if (u_cnic.length()==13) {
                        if (email.trim().matches(emailPattern)) {
                            if (u_phone.length() == 11) {
                                user.put("name",username);
                                user.put("email",email);
                                user.put("cnic",u_cnic);
                                user.put("phone",u_phone);
                                db.collection("users")
                                        .add(user)
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
                                Intent i = new Intent(Signup.this, home.class);
                                i.putExtra("phone",u_phone);
                                startActivity(i);
                                finish();
                            }
                            else{
                                Toast.makeText(getApplicationContext()," Invalid phone number",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext()," Invalid email address",Toast.LENGTH_SHORT).show();
                    }
                    }
                    else{


                            Toast.makeText(getApplicationContext(), "Check your cnic", Toast.LENGTH_SHORT).show();

                    }
                }




            }
        });


    }}