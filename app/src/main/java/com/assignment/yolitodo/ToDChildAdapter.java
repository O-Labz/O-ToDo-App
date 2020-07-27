package com.assignment.yolitodo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ToDChildAdapter extends RecyclerView.Adapter<ToDChildAdapter.MyViewHolder>{

    public final static String SHARED_STORAGE = "sharedStorage";

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

            holder.deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatabaseHelper dbHelper = new DatabaseHelper(context);
                    int result = position + 1;

                    dbHelper.deleteChild(loadItemClicked(), result);

                    Intent intent = new Intent(context, ReminderActivity.class);
                    intent.putExtra("itemId",loadItemClicked ());
                    context.startActivity(intent);
                }
            });
    }


    public int loadItemClicked () {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_STORAGE,Context.MODE_PRIVATE);

        int defaultValue = sharedPreferences.getInt("Position", 1);

        return defaultValue;
    }

    @Override
    public int getItemCount() {
        return children.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView itemText;
        ImageView deleteButton;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            itemText = itemView.findViewById(R.id.reminderItem);
            deleteButton = itemView.findViewById(R.id.childDelete);

        }
    }

}
