package com.cookandroid.mid_test;

import android.content.Context;
import android.media.Image;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SingleItemView extends LinearLayout {
    TextView textview,textview2,date;
    ImageView imageView;

    public SingleItemView(Context context){
        super(context);
        init(context);
    }
    public SingleItemView(Context context, @Nullable AttributeSet attrs){
        super(context,attrs);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.single_itme,this,true);
        textview = findViewById(R.id.text1);
        textview2 = findViewById(R.id.text2);
        date = findViewById(R.id.date);
        imageView = findViewById(R.id.imageview);
    }

    public void setTitle(String title){
        textview.setText(title);
    }
    public void setContent(String content){
        textview2.setText(content);
    }
    public void setImge(int img){
        imageView.setImageResource(img);
    }
    public void setDate(String getdate){date.setText(getdate);}
}
