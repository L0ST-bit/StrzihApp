package com.example.strzihapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class RecyclerViewModel extends AndroidViewModel {

    public RecyclerViewModel(@NonNull Application application, int curIndex) {
        super(application);
    }

    private static final String TAG = "StrizhViewModelRecycler";
    int CurIndex;

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

    private ArrayList<TaskModel> notesBank = new ArrayList<TaskModel>()
    {
        {
            add(new TaskModel (0, "Заметка про лабы1", "Нужно всё сделать1", false));
            add(new TaskModel (1, "Заметка про лабы2", "Нужно всё сделать2", false));
            add(new TaskModel (2, "Заметка про лабы3", "Нужно всё сделать3", false));
        }

    };



}
