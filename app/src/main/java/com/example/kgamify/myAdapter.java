package com.example.kgamify;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.holder> {


    List<Backend_Category> category_arr;



    public myAdapter(List<Backend_Category> category_arr) {
        this.category_arr = category_arr;

    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.main_category,parent,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        holder.tv_cat_name.setText(category_arr.get(position).getCategory().toString());
    }

    @Override
    public int getItemCount() {
        return category_arr.size();
    }


    class holder extends RecyclerView.ViewHolder{

        TextView tv_cat_name,tv_number_of_champ;
        ImageView img_expand_more;


        public holder(@NonNull View itemView) {
            super(itemView);

            tv_cat_name=(TextView) itemView.findViewById(R.id.tv_cat_name);
            tv_number_of_champ=(TextView) itemView.findViewById(R.id.tv_number_of_champ);
            img_expand_more=(ImageView) itemView.findViewById(R.id.img_expand_more);



        }
    }

}
