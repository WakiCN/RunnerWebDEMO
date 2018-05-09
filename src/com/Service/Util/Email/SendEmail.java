package com.Service.Util.Email;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * 发送邮件所用类
 *
 * @author Waki
 */
public class SendEmail {
    private EmailUser_Bean user;
    private String EmailMessage;

    public SendEmail(EmailUser_Bean user, String emailMessage) {
        this.user = user;
        EmailMessage = emailMessage;
    }

    public void SendEmail() throws Exception {
        String MyEmailAccount = "RunnerNet@163.com";
        String MyEmailPass = "JavaRunnerNet666";
        String MyEmailSMTPHost = "smtp.163.com";
        Properties props = new Properties();//参数配置
        props.setProperty("mail.transport.protocol", "smtp");//使用协议
        props.setProperty("mail.smtp.host", MyEmailSMTPHost);
        props.setProperty("mail.smtp.auth", "true");
        final String smtpPort = "465";    //SMTP服务器的端口
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);
        //根据配置创建会话对象，用于和邮件服务器交互
        Session session = Session.getInstance(props);
        session.setDebug(false);
        //创建一封邮件
        MimeMessage mimeMessage = createMimeMessage(session, MyEmailAccount, user, EmailMessage);
        //根据交互对象获取邮件传输对象
        Transport transport = session.getTransport();
        //使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
        transport.connect(MyEmailAccount, MyEmailPass);
        //发送邮件, 发到所有的收件地址
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
    }

    /**
     * @param session  和邮件服务器交互对象
     * @param sendMail 发件人邮箱
     * @param user     收件人
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public MimeMessage createMimeMessage(Session session, String sendMail, EmailUser_Bean user, String emailMessage) throws MessagingException, UnsupportedEncodingException {
        //创建一封邮件
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sendMail, "Runner--跑者", "UTF-8"));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(user.getEmail(), user.getName(), "UTF-8"));
        message.setSubject(user.getName() + "欢迎您注册Runner--跑者，这是一封验证码邮件", "UTF-8");//邮件的标题
        message.setContent(emailMessage, "text/html;charset=UTF-8");//邮件的正文
        message.setSentDate(new Date());//邮件发送时间
        message.saveChanges();//保存邮件
        return message;
    }
}
