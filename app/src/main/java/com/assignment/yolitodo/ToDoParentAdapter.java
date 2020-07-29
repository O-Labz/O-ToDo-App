package com.assignment.yolitodo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.LinkedHashMap;

public class ToDoParentAdapter extends RecyclerView.Adapter<ToDoParentAdapter.MyViewHolder> {


    LinkedHashMap<String, String> parents;

    Context context;

    public ToDoParentAdapter (Context ct, LinkedHashMap<String, String> parent){
        context = ct;
        parents = parent;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.itemrow,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        String keys [] = parents.keySet().toArray(new String[parents.size()]);

        holder.positionText.setText(String.valueOf(position));
        holder.itemText.setText(keys[position]);
        holder.dateText.setText(parents.get(keys[position]));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int result = position + 1;
                Intent intent = new Intent(context, ReminderActivity.class);
                intent.putExtra("itemId",result);
                context.startActivity(intent);
            }
        });
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper dbHelper = new DatabaseHelper(context);
                int result = position + 1;
                dbHelper.deleteParent(result);

                // Refresh Activity
                Intent intent = new Intent(context,MainActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return parents.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView itemText;
        TextView dateText;
        TextView positionText;
        ConstraintLayout mainLayout;
        ImageView deleteButton;


        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            itemText = itemView.findViewById(R.id.textItem);
            dateText = itemView.findViewById(R.id.dueLabel);
            positionText = itemView.findViewById(R.id.positionText);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            deleteButton = itemView.findViewById(R.id.parentDelete);
        }
    }

}