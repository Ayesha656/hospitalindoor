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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class doctorprofileedit extends AppCompatActivity {
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorprofileedit);

        EditText d_user=findViewById(R.id.d_username);
        EditText d_ema=findViewById(R.id.d_email);
        EditText d_cn=findViewById(R.id.d_cnic);
        EditText d_phone=findViewById(R.id.d_number);
        Button button= findViewById(R.id.doc_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(d_user.getText().toString()) || TextUtils.isEmpty(d_ema.getText().toString()) || TextUtils.isEmpty(d_cn.getText().toString()) || TextUtils.isEmpty(d_phone.getText().toString()) ||
                        TextUtils.isEmpty(d_user.getText().toString()) && TextUtils.isEmpty(d_ema.getText().toString()) && TextUtils.isEmpty(d_cn.getText().toString()) && TextUtils.isEmpty(d_phone.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Enter the above required field", Toast.LENGTH_SHORT).show();
                }
                else{
                    UpdateData(d_user,d_ema,d_cn,d_phone);
                    Intent i = new Intent(doctorprofileedit.this,signupsuccessful.class);
                    startActivity(i);

                }
            }
        });
    }
    private void UpdateData(EditText d_user, EditText d_ema, EditText d_cn, EditText d_phone) {
        Map<String,Object> userDetail=new HashMap<>();
        userDetail.put("cnic",d_user);
        userDetail.put("email",d_ema);
        userDetail.put("name",d_cn);
        userDetail.put("phone",d_phone);
        db.collection("doctordetail")
                .whereEqualTo("email",d_ema)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful() && !task.getResult().isEmpty())
                {
                    DocumentSnapshot documentSnapshot=task.getResult().getDocuments().get(0);
                    String documentID=documentSnapshot.getId();
                    db.collection("doctordetail")
                            .document(documentID)
                            .update(userDetail)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(doctorprofileedit.this,"Data Updated Successfully",Toast.LENGTH_SHORT);

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(doctorprofileedit.this,"Some Error Occured",Toast.LENGTH_SHORT);

                        }
                    });

                }
                else{
                    Toast.makeText(doctorprofileedit.this,"Failed",Toast.LENGTH_SHORT);

                }
            }
        });
    }
}