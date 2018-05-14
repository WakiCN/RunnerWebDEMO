package com.Dao.HibernateEntity.Message;

import java.util.Date;

public class News_Message {
    private int message_Id;
    private int news_Id;
    private int id;
    private Date message_Date;
    private String message_text;
    private int message_display;

    public int getMessage_Id() {
        return message_Id;
    }

    public void setMessage_Id(int message_Id) {
        this.message_Id = message_Id;
    }

    public int getNews_Id() {
        return news_Id;
    }

    public void setNews_Id(int news_Id) {
        this.news_Id = news_Id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getMessage_Date() {
        return message_Date;
    }

    public void setMessage_Date(Date message_Date) {
        this.message_Date = message_Date;
    }

    public String getMessage_text() {
        return message_text;
    }

    public void setMessage_text(String message_text) {
        this.message_text = message_text;
    }

    public int getMessage_display() {
        return message_display;
    }

    public void setMessage_display(int message_display) {
        this.message_display = message_display;
    }
}
