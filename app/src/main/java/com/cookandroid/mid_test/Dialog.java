package com.cookandroid.mid_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class Dialog extends AppCompatActivity {
    DatePicker mDate;
    EditText edt1,edt2;
    DBHelper dbHelper;
    Integer id;
    ImageView imv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_page);
        id =0;
        dbHelper = new DBHelper(Dialog.this, 1);
        imv = (ImageView) findViewById(R.id.select_image);
        edt1 = (EditText) findViewById(R.id.title);
        edt2 = (EditText) findViewById(R.id.content);
        mDate = (DatePicker) findViewById(R.id.datepicker);
    }

    public void date_picker(View v){
        Integer month = mDate.getMonth()+1;
        Todo_list todo = new Todo_list();
        todo.setTitle(edt1.getText().toString());
        todo.setContent(edt2.getText().toString());
        todo.setId(id);
        todo.setdate(mDate.getYear()
                +"-"
                +month.toString()
                +"-"
                + mDate.getDayOfMonth());
        int a  = dbHelper.save(todo);
        if (a==1){
            Toast.makeText(this,  "입력 완료.", Toast.LENGTH_LONG).show();
            finish();
        }
        else{
            Toast.makeText(this,  "제목이 중복됬습니다.", Toast.LENGTH_LONG).show();
        }

//        finish();
    }

    public void Image_click(View v){
        switch (v.getId()){
            case R.id.image0:
                id = 0;
                imv.setImageResource(R.drawable.todo2);
                break;
            case R.id.image1:
                id = 1;
                imv.setImageResource(R.drawable.todo3);
                break;
            case R.id.image2:
                id = 2;
                imv.setImageResource(R.drawable.todo4);
                break;
            case R.id.image3:
                id = 3;
                imv.setImageResource(R.drawable.todo5);
                break;
            case R.id.image4:
                id = 4;
                imv.setImageResource(R.drawable.todo6);
                break;
            case R.id.image5:
                id = 5;
                imv.setImageResource(R.drawable.todo7);
                break;
            case R.id.image6:
                id = 6;
                imv.setImageResource(R.drawable.todo8);
                break;
            case R.id.image7:
                id = 7;
                imv.setImageResource(R.drawable.todo9);
                break;
        }
    }

}
