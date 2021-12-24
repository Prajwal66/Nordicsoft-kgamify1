package com.example.kgamify;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class myAdapter2 extends RecyclerView.Adapter<myAdapter2.holder2> {

    List<Backend_Championship> champ_arr;
    Context context;
    LinearLayout  fixed_layout_champ,champ_linear_layout;

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

        String Label_of_champ=champ_arr.get(position).getLabel().toString();
        String champ_name=champ_arr.get(position).getChampionship().toString();
        String champ_description=champ_arr.get(position).getDescription().toString();

        int num_of_questions=champ_arr.get(position).getNumber_of_questions();
        int time_for_quiz=champ_arr.get(position).getTotal_time();
        int bonus_coins=champ_arr.get(position).getTotal_coins();
        int num_of_participants=champ_arr.get(position).getNumber_of_participants();

        holder.tv_champ_name.setText(champ_arr.get(position).getChampionship().toString());
        holder.tv_qualification.setText("Qualification : "+champ_arr.get(position).getQualification().toString());
        holder.tv_champ_start_time.setText("Start Time : "+champ_arr.get(position).getStart_time().toString());
        holder.tv_number_of_participants.setText(champ_arr.get(position).getNumber_of_participants()+" participants");
        holder.tv_total_coins.setText(champ_arr.get(position).getTotal_coins()+" coins");

        holder.btn_play_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,GameMode.class);
                i.putExtra("Label_of_champ",Label_of_champ);
                i.putExtra("champ_name",champ_name);
                i.putExtra("num_of_questions",num_of_questions);
                i.putExtra("time_for_quiz",time_for_quiz);
                i.putExtra("bonus_coins",bonus_coins);

                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });

        holder.tv_champ_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,ChampInfo.class);

                i.putExtra("champ_name_for_pop",champ_name);
                i.putExtra("champ_participants_for_pop",num_of_participants);
                i.putExtra("champ_coins_for_pop",bonus_coins);
                i.putExtra("champ_desc",champ_description);

                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return champ_arr.size();
    }


    class holder2 extends RecyclerView.ViewHolder{

     TextView tv_champ_name,tv_number_of_participants,tv_total_coins,tv_qualification,tv_champ_start_time;

     ImageView img_person,img_coin,img_i_btn;

     Button btn_play_game;




        public holder2(@NonNull View itemView) {
            super(itemView);

            tv_champ_name=(TextView) itemView.findViewById(R.id.tv_champ_name);
            tv_number_of_participants=(TextView) itemView.findViewById(R.id.tv_number_of_participants);
            tv_total_coins=(TextView) itemView.findViewById(R.id.tv_total_coins);
            tv_qualification=(TextView) itemView.findViewById(R.id.tv_qualification);
            tv_champ_start_time=(TextView) itemView.findViewById(R.id.tv_champ_start_time);


            img_person=(ImageView) itemView.findViewById(R.id.img_person);
            img_coin=(ImageView) itemView.findViewById(R.id.img_coin);
            img_i_btn=(ImageView) itemView.findViewById(R.id.img_i_btn);

            btn_play_game=(Button) itemView.findViewById(R.id.btn_playGame);


            fixed_layout_champ=(LinearLayout) itemView.findViewById(R.id.fixed_layout_champ);
            champ_linear_layout=(LinearLayout) itemView.findViewById(R.id.champ_linear_layout);


        }
    }
}
