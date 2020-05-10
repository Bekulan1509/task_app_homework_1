package com.twodev.taskapp.ui;

import com.twodev.taskapp.models.Task;

import java.util.ArrayList;

public interface OnItemClickListener {
    void onItemClickListener(int position);
    void communicateActivityFragment(Task task);

}
