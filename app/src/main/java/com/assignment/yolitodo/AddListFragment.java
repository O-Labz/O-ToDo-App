package com.assignment.yolitodo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class AddListFragment extends DialogFragment {

    EditText editText;
    CalendarView calendarView;

    Calendar calendar = Calendar.getInstance();


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.fragment_add_list, null))
                // Add action buttons
                .setMessage(R.string.listDialogueName)
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // Call function in main activity to add a new list

                        addToList(null);

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddListFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }


    public void addToList(View view) {
        // Do something in response to button

        // Creates instance of db
        DatabaseHelper dbHelper = new DatabaseHelper(getActivity());

        editText = getDialog().findViewById(R.id.editTextTextPersonName);
        calendarView = getDialog().findViewById(R.id.calendar);

        String message = editText.getText().toString();

        calendarView.setOnDateChangeListener( new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                calendar = new GregorianCalendar( year, month, dayOfMonth );
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            }
        });

        String selectedDateString = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());



        if (!message.isEmpty()){
            dbHelper.addText(selectedDateString, message);

            // Show Toast
            Context context = getActivity();
            String text = "New List " + message + " Created";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            refreshActivity();
        }
    }


    public void refreshActivity(){
        Context context = getActivity();

        // Refresh Main activity
        Intent intent = new Intent(context,MainActivity.class);
        context.startActivity(intent);
    }

}