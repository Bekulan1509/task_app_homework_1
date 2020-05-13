package com.twodev.taskapp.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.twodev.taskapp.models.Task;

@Database(entities = {Task.class},version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract TaskDao taskDao();
}
