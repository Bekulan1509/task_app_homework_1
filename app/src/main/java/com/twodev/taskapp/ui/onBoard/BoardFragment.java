package com.twodev.taskapp.ui.onBoard;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.twodev.taskapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoardFragment extends Fragment {

    public BoardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_board, container, false);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textTitle = view.findViewById(R.id.textTitle);
        ImageView imageView = view.findViewById(R.id.imageView);
        Button button = view.findViewById(R.id.button);
        int pos = getArguments().getInt("pos");
        switch (pos) {
            case 0:
                textTitle.setText("Hello");
                imageView.setImageResource(R.drawable.naruto_1);
                button.setVisibility(View.INVISIBLE);
                break;
            case 1:
                textTitle.setText("how are you?");
                imageView.setImageResource(R.drawable.naruto_2);
                button.setVisibility(View.INVISIBLE);
                break;
            case 2:
                imageView.setImageResource(R.drawable.naruto_3);
                textTitle.setText("what are you doing?");

                break;
        }
    }
}
