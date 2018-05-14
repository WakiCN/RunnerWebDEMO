package com.Service.register_login;

import com.Controller.Controller;
import com.Dao.HibernateEntity.User.User;
import com.Dao.HibernateSessionFactory;
import com.Service.Util.Md5.Md5Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 解决登录所用的类
 *
 * @author Waki
 */
public class Login extends Controller {
    /**
     * 通过HttpSeesion里的辨别登录状态
     */
    private static final String LOGGINGSTATUS = "LOGGINGSTATUS";
    private static final String LOGGINGNAME = "LOGGINGNAME";

    /**
     * 传入账户密码以及验证码值来确定登录状态
     * Request里需要包含Vaildcode--验证码值
     * Name--用户名
     * PassWord--密码
     */
    public void Login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = getLOGGINGNAME(req);
        if (name != null) {
            resp.getWriter().print("您已登录");
            return;
        }
        ValidCodeStatus vs = new ValidCodeStatus();
        if (vs.VerifyServerVaildCode(req).equals("验证码填写正确")) {
            if (getUser(req.getParameter("Name"), Md5Message.getMD5(req.getParameter("PassWord"))).equals("账户不存在")) {
                resp.getWriter().print("账户不存在");
            } else if (getUser(req.getParameter("Name"), Md5Message.getMD5(req.getParameter("PassWord"))).equals("密码错误")) {
                resp.getWriter().print("密码错误");
            } else {
                req.getSession().setAttribute(LOGGINGSTATUS, true);
                req.getSession().setAttribute(LOGGINGNAME, req.getParameter("Name"));
                resp.getWriter().print("登录成功");
                //TODO 有一种状态没有解决，如果账户被ban怎么办
            }
        } else {
            resp.getWriter().print("您的验证码输入有误，请重新输入验证码");
        }

    }

    /**
     * 获取该HttpSession的登录状态
     *
     * @return true:登录状态下有值，便代表了已登录 false则相反
     */
    private boolean getLOGGINGSTATUS(HttpServletRequest req) {
        return req.getSession().getAttribute(LOGGINGSTATUS) != null ? true : false;
    }

    /**
     * 通过判断该HttpSession的登录状态，来取对应的登录值
     *
     * @return 用户名或者没有登录的Null
     */
    public String getLOGGINGNAME(HttpServletRequest req) {
        return getLOGGINGSTATUS(req) ? (String) req.getSession().getAttribute(LOGGINGNAME) : null;
    }

    /**
     * 验证传入的两个字符串是否在数据库里存在以及账号密码是否正确
     *
     * @return 状态字符串
     */
    public String getUser(String name, String Pass) {
        SessionFactory sf = HibernateSessionFactory.getSf();
        Session se = sf.openSession();
        String hql = "from User where name=" + name;
        try {
            se.beginTransaction();
            User us = (User) se.createQuery(hql).uniqueResult();
            if (us != null) {
                if(us.getPassword().equals(Pass)){
                    if (us.getIsban()==0)
                        return "登录成功";
                    else
                        return "账户被ban";
                }else
                    return "密码错误";
            } else
                return "账户不存在";
        } finally {
            se.getTransaction().commit();
            se.close();
        }
    }
}
