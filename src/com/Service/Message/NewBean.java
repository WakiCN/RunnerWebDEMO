package com.Service.Message;

/**
 * 单个新闻传输对象
 *
 * @author Waki
 */
public class NewBean {
    private String Date;
    private String Title;
    private String Text;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }
}
