package com.example.strzihapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class StrizhViewModel extends AndroidViewModel {

    private NotesRepo repository;
    private LiveData<List<TaskModel>> allTasks;

    public StrizhViewModel(Application application) {
        super(application);
        repository = new NotesRepo(application);
        allTasks = repository.getAllTasks();
    }



    public void insert(TaskModel task) { repository.insert(task); }
    public void update(TaskModel task) { repository.update(task); }
    public void delete(TaskModel task)
    {

        notesBank.remove(task.getId()-1);
        repository.delete(task);
    }

    public LiveData<List<TaskModel>> getAllTasks() {
        return allTasks;
    }



    private MutableLiveData<ArrayList<TaskModel>> data;


    public MutableLiveData<ArrayList<TaskModel>> getData() {
        if (data == null) {
            data = new MutableLiveData<ArrayList<TaskModel>>();
            loadData();
        }
        return data;
    }


    private void loadData() {
        data.setValue(notesBank);

    }

    @Override
    protected void onCleared() {
        super.onCleared();

    }

    public void setNotesBank(ArrayList<TaskModel> notesBank) {
        this.notesBank = notesBank;
    }

    public ArrayList<TaskModel> getNotesBank() {
        return notesBank;
    }

    private ArrayList<TaskModel> notesBank = new ArrayList<TaskModel>()
    {
        {
            add(new TaskModel ( "Заметка про лабы1", "Нужно всё сделать1", false));
            add(new TaskModel ( "Заметка про лабы2", "Нужно всё сделать2", true));

        }

    };


}
