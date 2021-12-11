package com.cookandroid.mid_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class edit_todo extends AppCompatActivity {
    TextView title;
    EditText content;
    DatePicker date_picker;
    String[] getdate;
    Todo_list todo ;
    Integer id;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_todo_list);

        dbHelper = new DBHelper(edit_todo.this, 1);

        Intent intent = getIntent();
        title = (TextView) findViewById(R.id.edit_title);
        content = (EditText) findViewById(R.id.edit_content);
        date_picker = (DatePicker) findViewById(R.id.edit_date);

        getdate = intent.getExtras().getString("date").split("-");

        /*데이터 수신*/
        title.setText(intent.getExtras().getString("title"));
        content.setText(intent.getExtras().getString("content"));
        id = intent.getExtras().getInt("id");

        // 주의 사항 month는 1을 빼줘야함...
        date_picker.init(Integer.parseInt(getdate[0]) , Integer.parseInt(getdate[1]) -1, Integer.parseInt(getdate[2]), new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        System.out.println(getdate);
                    }
                }
                );
    }

    public void update(View v){
        Integer month = date_picker.getMonth()+1;
        todo = new Todo_list();
        todo.setTitle(title.getText().toString());
        todo.setContent(content.getText().toString());
        todo.setId(id);
        todo.setdate(date_picker.getYear()
                +"-"
                +month.toString()
                +"-"
                + date_picker.getDayOfMonth());
        dbHelper.update(todo);
        Toast.makeText(this,  "수정 완료", Toast.LENGTH_LONG).show();
        finish();
    }
    public void delete(View v){
        dbHelper.delete(title.getText().toString());
        System.out.println(title.getText().toString());
        Toast.makeText(this,  "삭제 완료", Toast.LENGTH_LONG).show();
        finish();
    }
}
