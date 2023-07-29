package com.example.hospitalindoor;

import static android.content.Context.DOWNLOAD_SERVICE;
import static android.os.Environment.DIRECTORY_DOWNLOADS;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class Myadapter4 extends RecyclerView.Adapter<Myadapter4.MyViewHolder> {
    Context context;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;

    public Myadapter4(Context context, ArrayList<report_detail> userArraylist4) {
        this.context = context;
        this.userArraylist4 = userArraylist4;
    }

    ArrayList<report_detail> userArraylist4;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item6,parent,false);
        return new Myadapter4.MyViewHolder(v);

    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        report_detail dot=userArraylist4.get(position);
        holder.a_apointment_link.setText(dot.reporturl);
        holder.us_uid.setText(dot.uid);
        holder.reviewlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),reviewbydoctor.class);
              //  i.putExtra("url",dot.reporturl);
                i.putExtra("u_uid",dot.uid);
                view.getContext().startActivity(i);
            }
        });
    }





    @Override
    public int getItemCount() {
        return  userArraylist4.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView a_apointment_link,us_uid;
        Button reviewlink;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            a_apointment_link=itemView.findViewById(R.id.a_url);
            us_uid=itemView.findViewById(R.id.a_uid);
            reviewlink=itemView.findViewById(R.id.rev);
        }
    }
}
