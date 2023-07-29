package com.example.hospitalindoor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class login extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{8,}" +                // at least 8 characters
                    "$");
    private FirebaseAuth auth;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       Button x=findViewById(R.id.adminin);
        Button y=findViewById(R.id.signupbtn);
        TextView fog= findViewById(R.id.forgotpassword);
        EditText uname=findViewById(R.id.username);
        EditText pass=findViewById(R.id.password);
        auth= FirebaseAuth.getInstance();
        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname1= uname.getText().toString();
                String pass2= pass.getText().toString();
                if (TextUtils.isEmpty(uname1)|| TextUtils.isEmpty(pass2)||TextUtils.isEmpty(uname1) && TextUtils.isEmpty(pass2) ) {
                    Toast.makeText(getApplicationContext(), "Enter the above required feild", Toast.LENGTH_SHORT).show();
                }
                else if (!PASSWORD_PATTERN.matcher(pass2).matches()) {
                    pass.setError("Password is too weak");
                }

                else{
                    loginuser(uname1,pass2);

                }

            }
        });
        y.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent c= new Intent(login.this,signup2.class);
                startActivity(c);
            }
        });
        fog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent c= new Intent(login.this,forgotpassword.class);
                startActivity(c);
            }
        });


    }


    private void loginuser(String uname1, String pass2) {
        auth.signInWithEmailAndPassword(uname1,pass2).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                String uid;
                uid = auth.getUid();
                Toast.makeText(getApplicationContext(), "Login Success 1", Toast.LENGTH_SHORT).show();

                db.collection("users").whereEqualTo("uid",uid).get().addOnCompleteListener(new
                                                                                                          OnCompleteListener<QuerySnapshot>() {
                                                                                                              @Override
                                                                                                              public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                                                                                  if (task.isSuccessful()){

                                                                                                                      QuerySnapshot document = task.getResult();
                                                                                                                      if(document.isEmpty()){
                                                                                                                          Toast.makeText(getApplicationContext(), "Data not Found", Toast.LENGTH_SHORT).show();
                                                                                                                      }else{
                                                                                                                          Toast.makeText(getApplicationContext(), "Data Found", Toast.LENGTH_SHORT).show();
                                                                                                                          Intent i = new Intent(login.this,home.class);
                                                                                                                          i.putExtra("uid",uid);
                                                                                                                          startActivity(i);
                                                                                                                          finish();
                                                                                                                          System.out.println(uid);

                                                                                                                      }

                                                                                                                  }else{
                                                                                                                      Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_SHORT).show();

                                                                                                                  }
                                                                                                              }
                                                                                                          });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "error : "+e.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        }
    }