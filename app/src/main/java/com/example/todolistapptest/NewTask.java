package com.example.todolistapptest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewTask extends AppCompatActivity {


    Button create;
    Button cancel;
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        Intent i = getIntent();

        create = findViewById(R.id.ButtonCreate);
        cancel = findViewById(R.id.ButtonCancel);
        name = findViewById(R.id.taskName);

        AlertDialog.Builder error = new AlertDialog.Builder(this);
        error.setTitle("ERROR");
        error.setMessage("No task name entered");
        error.setCancelable(true);
        error.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //dismiss the dialog
                    }
                });
        error.create();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED, i);
                finish();
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = name.getText().toString();
                if (!s.equals("")) {
                    i.putExtra("title", s);
                    setResult(RESULT_OK, i);
                    finish();
                }
                else{
                    error.show();
                }
            }
        });
    }
}