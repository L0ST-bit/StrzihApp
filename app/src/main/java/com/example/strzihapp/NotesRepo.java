package com.example.strzihapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotesRepo {
    private Dao_DB taskDao;
    private LiveData<List<TaskModel>> allTasks;

    private ExecutorService executorService = Executors.newFixedThreadPool(4);

    NotesRepo(Application application) {
        DB_notes db = DB_notes.getDatabase(application);
        taskDao = db.notesDao();
        allTasks = taskDao.getAllTasks();
    }

    LiveData<List<TaskModel>> getAllTasks() {
        return allTasks;
    }




    void insert(TaskModel task) {
        //new insertAsyncTask(taskDao).execute(task);
        executorService.execute(() -> taskDao.insert(task));
    }
    void update(TaskModel task) {
        //new insertAsyncTask(taskDao).execute(task);
        executorService.execute(() -> taskDao.update(task));
    }
    void delete(TaskModel task) {
        //new insertAsyncTask(taskDao).execute(task);
        executorService.execute(() -> taskDao.delete(task));
        //executorService.execute(() -> taskDao.deleteN(task, id));
    }



//    private static class insertAsyncTask extends AsyncTask<TaskModel, Void, Void> {
//        private Dao_DB asyncTaskDao;
//
//        insertAsyncTask(Dao_DB dao) {
//            asyncTaskDao = dao;
//        }
//
//        @Override
//        protected Void doInBackground(final TaskModel... params) {
//            asyncTaskDao.insert(params[0]);
//            return null;
//        }
//    }
}
