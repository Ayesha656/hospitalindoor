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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.regex.Pattern;

public class admin_login extends AppCompatActivity {
    private FirebaseAuth auth;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{8,}" +                // at least 8 characters
                    "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        Button x=findViewById(R.id.login);
        TextView y=findViewById(R.id.adminforgotpassword);
        EditText ad_name=findViewById(R.id.adminname);
        EditText ad_pass=findViewById(R.id.adminpwd);
        auth= FirebaseAuth.getInstance();
        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String b= ad_name.getText().toString();
                String c= ad_pass.getText().toString();
                if (TextUtils.isEmpty(b)|| TextUtils.isEmpty(c) ||TextUtils.isEmpty(b) && TextUtils.isEmpty(c)  ) {
                    Toast.makeText(getApplicationContext(), "Enter the above required field", Toast.LENGTH_SHORT).show();
                }

                else{
                    loginuser(b,c);

                }

            }
        });


        y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(admin_login.this,forgotpassword.class);
                startActivity(i);

            }
        });
    }

    private void loginuser(String b, String c) {
        auth.signInWithEmailAndPassword(b,c).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                String uid;
                uid = auth.getUid();
                Toast.makeText(getApplicationContext(), "Login Success 1", Toast.LENGTH_SHORT).show();

                db.collection("admin").whereEqualTo("uid",uid).get().addOnCompleteListener(new
                                                                                                   OnCompleteListener<QuerySnapshot>() {
                                                                                                       @Override
                                                                                                       public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                                                                           if (task.isSuccessful()){

                                                                                                               QuerySnapshot document = task.getResult();
                                                                                                               if(document.isEmpty()){
                                                                                                                   Toast.makeText(getApplicationContext(), "Data not Found", Toast.LENGTH_SHORT).show();
                                                                                                               }else{
                                                                                                                   Toast.makeText(getApplicationContext(), "Data Found", Toast.LENGTH_SHORT).show();
                                                                                                                   Intent i = new Intent(admin_login.this,admindashboard.class);
                                                                                                                   startActivity(i);

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
