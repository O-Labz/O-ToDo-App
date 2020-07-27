package com.assignment.yolitodo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

public class AddChildFragment extends DialogFragment {

    public final static String SHARED_STORAGE = "sharedStorage";

    EditText editText;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.fragment_add_child_list, null))
                // Add action buttons
                .setMessage(R.string.addReminderButton)
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // Call function in main activity to add a new list

                        addToList(null);

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddChildFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }


    public void addToList(View view) {
        // Do something in response to button

        // Creates instance of db
        DatabaseHelper dbHelper = new DatabaseHelper(getActivity());

        editText = getDialog().findViewById(R.id.childItemName);
        String message = editText.getText().toString();

        int parentId = loadItemClicked();

        int position = dbHelper.getRemindersInList(parentId).size();

        position = position + 1;

        if (!message.isEmpty()){
            dbHelper.addReminder(message,parentId, position);

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

        // Refresh Reminder activity
        Intent intent = new Intent(context, ReminderActivity.class);
        intent.putExtra("itemId",loadItemClicked ());
        context.startActivity(intent);
    }

    public int loadItemClicked () {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_STORAGE,Context.MODE_PRIVATE);

        int defaultValue = sharedPreferences.getInt("Position", 1);

        return defaultValue;
    }
}
