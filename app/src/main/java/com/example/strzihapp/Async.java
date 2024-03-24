package com.example.strzihapp;

import android.os.AsyncTask;

public class Async extends AsyncTask<TaskModel, Void, Void> {
    private Dao_DB Dao;
    private String oper;

    Async(Dao_DB dao, String oper) {
        this.Dao = dao;
        this.oper = oper;
    }

    @Override
    protected Void doInBackground(final TaskModel... params) {



        return null;
    }
}
