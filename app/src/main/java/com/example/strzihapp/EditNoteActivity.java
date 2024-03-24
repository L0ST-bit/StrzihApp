package com.example.strzihapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.ArrayList;

public class EditNoteActivity extends AppCompatActivity {

    private static final String TAG_NAME = "name";
    private static final String TAG_DESC = "description";
    private static final String TAG_ID = "id_note";
    private static final String TAG_CHECK = "check";
    private static final String TAG_DELETE = "delete";

    private EditText note_name, note_description;
    private Button save_button, remove_button;
    private CheckBox checkBox_;
    private int idNoteInArray;

    private StrizhViewModel strizhViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        strizhViewModel = new ViewModelProvider(this).get(StrizhViewModel.class);

        note_name = findViewById(R.id.edit_note_name_edit);
        note_description = findViewById(R.id.edit_note_description_edit);
        save_button = findViewById(R.id.edit_save_button);
        remove_button = findViewById(R.id.buttonRemove);

        checkBox_ = (CheckBox) findViewById(R.id.edit_checkbox);


        Intent intent = getIntent();


        idNoteInArray = intent.getIntExtra(TAG_ID, 0)-1;
        String name = intent.getStringExtra(TAG_NAME);
        String description = intent.getStringExtra(TAG_DESC);
        boolean check = intent.getBooleanExtra(TAG_CHECK, false);
        note_name.setText(name);
        note_description.setText(description);
        checkBox_.setChecked(check);


        checkBox_.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)

            {


                Intent resultIntent = new Intent(EditNoteActivity.this, MainActivity.class);
                resultIntent.putExtra(TAG_NAME,note_name.getText().toString());
                resultIntent.putExtra(TAG_DESC,note_description.getText().toString());
                resultIntent.putExtra(TAG_ID,idNoteInArray);
                resultIntent.putExtra(TAG_CHECK,checkBox_.isChecked());
                setResult(Activity.RESULT_OK, resultIntent);

                finish();
            }
        });
        remove_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)

            {

                Intent resultIntent = new Intent(EditNoteActivity.this, MainActivity.class);
                resultIntent.putExtra(TAG_DELETE,true);
                resultIntent.putExtra(TAG_ID,idNoteInArray);
                setResult(Activity.RESULT_OK, resultIntent);

                finish();
            }
        });
    }







}