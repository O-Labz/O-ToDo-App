package com.assignment.yolitodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class ReminderActivity extends AppCompatActivity {

    public final static String SHARED_STORAGE = "sharedStorage";

    private ArrayList toDoContainers;

    private RecyclerView toDoChildrenView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        DatabaseHelper dbHelper = new DatabaseHelper(ReminderActivity.this);


        // Set up list view

        // Find ListView
        toDoChildrenView = findViewById(R.id.reminderList);

        // Get items from database
        toDoContainers = dbHelper.getAllReminderItems(getPosition());

        ToDChildAdapter toDChildAdapter = new ToDChildAdapter(ReminderActivity.this, toDoContainers);

        toDoChildrenView.setAdapter(toDChildAdapter);

        toDoChildrenView.setLayoutManager(new LinearLayoutManager(ReminderActivity.this));

    }


    public void showChildListDialog(View view) {
        // Do something in response to button

        AddChildFragment modal = new AddChildFragment();

        modal.show(getSupportFragmentManager(),"addChild");

    }


    private int getPosition(){
        int position = 0;

        if (getIntent().hasExtra("itemId"))
        {
            position = getIntent().getIntExtra("itemId", 1);
            savePosition(position);
        }
        return position;
    }


    public void savePosition(int position){

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_STORAGE,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("Position", position);
        editor.commit();
    }
}