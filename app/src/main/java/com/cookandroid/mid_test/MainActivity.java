package com.cookandroid.mid_test;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    DBHelper dbHelper;
    TextView tv1;
    EditText text1,text2;

    ListView lvtodos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(MainActivity.this, 1);
        tv1 = (TextView) findViewById(R.id.tv1);
        text1 = (EditText) findViewById(R.id.title);
        text2 = (EditText) findViewById(R.id.content);
        lvtodos = (ListView) findViewById(R.id.lvtodo);
//        List<Todo_list> todos = dbHelper.get();
//        lvtodos.setAdapter(new TodoListAdapter(todos,MainActivity.this));
    }

    public void change_page(View v){
        Intent intent = new Intent(getApplicationContext(), Dialog.class);
//        intent.putExtra("title",text1.getText().toString());
//        intent.putExtra("content",text2.getText().toString());
        startActivity(intent);
    }

    public void db_click(View v){
        switch (v.getId()){
            case R.id.get:
                List<Todo_list> todos = dbHelper.get();
                lvtodos.setAdapter(new TodoListAdapter(todos,MainActivity.this));
                break;
            case R.id.reset:
                dbHelper.reset();
                PackageManager packageManager = getPackageManager();
                Intent intent = packageManager.getLaunchIntentForPackage(getPackageName());
                ComponentName componentName = intent.getComponent();
                Intent mainIntent = Intent.makeRestartActivityTask(componentName);
                startActivity(mainIntent);
                System.exit(0);
                break;
        }
    }

    public void open_dialog(View v){
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setView(R.layout.dialog)
                .create()
                .show();
    }

    private  class TodoListAdapter extends BaseAdapter{
        private List<Todo_list> todos;
        private Context context;

        Integer[] Icon = { R.drawable.todo2, R.drawable.todo3,
                R.drawable.todo4, R.drawable.todo5, R.drawable.todo7,
                R.drawable.todo7, R.drawable.todo8, R.drawable.todo9};

        public TodoListAdapter(List<Todo_list> todos,Context context){
            this.todos = todos;
            this.context = context;
        }

        @Override
        public int getCount(){
            return this.todos.size();
        }

        @Override
        public Object getItem(int position){
            return this.todos.get(position);
        }

        @Override
        public long getItemId(int position){
            return position;
        }

        @Override
        public View getView(int position, View convertView , ViewGroup parent){
            SingleItemView single = null;
            if(convertView == null){
                single = new SingleItemView((getApplicationContext()));
            }
            else{
                single = (SingleItemView) convertView;
            }
            Todo_list todo = (Todo_list)getItem(position);
            single.setTitle(todo.getTitle());
            single.setContent(todo.getContent());
            single.setImge(Icon[todo.getId()]);
            single.setDate(todo.getdate());
            System.out.println("날짜 : "+todo.getdate());
            single.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(getApplicationContext(), edit_todo.class);
                    intent.putExtra("title",todo.getTitle());
                    intent.putExtra("content",todo.getContent());
                    intent.putExtra("date",todo.getdate());
                    intent.putExtra("id",todo.getId());
                    startActivity(intent);

                }
            });

//            TextView textview = new TextView(context);
//            textview.setLayoutParams(new ViewGroup.LayoutParams(1000,300));
//            textview.setPadding(1,3,1,3);
//
//            textview.setText(todo.getTitle());
//            textview.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    System.out.println(todo.getContent());
//                }
//            });
//            final int pos = position;

            return single;
        }
    }

}