package com.example.strzihapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

        NotesAdapter adapter = new NotesAdapter(requireContext(), notes);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TaskModel selectedNote = notes.get(position);
                Intent intent = new Intent(requireContext(), MainActivity3.class);
                intent.putExtra("name", selectedNote.getName());
                intent.putExtra("desc", selectedNote.getDescription());
                //startActivity(intent);

                Bundle bundle = new Bundle();
                bundle.putString("Key", selectedNote.getName());
                BlankFragment2 blankFragment2 = new BlankFragment2();
                blankFragment2.setArguments(bundle);
                ((MainActivity2) requireActivity()).sharedString = selectedNote.getName();
                ((MainActivity2) requireActivity()).fr2();

//                nameView = view.findViewById(R.id.textViewName);
//                descView = view.findViewById(R.id.textView2Desc);
//                nameView.setText(selectedNote.getName());
//                descView.setText(selectedNote.getDescription());
            }
        });



        return view;
    }
}