package com.assignment.yolitodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ToDoParentAdapter extends RecyclerView.Adapter<ToDoParentAdapter.MyViewHolder> {

    ArrayList<String> parents;

    Context context;

    public ToDoParentAdapter (Context ct, ArrayList<String> parent){
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
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.itemText.setText(parents.get(position));
    }

    @Override
    public int getItemCount() {
        return parents.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView itemText;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            itemText = itemView.findViewById(R.id.textItem);

        }
    }

}