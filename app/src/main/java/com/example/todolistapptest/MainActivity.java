package com.example.todolistapptest;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button add;
    LinearLayout layout;

    DataBaseHelper DBH = new DataBaseHelper(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = findViewById(R.id.add);
        layout = findViewById(R.id.container);

        loadTasks();

        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                StartCreateTask();
            }
        });
    }

    // Start the "create a task" activity
    private void StartCreateTask(){
        Intent i = new Intent(this, NewTask.class);
        ntRes.launch(i);
    }

    ActivityResultLauncher<Intent> ntRes = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>(){
                @Override
                public void onActivityResult(ActivityResult result){
                    if(result != null && result.getResultCode() == RESULT_OK){
                        String t = result.getData().getStringExtra("title");

                        Task temp = new Task(t);
                        writeFile(temp);

                        addCard(temp);
                    }
                }
            });

    // add a task card
    private void addCard(Task task) {
        final View view = getLayoutInflater().inflate(R.layout.card, null);

        TextView nameView = view.findViewById(R.id.name);
        Button delete = view.findViewById(R.id.delete);
        Button vTask = view.findViewById(R.id.viewTask);

        nameView.setText(task.name);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.removeView(view);
                deleteTask(task);
            }
        });
        vTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTaskView(task);
            }
        });

        layout.addView(view);
    }

    // TODO: expand view task by implementing an "edit task" feature
    // TODO: implement subtasks
    public void startTaskView(Task task){
        Intent i = new Intent(MainActivity.this, ViewTask.class);
        i.putExtra("name", task.name);
        ntRes.launch(i);
    }

    // save a task to storage text file
    public void writeFile(Task t){
        boolean success = DBH.addTask(t);
        String s = "Task saved successfully";
        if (!success)
            s = "Error: task could not be saved";

        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
    }

    // load the tasks from the storage text file
    public void loadTasks(){
        List<Task> tasks = DBH.getTasks();
        for (int i = 0; i < tasks.size(); i++){
            addCard(tasks.get(i));
        }
    }

    // delete a task from the storage text file
    public void deleteTask(Task task){
        DBH.deleteTask(task.id);
    }
}