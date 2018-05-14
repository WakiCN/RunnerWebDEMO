package com.Service.Message;

import com.Dao.HibernateEntity.Message.News;

import java.util.List;

public class NewsBean {
    private boolean goon;
    private List<News> li;

    public boolean isGoon() {
        return goon;
    }

    public void setGoon(boolean goon) {
        this.goon = goon;
    }

    public List<News> getLi() {
        return li;
    }

    public void setLi(List<News> li) {
        this.li = li;
    }
}
