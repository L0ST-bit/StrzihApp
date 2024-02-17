package com.example.strzihapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {




    private static final String TAG = "StrizhViewModel";
    private EditText note_name, note_description;
    private Button add_button, save_button, show_last_button;
    private ImageButton next, previous;
    private int idNote;

    private ArrayList<TaskModel> notes = new ArrayList<TaskModel>();
    private void Console(){
        int t = 0;
        t++;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Console();


        StrizhViewModel strizhViewModel = new ViewModelProvider(this).get(StrizhViewModel.class);
        strizhViewModel.getData().observe(this, data -> {
             notes = data;
        });
        strizhViewModel.getDataInt().observe(this, index -> {
            idNote = index;
        });
        Log.d(TAG, "Модель получена");


        note_name = (EditText) findViewById(R.id.note_name_edit);
        note_description = (EditText)
                findViewById(R.id.note_description_edit);
        add_button = (Button) findViewById(R.id.add_button);
        save_button = (Button) findViewById(R.id.save_button);
        show_last_button = (Button) findViewById(R.id.show_last_button);
        previous = (ImageButton) findViewById(R.id.previous);
        next = (ImageButton) findViewById(R.id.next);

        Log.d("Lifecycle", "onCreate invoked");


        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //strizhViewModel.addNote();
                notes.add(new TaskModel(notes.size(), note_name.getText().toString(), note_description.getText().toString()));

                note_name.setText("");
                note_description.setText("");

                idNote = notes.size()-1;

            }
        });
        show_last_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idNote = notes.size()-1;
                note_name.setText(notes.get(idNote).getName());
                note_description.setText(notes.get(idNote).getDescription());

            }
        });

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notes.get(idNote).setName(note_name.getText().toString());
                notes.get(idNote).setDescription(note_description.getText().toString());

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int idTemp = idNote;
                if(idTemp++< notes.size()-1)
                {
                    strizhViewModel.moveToNext();
                    //idNote++;
                    note_name.setText(notes.get(idNote).getName());
                    note_description.setText(notes.get(idNote).getDescription());
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idTemp = idNote;
                if(idTemp-- > 0)
                {
                    strizhViewModel.moveToPrevious();
                    //idNote--;
                    note_name.setText(notes.get(idNote).getName());
                    note_description.setText(notes.get(idNote).getDescription());
                }
            }
        });


    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Lifecycle", "onStart invoked");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Lifecycle", "onResume invoked");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Lifecycle", "onPause invoked");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Lifecycle", "onStop invoked");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Lifecycle", "onDestroy invoked");
    }




}