package com.Dao.HibernateEntity;

import java.util.Date;

public class User {
    private int id, sex_Id, level_Id, isban;
    private String name, password, email;
    private Date regDate, birthDate;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSex_Id() {
        return sex_Id;
    }

    public void setSex_Id(int sex_Id) {
        this.sex_Id = sex_Id;
    }

    public int getLevel_Id() {
        return level_Id;
    }

    public void setLevel_Id(int level_Id) {
        this.level_Id = level_Id;
    }

    public int getIsban() {
        return isban;
    }

    public void setIsban(int isban) {
        this.isban = isban;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
