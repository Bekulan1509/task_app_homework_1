package com.twodev.taskapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.twodev.taskapp.ui.firestore.FireStoreFragment;

public class FireStore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fire_store_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, FireStoreFragment.newInstance())
                    .commitNow();
        }
    }
}
