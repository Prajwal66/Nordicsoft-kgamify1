package com.example.kgamify;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.holder> {


    List<Backend_Category> category_arr;
    Context context;



    public myAdapter(List<Backend_Category> category_arr, Context context) {

        this.category_arr = category_arr;
        this.context=context;
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

        String Cat_selected=category_arr.get(position).getCategory().toString();
        holder.tv_cat_name.setText(Cat_selected);
        holder.tv_number_of_champ.setText(category_arr.get(position).getCount().toString());
        holder.linear_layout_category_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,Championships.class);
                i.putExtra("Category_selected",Cat_selected);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return category_arr.size();
    }


    class holder extends RecyclerView.ViewHolder{

        TextView tv_cat_name,tv_number_of_champ;
        ImageView img_expand_more;
       RelativeLayout linear_layout_category_box;


        public holder(@NonNull View itemView) {
            super(itemView);

            tv_cat_name=(TextView) itemView.findViewById(R.id.tv_cat_name);
            tv_number_of_champ=(TextView) itemView.findViewById(R.id.tv_number_of_champ);
            //img_expand_more=(ImageView) itemView.findViewById(R.id.img_expand_more);
            linear_layout_category_box=(RelativeLayout) itemView.findViewById(R.id.fixed_layout);



        }
    }

}
