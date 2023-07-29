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

public class editprofilea extends AppCompatActivity {

    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofilea);

        EditText e_user=findViewById(R.id.e_username);
        EditText e_ema=findViewById(R.id.e_email);
        EditText e_pass=findViewById(R.id.e_password);
        EditText e_cn=findViewById(R.id.e_cnic);
        EditText e_phone=findViewById(R.id.e_number);
       Button bt=findViewById(R.id.save);
       db=FirebaseFirestore.getInstance();

       bt.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String e_us=e_user.getText().toString();
               String e_mail=e_ema.getText().toString();
               String e_pas1=e_pass.getText().toString();
               String e_cni=e_cn.getText().toString();
               String e_ph=e_phone.getText().toString();

               if(TextUtils.isEmpty(e_us)||TextUtils.isEmpty(e_mail)||TextUtils.isEmpty(e_pas1)||TextUtils.isEmpty(e_cni)||TextUtils.isEmpty(e_ph)||
                       TextUtils.isEmpty(e_us) && TextUtils.isEmpty(e_mail) && TextUtils.isEmpty(e_pas1) && TextUtils.isEmpty(e_cni) && TextUtils.isEmpty(e_ph)
               ){
                   Toast.makeText(getApplicationContext(), "Enter the above required feild", Toast.LENGTH_SHORT).show();
               }
               else{
                   UpdateData(e_us,e_mail,e_cni,e_ph);
                   Intent i = new Intent(editprofilea.this,signupsuccessful.class);
                   startActivity(i);

               }
           }
       });
    }

    private void UpdateData(String e_us, String e_mail, String e_cni, String e_ph) {
        Map<String,Object> userDetail=new HashMap<>();
        userDetail.put("cnic",e_cni);
        userDetail.put("email",e_mail);
        userDetail.put("name",e_us);
        userDetail.put("phone",e_ph);
        db.collection("users")
                .whereEqualTo("email",e_mail)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful() && !task.getResult().isEmpty())
                {
                    DocumentSnapshot documentSnapshot=task.getResult().getDocuments().get(0);
                    String documentID=documentSnapshot.getId();
                    db.collection("users")
                            .document(documentID)
                            .update(userDetail)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(editprofilea.this,"Data Updated Successfully",Toast.LENGTH_SHORT);

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(editprofilea.this,"Some Error Occured",Toast.LENGTH_SHORT);

                        }
                    });

                }
                else{
                    Toast.makeText(editprofilea.this,"Failed",Toast.LENGTH_SHORT);

                }
            }
        });
    }
}