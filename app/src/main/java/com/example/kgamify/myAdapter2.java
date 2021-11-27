package com.example.kgamify;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class myAdapter2 extends RecyclerView.Adapter<myAdapter2.holder2> {

    List<Backend_Championship> champ_arr;
    Context context;

    public myAdapter2(List<Backend_Championship> champ_arr,Context context) {
        this.champ_arr = champ_arr;
        this.context=context;
    }

    @NonNull
    @Override
    public holder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.championship_expand,parent,false);
        return new holder2(view);

    }

    @Override
    public void onBindViewHolder(@NonNull holder2 holder, int position) {

        holder.tv_champ_name.setText(champ_arr.get(position).getChampionship().toString());
        holder.tv_qualification.setText("Qualification : "+champ_arr.get(position).getQualification().toString());
        holder.tv_champ_start_time.setText("Start Time : "+champ_arr.get(position).getStart_time().toString());
        holder.tv_champ_end_time.setText("End Time : "+champ_arr.get(position).getEnd_time().toString());
        // holder.tv_number_of_participants.setText(champ_arr.get(position).getNumber_of_participants().toString());
        // holder.tv_total_coins.setText(champ_arr.get(position).getTotal_coins().toString());

      /*  holder.img_i_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Rushika please add open info pop up code here
            }
        });*/


    }

    @Override
    public int getItemCount() {
        return champ_arr.size();
    }


    class holder2 extends RecyclerView.ViewHolder{

     TextView tv_champ_name,tv_number_of_participants,tv_total_coins,tv_qualification,tv_champ_start_time,tv_champ_end_time;

     ImageView img_person,img_coin,img_i_btn;



        public holder2(@NonNull View itemView) {
            super(itemView);

            tv_champ_name=(TextView) itemView.findViewById(R.id.tv_champ_name);
            tv_number_of_participants=(TextView) itemView.findViewById(R.id.tv_number_of_participants);
            tv_total_coins=(TextView) itemView.findViewById(R.id.tv_total_coins);
            tv_qualification=(TextView) itemView.findViewById(R.id.tv_qualification);
            tv_champ_start_time=(TextView) itemView.findViewById(R.id.tv_champ_start_time);
            tv_champ_end_time=(TextView) itemView.findViewById(R.id.tv_champ_end_time);

            img_person=(ImageView) itemView.findViewById(R.id.img_person);
            img_coin=(ImageView) itemView.findViewById(R.id.img_coin);
            img_i_btn=(ImageView) itemView.findViewById(R.id.img_i_btn);



        }
    }
}
