package com.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 控制层
 * 控制前台调用后台代码的简单模块
 */
public class Controller extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mName = req.getParameter("MName");
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        try {
            Method m;
            if (mName.equals("printVaildCode")) {         //前台获取验证码
                m = this.getClass().getDeclaredMethod(mName, HttpServletRequest.class, HttpServletResponse.class);
                m.invoke(this, req, resp);
            } else if (mName.equals("Login")) {                //传入账户密码以及验证码值来确定登录状态
                m = this.getClass().getDeclaredMethod(mName, HttpServletRequest.class, HttpServletResponse.class);
                m.invoke(this, req, resp);
            } else if (mName.equals("printCodeEmail")) {        //打印图形验证码到前台
                m = this.getClass().getDeclaredMethod(mName, HttpServletRequest.class, HttpServletResponse.class);
                m.invoke(this, req, resp);
            } else if (mName.equals("Register")) {              //注册使用的方法
                m = this.getClass().getDeclaredMethod(mName, HttpServletRequest.class, HttpServletResponse.class);
                m.invoke(this, req, resp);
            } else if (mName.equals("getNews")) {                //获取新闻表
                m = this.getClass().getDeclaredMethod(mName, HttpServletRequest.class, HttpServletResponse.class);
                m.invoke(this, req, resp);
            } else if (mName.equals("getNew")) {                  //获取对应的新闻
                m = this.getClass().getDeclaredMethod(mName, HttpServletRequest.class, HttpServletResponse.class);
                m.invoke(this, req, resp);
            } else
                ShowError(resp);        //如果有人篡改网页方法控制源码并且没有成功则进入这个页面
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    private void ShowError(HttpServletResponse resp) throws IOException {
        System.out.println("有人篡改网页源码");
        resp.getWriter().print("小调皮，别乱改了");
    }
}
