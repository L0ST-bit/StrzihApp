package com.example.strzihapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class StrizhViewModel extends AndroidViewModel {
    private static final String TAG = "StrizhViewModel";
    int CurIndex;

    private NotesRepo repository;
    private LiveData<List<TaskModel>> allTasks;

    public StrizhViewModel(Application application) {
        super(application);
        repository = new NotesRepo(application);
        allTasks = repository.getAllTasks();
    }


    LiveData<List<TaskModel>> getAllTasks() { return allTasks; }

    public void insert(TaskModel task) { repository.insert(task); }



    private MutableLiveData<ArrayList<TaskModel>> data;
    private MutableLiveData<Integer> index;

    public MutableLiveData<ArrayList<TaskModel>> getData() {
        if (data == null) {
            data = new MutableLiveData<ArrayList<TaskModel>>();
            loadData();
        }
        return data;
    }
    public MutableLiveData<Integer> getDataInt() {
        if (index == null) {
            index = new MutableLiveData<Integer>();
            loadDataInt();
        }
        return index;
    }

    private void loadData() {
        data.setValue(notesBank);

    }
    private void loadDataInt() {
        index.setValue(CurIndex);

    }




    @Override
    protected void onCleared() {
        super.onCleared();

    }

    public void setNotesBank(ArrayList<TaskModel> notesBank) {
        this.notesBank = notesBank;
    }

    private ArrayList<TaskModel> notesBank = new ArrayList<TaskModel>()
    {
        {
            add(new TaskModel (1, "Заметка про лабы1", "Нужно всё сделать1", false));
            add(new TaskModel (2, "Заметка про лабы2", "Нужно всё сделать2", true));

        }

    };

    public void showLast()
    {
        CurIndex = notesBank.size()-1;
        loadDataInt();
    }
    public void moveToNext()
    {
        int idTemp = CurIndex;
        if(idTemp++< notesBank.size()-1)
        {
            CurIndex++;
            loadDataInt();}

        }

    public void moveToPrevious()
    {
        int idTemp = CurIndex;
        if(idTemp-- > 0)
        {
            CurIndex--;
            loadDataInt();
        }

    }


    protected void insertNote(TaskModel note, Dao_DB Dao)
    {


        new Async(Dao, "insert").execute(note);

    }
    protected void updateNote(TaskModel note, Dao_DB Dao)
    {
        new Async(Dao, "update").execute(note);

    }


}
