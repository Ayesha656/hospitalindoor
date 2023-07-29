package com.example.hospitalindoor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Myadapter  extends RecyclerView.Adapter<Myadapter.MyViewHolder> {
    Context context;
    ArrayList<doc_detail> userArraylsit;

    public Myadapter(Context context, ArrayList<doc_detail> userArraylsit) {
        this.context = context;
        this.userArraylsit = userArraylsit;
    }

    @NonNull
    @Override
    public Myadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Myadapter.MyViewHolder holder, int position) {
        doc_detail doctor=userArraylsit.get(position);
        holder.firstname.setText(doctor.name);
        holder.f_doctor_spec.setText(doctor.Specialization);
        holder.f_doctor_ext.setText(doctor.Extension);
        holder.f_doct_day.setText(doctor.Day);
        holder.f_doc_time.setText(doctor.Time);
        holder.f_doc_email.setText(doctor.Email);
    }

    @Override
    public int getItemCount() {
        return userArraylsit.size();
    }
    public static class  MyViewHolder extends RecyclerView.ViewHolder{
        TextView firstname, f_doctor_spec,f_doctor_ext,f_doct_day,f_doc_time,f_doc_email;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            firstname=itemView.findViewById(R.id.firstname);
            f_doctor_spec=itemView.findViewById(R.id.spec);
            f_doctor_ext=itemView.findViewById(R.id.ext);
            f_doct_day=itemView.findViewById(R.id.f_day);
            f_doc_time=itemView.findViewById(R.id.f_time);
            f_doc_email=itemView.findViewById(R.id.f_mail);
        }
    }
}
