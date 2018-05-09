package com.Service.Util.Email;

/**
 * 为解决邮件发送问题所存在的JavaBean类
 * @author Waki
 * */
public class EmailUser_Bean {
    private String Name,Email;
    /**
     * @param name 如何称呼收件人
     * @param email 收件人邮件地址
     * */
    public EmailUser_Bean(String name, String email) {
        Name = name;
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
