package com.example.hospitalindoor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.MyViewHolder> {
    Context context;
    ArrayList<User> userArrayList3;

    public NewAdapter(Context context, ArrayList<User> userArrayList3) {
        this.context = context;
        this.userArrayList3 = userArrayList3;
    }

    @NonNull
    @Override
    public NewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item2,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewAdapter.MyViewHolder holder, int position) {
        User user= userArrayList3.get(position);
        holder.name2.setText(user.name);
        holder.Specialization2.setText(user.Specialization);


    }

    @Override
    public int getItemCount() {
        return userArrayList3.size();
    }
    public static  class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView name2,Specialization2;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name2=itemView.findViewById(R.id.tvfirstname);
            Specialization2= itemView.findViewById(R.id.tvspec);
        }
    }}
