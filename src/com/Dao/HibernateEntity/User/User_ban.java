package com.Dao.HibernateEntity.User;

import java.util.Date;

public class User_ban {
    private int ban_Id,ban_adminId,ban_UserId;
    private Date ban_Date,ban_unDate;

    public int getBan_Id() {
        return ban_Id;
    }

    public void setBan_Id(int ban_Id) {
        this.ban_Id = ban_Id;
    }

    public int getBan_adminId() {
        return ban_adminId;
    }

    public void setBan_adminId(int ban_adminId) {
        this.ban_adminId = ban_adminId;
    }

    public int getBan_UserId() {
        return ban_UserId;
    }

    public void setBan_UserId(int ban_UserId) {
        this.ban_UserId = ban_UserId;
    }

    public Date getBan_Date() {
        return ban_Date;
    }

    public void setBan_Date(Date ban_Date) {
        this.ban_Date = ban_Date;
    }

    public Date getBan_unDate() {
        return ban_unDate;
    }

    public void setBan_unDate(Date ban_unDate) {
        this.ban_unDate = ban_unDate;
    }
}
