package com.twodev.taskapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.twodev.taskapp.R;
import com.twodev.taskapp.models.Task;
import com.twodev.taskapp.ui.OnItemClickListener;

import java.util.HashMap;
import java.util.Map;

public class FormActivity extends AppCompatActivity {
    private EditText editTitle;
    private EditText editDesc;
    private Task task;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        editTitle = findViewById(R.id.editTitle);
        editDesc = findViewById(R.id.editDesc);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("New task");
        }
        task = (Task) getIntent().getSerializableExtra("task");
        if (task!=null){
            editTitle.setText(task.getTitle());
            editDesc.setText(task.getDesc());
        }
    }

    public void onClick(View view) {
        String title = editTitle.getText().toString().trim();
        String desc = editDesc.getText().toString().trim();

        Map<String,Object> map = new HashMap<>();
        map.put("description",desc);
        map.put("Task",title);
        FirebaseFirestore.getInstance().collection("tasks").add(map);

        if (task!=null){
            task.setTitle(title);
            task.setDesc(desc);
            App.getInstance().getDataBase().taskDao().update(task);
        }
        else {
             task = new Task(title, desc);
             task.setTitle(title);
             task.setDesc(desc);
            App.getInstance().getDataBase().taskDao().insert(task);
        }
        finish();


    }

}
