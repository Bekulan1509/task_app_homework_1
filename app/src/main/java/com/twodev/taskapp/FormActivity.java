package com.twodev.taskapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.twodev.taskapp.R;
import com.twodev.taskapp.models.Task;

public class FormActivity extends AppCompatActivity {
    private EditText editTitle;
    private EditText editDesc;

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


    }

    public void onClick(View view) {
        String title = editTitle.getText().toString().trim();
        String desc = editDesc.getText().toString().trim();
        if (title.isEmpty()|| desc.isEmpty()){
            Toast.makeText(this,"fill in Task and description task",Toast.LENGTH_LONG).show();
        }else {
            Task task = new Task(title, desc);
            Intent intent = new Intent();
            intent.putExtra("task", task);
            setResult(RESULT_OK, intent);
            finish();
        }


    }
}
