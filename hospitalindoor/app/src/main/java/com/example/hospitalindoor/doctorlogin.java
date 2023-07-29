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

import java.util.regex.Pattern;

public class doctorlogin extends AppCompatActivity {
    FirebaseAuth auth =  FirebaseAuth.getInstance();

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
        setContentView(R.layout.activity_doctorlogin);
        Button d_log_in=findViewById(R.id.d_login);
        TextView d_fog= findViewById(R.id.d_forgotpassword);
        EditText doc_name=findViewById(R.id.d_username);
        EditText doc_pass=findViewById(R.id.d_password);
        d_log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String d_name= doc_name.getText().toString();
                String d_pass= doc_pass.getText().toString();
                if (TextUtils.isEmpty(d_name)|| TextUtils.isEmpty(d_pass)||TextUtils.isEmpty(d_name) && TextUtils.isEmpty(d_pass) ) {
                    Toast.makeText(getApplicationContext(), "Enter the above required feild", Toast.LENGTH_SHORT).show();
                }
                else if (!PASSWORD_PATTERN.matcher(d_pass).matches()) {
                    doc_pass.setError("Password is too weak");
                    Toast.makeText(getApplicationContext(), "Enter the above required feild", Toast.LENGTH_SHORT).show();

                }

                else{
                    auth.signInWithEmailAndPassword(d_name,d_pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            String uid;
                            uid = auth.getUid();
                            Toast.makeText(getApplicationContext(), "Login Success 1", Toast.LENGTH_SHORT).show();

                            db.collection("doctordetail").whereEqualTo("uid",uid).get().addOnCompleteListener(new
                       OnCompleteListener<QuerySnapshot>() {
                                                                  @Override
                                                      public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()){

                                QuerySnapshot document = task.getResult();
                                if(document.isEmpty()){
                                    Toast.makeText(getApplicationContext(), "Data not Found", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(getApplicationContext(), "Data Found", Toast.LENGTH_SHORT).show();
             Intent i = new Intent(doctorlogin.this,doctordashboard.class);
             startActivity(i);
             finish();

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
        });
        d_fog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(doctorlogin.this,forgotpassword.class);
                startActivity(i);
                finish();
            }
        });
    }
}