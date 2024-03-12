package com.example.strzihapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;


public class BlankFragment_frame_two extends Fragment {
    private static final String TAG_NAME = "name";
    private static final String TAG_DESC = "description";
    private static final String TAG_CHECK = "check";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_blank_frame_two, container, false);
        Bundle args = getArguments();
        if (args != null) {
            String newName = args.getString(TAG_NAME);
            String newDesc = args.getString(TAG_DESC);
            boolean check = args.getBoolean(TAG_CHECK);
            TextView name = (TextView) v.findViewById(R.id.textView_name);
            TextView desc = (TextView) v.findViewById(R.id.textView_desc);
            CheckBox checkBox2 = (CheckBox) v.findViewById(R.id.checkBox2);
            name.setText(newName);
            desc.setText(newDesc);
            checkBox2.setChecked(check);

        }



        return v;
    }
}