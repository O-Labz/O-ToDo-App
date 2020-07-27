package com.assignment.yolitodo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ToDChildAdapter extends RecyclerView.Adapter<ToDChildAdapter.MyViewHolder>{

        ArrayList<String> children;

        Context context;

    public ToDChildAdapter (Context ct, ArrayList<String> child){
            context = ct;
            children = child;
            }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup child, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.reminderrow,child,false);
            return new MyViewHolder(view);
            }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
            holder.itemText.setText(children.get(position));
//            holder.mainLayout.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
//            int result = position + 1;
//            Intent intent = new Intent(context, ReminderActivity.class);
//            intent.putExtra("itemId",result);
//            context.startActivity(intent);
//            }
//            });
    }

    @Override
    public int getItemCount() {
        return children.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView itemText;
//        ConstraintLayout mainLayout;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            itemText = itemView.findViewById(R.id.reminderItem);
//            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }
}
