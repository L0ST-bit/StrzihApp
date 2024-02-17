package com.example.strzihapp;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class StrizhViewModel extends AndroidViewModel {
    private static final String TAG = "StrizhViewModel";
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

    public StrizhViewModel(@NonNull Application application) {
        super(application);
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "view model Почти бух");
    }

    private ArrayList<TaskModel> notesBank = new ArrayList<TaskModel>()
    {
        {
            add(new TaskModel (0, "Заметка про лабы1", "Нужно всё сделать1"));
            add(new TaskModel (1, "Заметка про лабы2", "Нужно всё сделать2"));
            add(new TaskModel (2, "Заметка про лабы3", "Нужно всё сделать3"));
        }

    };


//    String name = notesBank.get(CurIndex).getName();
//    String description = notesBank.get(CurIndex).getDescription();


//    public void addNote(){
//        notesBank.add(new TaskModel(CurIndex, name, description));
//    }
    public void moveToNext(){

        int idTemp = CurIndex;
        if(idTemp++< notesBank.size()-1){        CurIndex++;
            loadDataInt();}

    }

    public void moveToPrevious(){
        int idTemp = CurIndex;

        if(idTemp-- > 0){CurIndex--;
            loadDataInt();}

    }
}
