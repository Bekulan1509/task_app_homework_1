package com.twodev.taskapp.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.twodev.taskapp.MainActivity;
import com.twodev.taskapp.R;
import com.twodev.taskapp.models.Task;
import com.twodev.taskapp.ui.OnItemClickListener;

import java.util.ArrayList;
import java.util.Collection;

public class HomeFragment extends Fragment  {

    private TaskAdapter adapter;
    private ArrayList<Task> list = new ArrayList<>();



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        adapter = new TaskAdapter(list);
        recyclerView.setAdapter(adapter);
        //  adapter.setOnItemClickListener(HomeFragment.this);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       Task task1 = (Task) data.getSerializableExtra("task");
      // Task task = (Task) getArguments().getSerializable("task");
       list.add(task1);
       adapter.notifyDataSetChanged();
    }

}



