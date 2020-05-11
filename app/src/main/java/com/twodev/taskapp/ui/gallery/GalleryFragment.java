package com.twodev.taskapp.ui.gallery;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.twodev.taskapp.R;
import com.twodev.taskapp.models.FileNames;
import com.twodev.taskapp.models.Task;
import com.twodev.taskapp.ui.home.TaskAdapter;

import java.io.File;
import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private GalleryAdapter adapter;
    private ArrayList<FileNames> list = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel = ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new GalleryAdapter(list);
        recyclerView.setAdapter(adapter);

        getPermissions();
    }

    private void getPermissions() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
getFiles();
        }else
             requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},101);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==101){
            getPermissions();
        }
    }

    private void getFiles() {
        File folder = new File(Environment.getExternalStorageDirectory(), "DCIM");
//       if (!folder.exists()){
//            folder.mkdir();
//        }
        for (File file : folder.listFiles()) {

            list.add(new FileNames(file.getAbsolutePath()));
            adapter.notifyDataSetChanged();
            Log.d("lala", "getFiles: " + file.getAbsolutePath());
        }
    }
}
