package com.example.todolistapptest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String FILE_NAME = "tasks.db";
    public static final String TASK_TABLE = "TASK_TABLE";
    public static final String COLUMN_TASK_NAME = "TASK_NAME";

    public DataBaseHelper(@Nullable Context context) {
        super(context, FILE_NAME, null, 1);
    }

    // called the first time a database is accessed
    // create a new database here
    @Override
    public void onCreate(SQLiteDatabase db) {
        String statement = "CREATE TABLE " + TASK_TABLE +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TASK_NAME + " TEXT)";
        db.execSQL(statement);
    }

    // called when database version number changes
    // provides forward compatibility
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    // Add a task to the task database file
    public boolean addTask(Task task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TASK_NAME, task.name);

        long insert = db.insert(TASK_TABLE, null, cv);

        db.close();

        if (insert == -1){
            return false;
        }
        else {
            task.id = insert;
            return true;
        }
    }

    // Delete a task from the task database file
    public boolean deleteTask(long id){

        SQLiteDatabase db = this.getWritableDatabase();
        String q = "DELETE FROM " + TASK_TABLE +
                " WHERE ID = " + id;

        Cursor c = db.rawQuery(q, null);
        boolean b = c.moveToFirst();

        c.close();
        db.close();

        return b;
    }

    // Get all tasks from the task database file
    public List<Task> getTasks(){
        List<Task> tasks = new ArrayList<>();

        // get data from database
        String q = "SELECT * FROM " + TASK_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(q, null);

        if (c.moveToFirst()){
            // loop through results
            do {
                int taskID = c.getInt(0);
                String taskName = c.getString(1);
                tasks.add(new Task(taskName, taskID));
            } while (c.moveToNext());
        }

        c.close();
        db.close();

        return tasks;
    }
}
