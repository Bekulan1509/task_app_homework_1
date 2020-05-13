package com.twodev.taskapp;

import android.app.Application;

import androidx.room.Room;

import com.twodev.taskapp.room.AppDataBase;

public class App extends Application {

    private AppDataBase dataBase;
    public static  App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        dataBase = Room.databaseBuilder(this,AppDataBase.class,"database").allowMainThreadQueries().build();
    }

    public static App getInstance() {
        return instance;
    }

    public AppDataBase getDataBase() {
        return dataBase;
    }
}
