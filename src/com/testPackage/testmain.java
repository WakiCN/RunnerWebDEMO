package com.testPackage;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.text.SimpleDateFormat;

public class testmain {
    private SessionFactory sessionFactory;

    public static void main(String[] args) throws Exception {
        SimpleDateFormat sm=new SimpleDateFormat();
    }

    protected void initDataBase() throws Exception {
        //为应用程序设置了一个SessionFactory！
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() //配置文件来自 hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            //注册表将被SessionFactory销毁，但是我们在构建SessionFactory时遇到了麻烦，所以手动破坏它。
            StandardServiceRegistryBuilder.destroy(registry);
            e.printStackTrace();
        }
    }
}
