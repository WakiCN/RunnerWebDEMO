package com.testPackage;

import com.Dao.HibernateEntity.User_Sex;
import com.Dao.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class testmain {
    public static void main(String[] args) throws Exception {
        SessionFactory sf=HibernateSessionFactory.getSf();
        Session se=sf.openSession();
        se.beginTransaction();
        try {
            User_Sex us=new User_Sex();
            us.setSex_Id(1);
            us.setSexName("男");
            se.save(us);
        }catch (Exception e){
            se.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            se.getTransaction().commit();
            se.close();
        }
    }


}
