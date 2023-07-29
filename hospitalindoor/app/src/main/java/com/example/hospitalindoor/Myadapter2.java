package com.example.hospitalindoor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Myadapter2 extends RecyclerView.Adapter<Myadapter2.MyViewHolder> {
    Context context;
    ArrayList<appoint_confirm> userArraylist2;

    public Myadapter2(Context context, ArrayList<appoint_confirm> userArraylist2) {
        this.context = context;
        this.userArraylist2=userArraylist2;
    }

    @NonNull
    @Override
    public Myadapter2.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item5,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        appoint_confirm ac=userArraylist2.get(position);
        holder.firstname.setText(ac.doctorname);
        holder.p_name.setText(ac.name);
        holder.p_status.setText(ac.status);
        holder.f_doct_day.setText(ac.date);
        holder.firstname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),acceptorrejectappointment.class);
                i.putExtra("doctoname",ac.doctorname);
                i.putExtra("name",ac.name);
                i.putExtra("stat",ac.status);
                i.putExtra("dat",ac.date);

                view.getContext().startActivity(i);


            }
        });


    }




    @Override
    public int getItemCount() {
        return userArraylist2.size();
    }
    public static class  MyViewHolder extends RecyclerView.ViewHolder{
        TextView firstname, p_status,p_name,f_doct_day;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            firstname=itemView.findViewById(R.id.firstname);
            p_status=itemView.findViewById(R.id.f_status);
            p_name=itemView.findViewById(R.id.p_name);
            f_doct_day=itemView.findViewById(R.id.p_date);
        }
    }
}

