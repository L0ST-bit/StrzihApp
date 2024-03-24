package com.example.strzihapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;


public class ItemFragment_note extends Fragment implements MyItemRecyclerViewAdapter.OnNoteListener {

    private static final String TAG_NAME = "name";
    private static final String TAG_DESC = "description";
    private static final String TAG_ID = "id_note";
    private static final String TAG_CHECK = "check";


    ArrayList<TaskModel> notes;
    private RecyclerView recyclerView;

    private SwipeRefreshLayout swipeRefreshLayout;
    private OnRefreshListener refreshListener;

    public interface OnRefreshListener {
        void onRefreshData();
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_note_list, container, false);

        swipeRefreshLayout =  view.findViewById(R.id.swipe_refresh_layout);
        MainActivity mainActivityRecycler = (MainActivity) getActivity();
        if (mainActivityRecycler != null) {
            notes = mainActivityRecycler.getNotes();

        }


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if (refreshListener != null) {
                    refreshListener.onRefreshData();
                }
                swipeRefreshLayout.setRefreshing(false); // Заканчиваем анимацию обновления
            }
        });




        //Log.d("sssssssssssssssssssssssssssssssssssssssssssssssssssssss", notes.get(1).getName());
        //mainActivityRecycler = (MainActivity) getActivity();
        //String name = notes.get(0).getName();
        recyclerView = view.findViewById(R.id.list);
        MyItemRecyclerViewAdapter adapter = new MyItemRecyclerViewAdapter(notes, getContext(),this);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        return view;
    }



    public void onNoteEdit(TaskModel selectedNote) {
        Intent intent = new Intent(getActivity(), EditNoteActivity.class);

        intent.putExtra(TAG_ID, selectedNote.getId());
        intent.putExtra(TAG_NAME, selectedNote.getName());
        intent.putExtra(TAG_DESC, selectedNote.getDescription());
        intent.putExtra(TAG_CHECK, selectedNote.isCheck());

        getActivity().startActivityForResult(intent, 1);

    }


}
