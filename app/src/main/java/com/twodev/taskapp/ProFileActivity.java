package com.twodev.taskapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.twodev.taskapp.models.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static android.graphics.Color.TRANSPARENT;
import static android.graphics.Color.colorSpace;

public class ProFileActivity extends AppCompatActivity {
    private static final int PICK_IMAGE = 1;
    EditText editName;
    ImageView imageView;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_file);
        editName = findViewById(R.id.editname);
        imageView = findViewById(R.id.image);

//        getData();
        getData2();
    }

    //вариант 2: получение данных из базы данных (этот вариант лучше так как обновлятеся in realTime)
    private void getData2() {
        String uid = FirebaseAuth.getInstance().getUid();
        FirebaseFirestore.getInstance().collection("users").document("BekulanUserId").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot.exists()) {
                    User user = documentSnapshot.toObject(User.class);
                    editName.setText(user.getName() + " " + user.getAge());
                }
            }
        });

    }

    //вариант 1: получение данных из базы данных
    private void getData() {
        String uid = FirebaseAuth.getInstance().getUid();
        FirebaseFirestore.getInstance().collection("users").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    User user = task.getResult().toObject(User.class);
//                    String name = task.getResult().getString("name");
                    editName.setText(user.getName() + " " + user.getAge());
                }
            }
        });

    }

    public void onClick(View view) {
        String uid = FirebaseAuth.getInstance().getUid();
        String name = editName.getText().toString().trim();
        User user = new User(name, 18, null);
//        Map<String,Object> map = new  HashMap<>();
//        map.put("name","Bekulan");
//        map.put("age",18);
        FirebaseFirestore.getInstance().collection("users").document("BekulanUserId").set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(ProFileActivity.this, "success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProFileActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void imageOnClick(View view) {
        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(gallery, "Select Picture"), PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imageView.setImageBitmap(bitmap);
                imageView.setBackgroundColor(Color.TRANSPARENT);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
