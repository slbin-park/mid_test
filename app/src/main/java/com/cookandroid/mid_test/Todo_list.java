package com.cookandroid.mid_test;

public class Todo_list {
    private int id;
    private String title;
    private String content;
    private String date;

    public int getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getContent(){
        return content;
    }
    public String getdate(){return date;}

    public void setId(int id){this.id = id;}
    public void setTitle(String title){
        this.title = title;
    }
    public void setContent(String content){
        this.content = content;
    }
    public void setdate(String date){this.date = date;}

}
