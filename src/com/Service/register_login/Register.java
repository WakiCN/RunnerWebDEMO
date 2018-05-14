package com.Service.register_login;

import com.Controller.Controller;
import com.Dao.HibernateEntity.User.User;
import com.Dao.HibernateEntity.User.User_Sex;
import com.Dao.HibernateSessionFactory;
import com.Service.Util.Md5.Md5Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 解决注册所用的类
 *
 * @author Waki
 */
public class Register extends Controller {
    /**
     * @param req 需要携带Vaildcode属性用来验证验证码是否正确
     */
    public void Register(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ValidCodeStatus vc = new ValidCodeStatus();
        String vaildcode = vc.VerifyServerVaildCode(req);
        SessionFactory sf = HibernateSessionFactory.getSf();
        Session se = sf.openSession();
        if (vaildcode.equals("验证码填写正确")) {
            UserDao(req, resp, se);
        } else {
            resp.getWriter().print("验证码错误，请重新填写");
        }
    }

    /**
     * 调用Dao层代码持久化该用户数据
     */
    private void UserDao(HttpServletRequest req, HttpServletResponse resp, Session se) {
        try {
            se.beginTransaction();
            se.save(setValue(req));
            se.getTransaction().commit();
            resp.getWriter().print("注册完成，现在进入登录页面");
        } catch (IOException io) {
            io.printStackTrace();
        } catch (Exception e) {
            se.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            se.close();
        }
    }

    /**
     * 前台值传入服务端并实例化用户实体类填充数据
     */
    private User setValue(HttpServletRequest req) {
        User us = new User();
        User_Sex userSex = new User_Sex();
        if (req.getParameter("Sex").equals("女"))
            userSex.setSex_Id(1);
        else
            userSex.setSex_Id(2);
        us.setId(0);
        us.setName(req.getParameter("Name"));
        us.setPassword(Md5Message.getMD5(req.getParameter("PassWord")));
        us.setRegDate(new Date());
        us.setUs(userSex);
        us.setBirthDate(new Date());
        us.setLevel_Id(0);
        us.setIsban(0);
        us.setEmail((String) req.getSession().getAttribute("UserEmail"));
        return us;
    }
}
