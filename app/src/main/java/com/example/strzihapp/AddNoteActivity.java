package com.example.strzihapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNoteActivity extends AppCompatActivity {
    private static final String TAG_NAME = "name";
    private static final String TAG_DESC = "description";


    private EditText note_name, note_description;
    private Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        note_name = (EditText) findViewById(R.id.add_note_name_edit);
        note_description = (EditText) findViewById(R.id.add_note_description_edit);
        add_button = (Button) findViewById(R.id.add_note_button);
        Intent intent = getIntent();

        String name = intent.getStringExtra(TAG_NAME);
        String description = intent.getStringExtra(TAG_DESC);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent resultIntent = new Intent();
                resultIntent.putExtra(TAG_NAME,note_name.getText().toString());
                resultIntent.putExtra(TAG_DESC,note_description.getText().toString());

                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }



}