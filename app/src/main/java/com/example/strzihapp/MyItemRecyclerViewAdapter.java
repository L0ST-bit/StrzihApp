package com.example.strzihapp;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.strzihapp.placeholder.PlaceholderContent.PlaceholderItem;
import com.example.strzihapp.databinding.FragmentItemNoteBinding;

import java.util.ArrayList;
import java.util.List;


public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {
    private ArrayList<TaskModel> notes;

    public MyItemRecyclerViewAdapter (ArrayList<TaskModel> notes) {
        this.notes = notes;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewNoteTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewNoteTitle = itemView.findViewById(R.id.textViewNoteTitle);
        }
    }

    @Override
    public MyItemRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyItemRecyclerViewAdapter.ViewHolder holder, int position) {
        TaskModel note = notes.get(position);
        holder.textViewNoteTitle.setText(note.getName());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}
