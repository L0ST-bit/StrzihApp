package com.example.strzihapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditNoteActivity extends AppCompatActivity {

    private static final String TAG_NAME = "name";
    private static final String TAG_DESC = "description";
    private static final String TAG_ID = "id_note";

    private EditText note_name, note_description;
    private Button save_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        note_name = (EditText) findViewById(R.id.edit_note_name_edit);
        note_description = (EditText)
                findViewById(R.id.edit_note_description_edit);

        save_button = (Button) findViewById(R.id.edit_save_button);

        Intent intent = getIntent();

        // Извлекаем данные из Intent
        int idNote = intent.getIntExtra(TAG_ID, 0);
        String name = intent.getStringExtra(TAG_NAME);
        String description = intent.getStringExtra(TAG_DESC);
        note_name.setText(name);
        note_description.setText(description);



        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent resultIntent = new Intent();
                resultIntent.putExtra(TAG_NAME,note_name.getText().toString());
                resultIntent.putExtra(TAG_DESC,note_description.getText().toString());
                resultIntent.putExtra(TAG_ID,idNote);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }



}