package com.example.hospitalindoor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class patientreport extends AppCompatActivity {
    FirebaseStorage storage = FirebaseStorage.getInstance();
    private final int Choose_PDF_FILE=1001;
    private  static final String Tag="patientreport";
    private Button choose_File;
    private TextView path;
    String url;
    Uri imageUri= null;
    String y;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Map<String, Object> user = new HashMap<>();

    String doctor_uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patientreport);
        choose_File=findViewById(R.id.upload);
        path=findViewById(R.id.filepath);
        Bundle b= getIntent().getExtras();
         y= getIntent().getStringExtra("uid");


      Spinner spinner=(Spinner) findViewById(R.id.dcotor_na) ;
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.doctorname, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        String g=spinner.getSelectedItem().toString();
        checkdocotoruri(g);


        choose_File.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choose_pdf_file_from_device();
            }
        });

    }

    private void checkdocotoruri(String g) {
        if(g.equals("Dr Faisal")){
            doctor_uri="W257EvLVeoNoEUAtr1l1GL429O92";
        }
        else if(g.equals("Dr Moin")){
            doctor_uri="TXiEFNhDLxUYOXAJtnzkDLyi6R53";
        }

        else if(g.equals("DR.YAWER SAEED")){
            doctor_uri="sQEV3heSS4dkrFHf3ptfFvqfg3N2";
        }
    }

    private  void choose_pdf_file_from_device(){
        Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("application/pdf");
        startActivityForResult(i,Choose_PDF_FILE);

    }
    public void onActivityResult(int requestCode,int resultCode,Intent resultdata) {

        super.onActivityResult(requestCode, resultCode, resultdata);
        if(requestCode== Choose_PDF_FILE && resultCode== Activity.RESULT_OK){
            if(resultdata!= null){
                Log.d(Tag,"on activity result"+ resultdata.getData());
               path.setText("File path"+resultdata.getData());
               imageUri=resultdata.getData();
               final String timestamp = " "+ System.currentTimeMillis();
                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                final String messagePushID = timestamp;
                final StorageReference filepath = storageReference.child(messagePushID + "." + "pdf");
                filepath.putFile(imageUri).continueWithTask(new Continuation() {
                    @Override
                    public Object then(@NonNull Task task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }
               url=filepath.getDownloadUrl().toString();
                        System.out.println("url"+url);
                        return filepath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            // After uploading is done it progress
                            // dialog box will be dismissed

                            Uri uri = task.getResult();
                            String myurl;
                            myurl = uri.toString();
                          //uploaded file url
                            System.out.println(myurl);
                            Toast.makeText(getApplicationContext(), "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                            getintotables(myurl,y,doctor_uri);
                        } else {
                      //      dialog.dismiss();
                            Toast.makeText(getApplicationContext(), "UploadedFailed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        }
    }

    private void getintotables(String myurl, String y, String doctor_uri) {
        user.put("reporturl",myurl);
        user.put("uid",y);
        user.put("doctoruid",doctor_uri);
        db.collection("reports")
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
    }
}


