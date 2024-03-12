package com.example.strzihapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;


public class ItemFragment_note extends Fragment {


    private CheckBox checkBox;
    ArrayList<TaskModel> notes;
    private RecyclerView recyclerView;
//    ArrayList<TaskModel> notes = new ArrayList<TaskModel>()
//    {
//        {
//            add(new TaskModel (0, "Заметка про лабы1", "Нужно всё сделать1", "", false));
//            add(new TaskModel (1, "Заметка про лабы2", "Нужно всё сделать2", "", false));
//            add(new TaskModel (2, "Заметка про лабы3", "Нужно всё сделать3", "", false));
//        }
//
//    };



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                MainActivity mainActivityRecycler = (MainActivity) getActivity();
        if (mainActivityRecycler != null) {
            notes = mainActivityRecycler.getNotes();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_note_list, container, false);



        recyclerView = view.findViewById(R.id.list);
        MyItemRecyclerViewAdapter adapter = new MyItemRecyclerViewAdapter(notes, requireContext());
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        return view;
    }
}