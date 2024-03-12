package com.example.strzihapp;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.strzihapp.placeholder.PlaceholderContent;

import java.util.ArrayList;


public class ItemFragment_note extends Fragment {



    private RecyclerView recyclerView;
    TextView nameView, descView;

    private ArrayList<TaskModel> notes= new ArrayList<TaskModel>()
    {
        {
            add(new TaskModel (0, "Заметка про лабы1", "Нужно всё сделать1"));
            add(new TaskModel (1, "Заметка про лабы2", "Нужно всё сделать2"));
            add(new TaskModel (2, "Заметка про лабы3", "Нужно всё сделать3"));
        }

    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_note_list, container, false);

        recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));




        return view;
    }
}