package com.example.tdd;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Iterator;
import com.example.tdd.R;

public class MainActivity extends AppCompatActivity {

    private ArrayList<TaskItem> todoList;
    private ArrayAdapter<TaskItem> todoAdapter;
    private EditText todoEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoList = new ArrayList<>();
        todoAdapter = new ArrayAdapter<>(this, R.layout.task_item, R.id.taskTextView, todoList);

        ListView todoListView = findViewById(R.id.todoListView);
        todoListView.setAdapter(todoAdapter);

        todoEditText = findViewById(R.id.todoEditText);

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask();
            }
        });

        Button clearAllButton = findViewById(R.id.clearAllButton);
        clearAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllTasks();
            }
        });
    }

    private void addTask() {
        String task = todoEditText.getText().toString().trim();
        if (!task.isEmpty()) {
            TaskItem taskItem = new TaskItem(task);
            todoList.add(taskItem);
            updateListUI();
            todoEditText.getText().clear();
        }
    }

    private void clearAllTasks() {
        todoList.clear();
        updateListUI();
        Toast.makeText(this, "All tasks cleared", Toast.LENGTH_SHORT).show();
    }

    private void updateListUI() {
        todoAdapter.notifyDataSetChanged();
    }
}

class TaskItem {
    private String task;

    TaskItem(String task) {
        this.task = task;
    }

    String getTask() {
        return task;
    }

    @Override
    public String toString() {
        return task;
    }
}
