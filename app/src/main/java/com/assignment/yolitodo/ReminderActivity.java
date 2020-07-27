package com.assignment.yolitodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ReminderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        System.out.println("Omri Position: "+ getPosition());
    }


    private int getPosition(){
        int position = 0;

        if (getIntent().hasExtra("itemId"))
        {
            position = getIntent().getIntExtra("itemId", 1);
        }
        return position;
    }

}