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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class reviewbydoctor extends AppCompatActivity {
    String p_url;
    FirebaseFirestore db= FirebaseFirestore.getInstance();
    Map<String, Object> user = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviewbydoctor);
        EditText givereview=findViewById(R.id.giveitreview);
        Button sendrev=findViewById(R.id.sendreview);

        Bundle bun= getIntent().getExtras();

        sendrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String v=givereview.getText().toString();
                System.out.println(v);
                p_url=getIntent().getStringExtra("u_uid");
                System.out.println(p_url);
                if(TextUtils.isEmpty(v)){
                    Toast.makeText(reviewbydoctor.this, "Please Send a Review ", Toast.LENGTH_SHORT).show();
                }
                else{
                    user.put("review",v);
                    db.collection("reports")
                            .whereEqualTo("uid",p_url)
                            .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful() && !task.getResult().isEmpty())
                            {
                                DocumentSnapshot documentSnapshot=task.getResult().getDocuments().get(0);
                                String documentID=documentSnapshot.getId();
                                db.collection("reports")
                                        .document(documentID)
                                        .update(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(reviewbydoctor.this,"Data Updated Successfully",Toast.LENGTH_SHORT);

                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(reviewbydoctor.this,"Some Error Occured",Toast.LENGTH_SHORT);

                                    }
                                });

                            }
                            else{
                                Toast.makeText(reviewbydoctor.this,"Failed",Toast.LENGTH_SHORT);

                            }
                        }
                    });




                }
            }
        });
    }
}