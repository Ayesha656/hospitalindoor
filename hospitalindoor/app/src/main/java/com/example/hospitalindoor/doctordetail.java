package com.example.hospitalindoor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class doctordetail extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<doc_detail> userArraylsit;
    Myadapter myadapter;
    FirebaseFirestore db;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctordetail);
        progressDialog= new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data... ");
        progressDialog.show();

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        db=FirebaseFirestore.getInstance();
        userArraylsit= new ArrayList<doc_detail>();
        myadapter= new Myadapter(doctordetail.this,userArraylsit);
        recyclerView.setAdapter(myadapter);
        EventChangeListner();

    }

    private void EventChangeListner() {
   db.collection("doctordetail").orderBy("name", Query.Direction.ASCENDING)
           .addSnapshotListener(new EventListener<QuerySnapshot>() {
               @Override
               public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                   if (error !=null){
                       if(progressDialog.isShowing())
                           progressDialog.dismiss();
                       Log.e("Firestore Error",error.getMessage());
                       return;
                   }
                   for (DocumentChange dc: value.getDocumentChanges()){
                       if(dc.getType()==DocumentChange.Type.ADDED){
                           userArraylsit.add(dc.getDocument().toObject(doc_detail.class));
                       }
                       myadapter.notifyDataSetChanged();
                       if(progressDialog.isShowing())
                           progressDialog.dismiss();

                   }

               }
           });
    }


}