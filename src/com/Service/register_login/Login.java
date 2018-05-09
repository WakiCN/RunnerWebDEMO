package com.Service.register_login;

import com.Controller.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 解决登录所用的类
 *
 * @author Waki
 */
public class Login extends Controller {
    public void Login(HttpServletRequest req, HttpServletResponse resp) {
        ValidCodeStatus vs = new ValidCodeStatus();
        try {
            resp.getWriter().print(vs.VerifyServerVaildCode(req));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
