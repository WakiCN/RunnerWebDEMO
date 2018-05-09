package com.Service;

import com.Dao.HibernateEntity.User;

import java.util.HashMap;
/**
 * @// TODO: 2018/5/8 保存用户信息所用集合
 * */
public class UserController {
    private static HashMap<String,User> ustatus;
    static {
        ustatus=new HashMap<>();
        System.out.println("用户身份集合已初始化：使用SessionId(String)+User对象填充");
    }
    public static HashMap<String,User> getUstatus(){return ustatus;}
}
