package com.example.strzihapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivityRecycler extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recycler);

        FrameLayout frameLayout = findViewById(R.id.frame_one);
        ItemFragment_note blankFragment = new ItemFragment_note();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_one, blankFragment);
        ft.commit();
        //fr2();
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