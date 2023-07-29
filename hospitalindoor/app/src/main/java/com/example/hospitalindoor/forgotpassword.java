package com.example.hospitalindoor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class forgotpassword extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{8,}" +                // at least 8 characters
                    "$");
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private FirebaseAuth auth;

   private EditText txtmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        Button pass_update=findViewById(R.id.f_update);
        txtmail=findViewById(R.id.f_email);
        auth= FirebaseAuth.getInstance();

        pass_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               validateData();
            }
        });
    }

    private void validateData() {
          String email=txtmail.getText().toString();
        if (TextUtils.isEmpty(email)){
            txtmail.setError("Required");
        }
        else{
            auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(forgotpassword.this, "Check Your Email", Toast.LENGTH_SHORT).show();
                        Intent v= new Intent(forgotpassword.this,optionselection.class);
                        startActivity(v);
                        finish();
                    }
                }
            });
        }
    }




}