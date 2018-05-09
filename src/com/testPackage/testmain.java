package com.testPackage;

import com.Dao.HibernateEntity.User;
import com.Dao.HibernateEntity.User_Sex;
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
            User us=new User();
            User_Sex u=new User_Sex();
            u.setSexName("女");
            u.setSex_Id(0);
            us.setEmail("1062540709@qq.com");
            us.setLevel_Id(0);
            us.setName("Test5");
            us.setIsban(0);
            us.setPassword("123456789");
            us.setBirthDate(new Date());
            us.setRegDate(new Date());
            us.setId(0);
            us.setUs(u);
            se.save(us);
            se.getTransaction().commit();
            System.out.println(us.getEmail());
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
