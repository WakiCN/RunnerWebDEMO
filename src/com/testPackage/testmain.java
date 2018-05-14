package com.testPackage;

import com.Dao.HibernateEntity.Message.News;
import com.Dao.HibernateEntity.Message.News_Message;
import com.Dao.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;

public class testmain {
    public static void main(String[] args) {
        SessionFactory sf=HibernateSessionFactory.getSf();
        Session se=sf.openSession();
        se.beginTransaction();
        try {
            News news=new News();
            news.setNews_Id(0);
            news.setNews_addDate(new Date());
            news.setNews_display(0);
            news.setNews_Text("Hello Runner的第一份新闻");
            News_Message newsM=new News_Message();
            newsM.setId(2);
            newsM.setNews_Id(1);
            newsM.setMessage_Id(0);
            newsM.setMessage_display(0);
            newsM.setMessage_text("Hello RunnerMessage2!");
            newsM.setMessage_Date(new Date());
            se.save(news);
            se.save(newsM);
            se.getTransaction().commit();
        }catch (Exception e){
            se.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            se.close();
            sf.close();
            System.out.println("se已关闭");
        }
    }


}
