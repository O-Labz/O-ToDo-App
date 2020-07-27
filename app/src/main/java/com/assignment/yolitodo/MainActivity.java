package com.assignment.yolitodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Toast;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ArrayList toDoContainers;

    private RecyclerView toDoParentView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toDoParentView = findViewById(R.id.listViewOptions);

        toDoContainers = new ArrayList();

        toDoContainers.add("hello");
        toDoContainers.add("omri");
        toDoContainers.add("hi");
        toDoContainers.add("good");

        ToDoParentAdapter toDoParentAdapter = new ToDoParentAdapter(MainActivity.this, toDoContainers);

        toDoParentView.setAdapter(toDoParentAdapter);


        toDoParentView.setLayoutManager(new LinearLayoutManager(MainActivity.this));




    }


    /** Called when the user taps the Send button */
    public void showListDialog(View view) {
        // Do something in response to button

        AddListFragment modal = new AddListFragment();

        modal.show(getSupportFragmentManager(),"addlist");

    }


    public void showToast(String message){

        Context context = getApplicationContext();
        String text = "New List " + message + " Created";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }



}