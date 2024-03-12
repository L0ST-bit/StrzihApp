package com.example.strzihapp;

import static androidx.core.content.ContentProviderCompat.requireContext;
import static androidx.core.content.ContextCompat.startActivity;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
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
    private Context context;
    public MyItemRecyclerViewAdapter(ArrayList<TaskModel> notes, Context context) {
        this.notes = notes;
        this.context = context;
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewNoteTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewNoteTitle = itemView.findViewById(R.id.textViewNoteTitle);
        }
    }

//    private OnItemClickListener listener;
//
//    public interface OnItemClickListener {
//        void onItemClick(View view, int position);
//    }
//
//    public void setOnItemClickListener(OnItemClickListener listener) {
//        this.listener = listener;
//    }

    @Override
    public MyItemRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(MyItemRecyclerViewAdapter.ViewHolder holder, int position) {
        TaskModel selectedNote = notes.get(position);
        holder.textViewNoteTitle.setText(selectedNote.getName());

        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(context,MainActivity3.class);
            intent.putExtra("name", selectedNote.getName());
            intent.putExtra("desc", selectedNote.getDescription());
            startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }



}
