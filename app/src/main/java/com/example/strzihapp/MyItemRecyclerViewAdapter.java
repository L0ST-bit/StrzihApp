package com.example.strzihapp;

import static androidx.core.content.ContentProviderCompat.requireContext;
import static androidx.core.content.ContextCompat.startActivity;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.strzihapp.placeholder.PlaceholderContent.PlaceholderItem;
import com.example.strzihapp.databinding.FragmentItemNoteBinding;

import java.util.ArrayList;
import java.util.List;


public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private static final String TAG_NAME = "name";
    private static final String TAG_DESC = "description";
    private static final String TAG_ID = "id_note";
    private static final String TAG_CHECK = "check";
    private ArrayList<TaskModel> notes;
    private Context context;
    private MainActivity mainActivity;
    public MyItemRecyclerViewAdapter(ArrayList<TaskModel> notes, Context context, MainActivity mainActivity) {
        this.notes = notes;
        this.context = context;
        this.mainActivity = mainActivity;
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewNoteTitle;
        public CheckBox checkBox;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewNoteTitle = itemView.findViewById(R.id.textViewNoteTitle);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }


    @Override
    public MyItemRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(MyItemRecyclerViewAdapter.ViewHolder holder, int position) {
        TaskModel selectedNote = notes.get(position);
        holder.textViewNoteTitle.setText(selectedNote.getName());
        holder.checkBox.setChecked(selectedNote.isCheck());

        holder.itemView.setOnClickListener(v -> {

            mainActivity.getEdit();
//
//            Intent intent = new Intent(context, EditNoteActivity.class);
//            intent.putExtra(TAG_NAME, selectedNote.getName());
//            intent.putExtra(TAG_DESC, selectedNote.getDescription());
//            intent.putExtra(TAG_ID, selectedNote.getId());
//            intent.putExtra(TAG_CHECK, selectedNote.isCheck());
//            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }





}
