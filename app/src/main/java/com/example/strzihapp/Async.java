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
        switch (oper)
        {
            case "insert":
                Dao.insert(params[0]);
                break;
            case "read":
                Dao.getAllTasks();
                break;
            case "update":
                Dao.update(params[0]);
                break;
            case "delete":
                Dao.delete(params[0]);

        }


        return null;
    }
}
