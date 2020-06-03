package com.twodev.taskapp;

import android.app.Application;

import androidx.multidex.MultiDexApplication;
import androidx.room.Room;

import com.twodev.taskapp.room.AppDataBase;

public class App extends MultiDexApplication {

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
