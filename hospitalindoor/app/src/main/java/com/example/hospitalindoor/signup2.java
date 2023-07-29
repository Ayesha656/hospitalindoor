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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup2 extends AppCompatActivity {
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);
        Button x=findViewById(R.id.signup2btn);
        EditText s_uname=findViewById(R.id.signusername);
        EditText s_pass=findViewById(R.id.signpassword);
        auth= FirebaseAuth.getInstance();
        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname2= s_uname.getText().toString();
                String pass3= s_pass.getText().toString();
                if (TextUtils.isEmpty(uname2)|| TextUtils.isEmpty(pass3)||TextUtils.isEmpty(uname2) && TextUtils.isEmpty(pass3) ) {
                    Toast.makeText(getApplicationContext(), "Enter the above required field", Toast.LENGTH_SHORT).show();
                }

                else{
                    registeruser(uname2,pass3);

                }

            }
        });

    }


    private void registeruser(String uname2, String pass3) {
        auth.createUserWithEmailAndPassword(uname2,pass3).addOnCompleteListener(signup2.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent i = new Intent(signup2.this,Signup.class);
                    i.putExtra("email",uname2);
                    i.putExtra("pwd",pass3);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), " login Failed ", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    }
