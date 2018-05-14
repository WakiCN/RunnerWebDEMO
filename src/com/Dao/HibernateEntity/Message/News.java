package com.Dao.HibernateEntity.Message;

import java.util.Date;

public class News {
    private int news_Id;
    private Date news_addDate;
    private String news_Text;
    private String news_Title;
    private int news_display;

    public String getNews_Title() {
        return news_Title;
    }

    public void setNews_Title(String news_Title) {
        this.news_Title = news_Title;
    }

    public int getNews_Id() {
        return news_Id;
    }

    public void setNews_Id(int news_Id) {
        this.news_Id = news_Id;
    }

    public Date getNews_addDate() {
        return news_addDate;
    }

    public void setNews_addDate(Date news_addDate) {
        this.news_addDate = news_addDate;
    }

    public String getNews_Text() {
        return news_Text;
    }

    public void setNews_Text(String news_Text) {
        this.news_Text = news_Text;
    }

    public int getNews_display() {
        return news_display;
    }

    public void setNews_display(int news_display) {
        this.news_display = news_display;
    }
}
