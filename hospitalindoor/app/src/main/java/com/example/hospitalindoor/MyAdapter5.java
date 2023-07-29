package com.example.hospitalindoor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter5 extends RecyclerView.Adapter<MyAdapter5.MyViewHolder>{
    Context context;
    ArrayList<review_patient> userArraylist5;

    public MyAdapter5(Context context, ArrayList<review_patient> userArraylist5) {
        this.context = context;
        this.userArraylist5 = userArraylist5;
    }

    @NonNull
    @Override

    public MyAdapter5.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item7,parent,false);
        return new MyAdapter5.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter5.MyViewHolder holder, int position) {
        review_patient re=userArraylist5.get(position);
      holder.thisrevi.setText(re.review);
    }

    @Override
    public int getItemCount() {
        return  userArraylist5.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView thisrevi;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            thisrevi=itemView.findViewById(R.id.thisreview);
        }
    }
}
