package com.Service.register_login;

import com.Controller.Controller;
import com.Dao.HibernateEntity.User;
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

        } else {
            resp.getWriter().print("验证码错误，请重新填写");
            return;
        }
    }

    private void UserDao(HttpServletRequest req, Session se) {
        User us = new User();
        //null,'Waki','61bc69fff1287f277a538f504490224d','2018-5-9',1,'1990-1-1',1,0,'1062540709@qq.com'
        us.setName(req.getParameter("Name"));
        us.setPassword(Md5Message.getMD5(req.getParameter("PassWord")));
        us.setRegDate(new Date());
        int sexid = req.getParameter("Sex").equals("女") ? 0 : 1;
        us.setSex_Id(sexid);
        us.setBirthDate(new Date());
        us.setLevel_Id(0);
        us.setIsban(0);
        us.setEmail(req.getParameter("Email"));
        try {
            se.beginTransaction();
            se.save(us);
            se.getTransaction().commit();
        }catch (Exception e){
            se.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            se.close();
        }
    }
}
