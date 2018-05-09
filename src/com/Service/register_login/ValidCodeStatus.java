package com.Service.register_login;

import com.Controller.Controller;
import com.Service.Util.DateFormat.Dispose;
import com.Service.Util.Email.EmailUser_Bean;
import com.Service.Util.Email.SendEmail;
import com.Service.Util.VaildCode.RandomValidateCodeUtil;
import com.Service.Util.VaildCode.ValidDateCode_Bean;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 解决各种验证码的录入以及验证问题
 *
 * @author Waki
 */
public class ValidCodeStatus extends Controller {
    /**
     * 服务端获取验证码，并填入相应的HttpServletRequest.getSession()里
     */
    private ValidDateCode_Bean getServerVaildCode(HttpServletRequest req) {
        RandomValidateCodeUtil ran = new RandomValidateCodeUtil();      //绘制验证码所用
        ValidDateCode_Bean vb = new ValidDateCode_Bean();
        vb.setSessionId(req.getSession().getId());          //把该验证码和所属客户端SessionID绑定起来
        vb.setCreateTime(new Date().getTime());             //创建该验证码的时间
        vb.setValidTime((5 * 60 * 1000));                   //添加该验证码的有效时间
        vb = ran.getRandcode(vb);                           //绘制验证码图片
        req.getSession().setAttribute(RandomValidateCodeUtil.RANDOMCODEKEY, vb);        //保存进所属客户端SessionID
        return vb;
    }

    /**
     * 服务端验证HttpServletRequest.getSession()里对应的验证码
     */
    public String VerifyServerVaildCode(HttpServletRequest req) {
        ValidDateCode_Bean vb = (ValidDateCode_Bean) req.getSession().getAttribute(RandomValidateCodeUtil.RANDOMCODEKEY);
        if (vb == null)
            return "您并没有任何需要验证的验证码";
        if (vb.isVaild()) {
            return vb.getVailCode().equalsIgnoreCase(req.getParameter("Vaildcode")) ? "验证码填写正确" + "" : "验证码错误";
        } else
            return "验证码失效";
    }

    /**
     * 打印图形验证码到前台
     */
    public void printVaildCode(HttpServletRequest req, HttpServletResponse resp) {
        ValidDateCode_Bean vb = getServerVaildCode(req);
        try {
            ImageIO.write(vb.getBi(), "JPEG", resp.getOutputStream());
            vb.setBi(null);         //清空掉图形验证码用来节省内存占用
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证Email字符串的方法
     */
    private boolean ValidateEmail(String Email) {
        if (Email == null)
            return false;
        if (Email.indexOf("@") > 0) {
            //1@xx.cn
            if (Email.length() > 5) {
                return true;
            } else
                return false;
        } else
            return false;
    }

    /**
     * 发送验证码邮件的方法并绑定给HttpSession
     */
    private boolean SendVaildCodeEmail(HttpServletRequest req) {
        RandomValidateCodeUtil rv = new RandomValidateCodeUtil();
        ValidDateCode_Bean vb = new ValidDateCode_Bean();
        rv.getRandcode(vb);
        vb.setBi(null);
        vb.setSessionId(req.getSession().getId());
        Date date = new Date();
        vb.setCreateTime(date.getTime());
        vb.setValidTime(5 * 60 * 1000);
        String EmailMessage = "您好,→→→→→" + vb.getVailCode() + "←←←←←是您的验证码，请您在五分钟内填入注册页面中的验证码栏(不区分大小写),服务端发送时间" + Dispose.formateDate(date, Dispose.SECONDS);
        String Email = req.getParameter("UserEmail");
        if (!ValidateEmail(Email))
            return false;
        EmailUser_Bean eb = new EmailUser_Bean(req.getParameter("UserName"), Email);
        SendEmail se = new SendEmail(eb, EmailMessage);
        try {
            se.SendEmail();
            req.getSession().setAttribute(RandomValidateCodeUtil.RANDOMCODEKEY, vb);
            req.getSession().setAttribute("UserEmail", Email);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 前台发送邮件验证码
     *
     * @param req 里面务必有UserName属性和UserEmail属性
     */
    public void printCodeEmail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (SendVaildCodeEmail(req)) {
            resp.getWriter().print("发送成功,请注意查收您的邮件箱(如果没收到请在垃圾箱里看看)");
        } else {
            resp.getWriter().print("发送失败");
        }
    }
}
