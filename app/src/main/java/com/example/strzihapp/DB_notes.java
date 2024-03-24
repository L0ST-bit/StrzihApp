package com.example.strzihapp;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

@Database(entities = {TaskModel.class}, version = 1, exportSchema = false)
public abstract class DB_notes extends RoomDatabase {
    public abstract Dao_DB notesDao();

    private static volatile DB_notes INSTANCE;

    static DB_notes getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DB_notes.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    DB_notes.class, "Notes_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

