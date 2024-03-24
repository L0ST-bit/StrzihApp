package com.example.strzihapp;

import androidx.annotation.RequiresPermission;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface Dao_DB {

    @Insert
    void insert(TaskModel task);

    @Update
    void update(TaskModel task);

    @Delete
    void delete(TaskModel task);

    @Query("SELECT * FROM NotesTable")
    LiveData<List<TaskModel>> getAllTasks();

    @Query("SELECT * FROM NotesTable WHERE id = :taskId")
    LiveData<TaskModel> getTaskById(int taskId);
}
