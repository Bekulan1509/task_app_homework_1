package com.twodev.taskapp.ui.onBoard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.twodev.taskapp.MainActivity;
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
        LinearLayout linearLayout = view.findViewById(R.id.linear);
        int pos = getArguments().getInt("pos");
        switch (pos) {
            case 0:
                textTitle.setText("Hello");
                textTitle.setTextColor(Color.WHITE);
                imageView.setImageResource(R.drawable.naruto_1);
                button.setVisibility(View.INVISIBLE);

                linearLayout.setBackgroundColor(getResources().getColor(R.color.board_1_color));

                break;
            case 1:
                textTitle.setText("how are you?");
                textTitle.setTextColor(Color.WHITE);
                imageView.setImageResource(R.drawable.naruto_2);
                button.setVisibility(View.INVISIBLE);

                linearLayout.setBackgroundColor(getResources().getColor(R.color.board_2_color));

                break;
            case 2:
                imageView.setImageResource(R.drawable.naruto_3);

                linearLayout.setBackgroundColor(getResources().getColor(R.color.board_3_color));


                textTitle.setText("what are you doing?");
                textTitle.setTextColor(Color.WHITE);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent =new Intent(getContext(), MainActivity.class);
                        saveIsShown();
                        startActivity(intent);
                       getActivity().finish();


                    }
                });

                break;
        }
    }

    private void saveIsShown() {
        SharedPreferences preferences = getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
        preferences.edit().putBoolean("isShown", true).apply();
    }
}
