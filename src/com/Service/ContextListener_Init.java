package com.Service;

import com.Dao.HibernateSessionFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 做一些跟着服务器启动便被初始化的工作
 */
@WebListener
public class ContextListener_Init implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        new HibernateSessionFactory().initDataBase();       //主动初始化Hibernate框架的Session工厂
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        HibernateSessionFactory.CloseSessionFactory();          //关闭Hibernate框架的Session工厂
    }
}
