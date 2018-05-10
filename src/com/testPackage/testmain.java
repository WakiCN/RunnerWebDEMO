package com.testPackage;

import com.Dao.HibernateEntity.User;
import com.Dao.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class testmain {
    public static void main(String[] args) {
        SessionFactory sf=HibernateSessionFactory.getSf();
        Session se=sf.openSession();
        se.beginTransaction();
        try {
            String hql="from User where name='aaa'";
            List<User> li=se.createQuery(hql).list();
            User us=li.get(0);
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
