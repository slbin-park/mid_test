package com.cookandroid.mid_test;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context, int version) {
            super(context, "Todolist", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE  Todolist ( title CHAR(20) PRIMARY KEY, content CHAR(100) , id NUMBER(3) , date DATE);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Todolist");
        onCreate(db);
    }

    public int save(Todo_list todo){
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.execSQL("INSERT INTO Todolist VALUES('"
                    + todo.getTitle()
                    + "', '"
                    + todo.getContent()
                    + "',"
                    + todo.getId()
                    + ",'"
                    + todo.getdate()
                    + "')");
            db.close();
            return 1;
        }
        catch (Exception e){
            db.close();
            return 0;
        }
    }

    public List<Todo_list> get(){
        SQLiteDatabase db = getWritableDatabase();
        String result = "";
        Cursor cursor = db.rawQuery("SELECT * FROM Todolist " +
                "ORDER BY date desc",null);

        List<Todo_list> todos = new ArrayList<Todo_list>();
        Todo_list todo = null;

        while (cursor.moveToNext()){
            todo = new Todo_list();
            todo.setTitle(cursor.getString(0));
            todo.setContent(cursor.getString(1));
            todo.setId((cursor.getInt(2)));
            todo.setdate(cursor.getString(3));
            todos.add(todo);
        }
        return todos;
    }

    public void reset(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE  FROM Todolist ");
        db.close();
    }

    public void delete(String title){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE  FROM Todolist WHERE title = '"
                + title
                + "';");
        System.out.println("DELETE FROM Todolist WHERE title = '"
                + title
                + "';");
        db.close();
    }

    public void update(Todo_list todo){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE Todolist SET title = '"
                + todo.getTitle()
                + "',content =  '"
                + todo.getContent()
                +"',id = "
                + todo.getId()
                + ",date = '"
                +todo.getdate()
                + "' " +
                "WHERE title = '"
                +todo.getTitle()
                +"'");
        db.close();
    }
}
