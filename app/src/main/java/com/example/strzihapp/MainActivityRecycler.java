package com.example.strzihapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import java.util.ArrayList;

public class MainActivityRecycler extends AppCompatActivity {

    private static final String TAG = "StrizhViewModelRecycler";
    private ArrayList<TaskModel> notes;
    private int idNote;



    private StrizhViewModel strizhViewModel;

    public ArrayList<TaskModel> getNotes() {
        return notes;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recycler);

        strizhViewModel = new ViewModelProvider(this).get(StrizhViewModel.class);
        strizhViewModel.getData().observe(this, data -> {
            notes = data;

        });
//        strizhViewModel.getDataInt().observe(this, index -> {
//            idNote = index;
//        });
//        Log.d(TAG, "Модель получена");


//        FrameLayout frameLayout = findViewById(R.id.frame_one);
//        ItemFragment_note blankFragment = new ItemFragment_note();
//
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.frame_one, blankFragment);
//        ft.commit();
        //fr2();
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

        ItemFragment_note blankFragment = new ItemFragment_note();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_one, blankFragment);
        ft.commit();

    }
//    public void fr2()
//    {
//        FrameLayout frameLayout2 = findViewById(R.id.frame_two);
//        ItemFragment_note blankFragment2 = new ItemFragment_note();
//        FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
//        ft2.replace(R.id.frame_two, blankFragment2);
//        ft2.commit();
//    }

}
