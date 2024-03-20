package com.example.strzihapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NotesRepo {
    private Dao_DB taskDao;
    private LiveData<List<TaskModel>> allTasks;

    NotesRepo(Application application) {
        DB_notes db = DB_notes.getDatabase(application);
        taskDao = db.notesDao();
        allTasks = taskDao.getAllTasks();
    }

    LiveData<List<TaskModel>> getAllTasks() {
        return allTasks;
    }

    void insert(TaskModel task) {
        new insertAsyncTask(taskDao).execute(task);
    }

    private static class insertAsyncTask extends AsyncTask<TaskModel, Void, Void> {
        private Dao_DB asyncTaskDao;

        insertAsyncTask(Dao_DB dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final TaskModel... params) {
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
