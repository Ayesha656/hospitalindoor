package com.example.hospitalindoor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter3 extends RecyclerView.Adapter<MyAdapter3.MyViewHolder>{
    Context context;
    ArrayList<notification> userArraylist3;
    public MyAdapter3(Context context, ArrayList<notification> userArraylist3) {
        this.context = context;
        this.userArraylist3 = userArraylist3;
    }




    @NonNull
    @Override
    public MyAdapter3.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item4,parent,false);
        return new MyAdapter3.MyViewHolder(v);
    }




    @Override
    public void onBindViewHolder(@NonNull MyAdapter3.MyViewHolder holder, int position) {
        notification not=userArraylist3.get(position);
        holder.f_appoint_date.setText(not.date);
        holder.f_appoint_status.setText(not.status);
        holder.firstname.setText(not.doctorname);


    }

    @Override
    public int getItemCount() {
        return  userArraylist3.size();
    }


    public static class  MyViewHolder extends RecyclerView.ViewHolder{
        TextView firstname, f_appoint_status,f_appoint_date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            firstname=itemView.findViewById(R.id.f_name);
            f_appoint_status=itemView.findViewById(R.id.f_appoint_status);
            f_appoint_date=itemView.findViewById(R.id.f_appoint_date);

        }
    }
}

