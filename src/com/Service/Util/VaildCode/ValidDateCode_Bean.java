package com.Service.Util.VaildCode;

import java.awt.image.BufferedImage;
import java.util.Date;

/**
 * 存储验证码以及验证码有效时间所用的javaBean类
 * @author Waki
 */
public class ValidDateCode_Bean {
    private String sessionId;
    private String vailCode;
    private BufferedImage bi;
    private long createTime;
    private long validTime;

    public ValidDateCode_Bean() {
    }

    /**
     * @param sessionId  发起该验证码请求Http的SessionId
     * @param vailCode   验证码本体
     * @param bi         图片验证码
     * @param createTime 创建时间是何时
     * @param validTime  有效时间是多久
     */
    public ValidDateCode_Bean(String sessionId, String vailCode, BufferedImage bi, long createTime, long validTime) {
        this.sessionId = sessionId;
        this.createTime = createTime;
        this.validTime = validTime;
        this.vailCode = vailCode;
        this.bi = bi;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getValidTime() {
        return validTime;
    }

    public void setValidTime(long validTime) {
        this.validTime = validTime;
    }

    public String getVailCode() {
        return vailCode;
    }

    public void setVailCode(String vailCode) {
        this.vailCode = vailCode;
    }

    public BufferedImage getBi() {
        return bi;
    }

    public void setBi(BufferedImage bi) {
        this.bi = bi;
    }

    /**
     * 验证该验证码是否仍然有效
     */
    public boolean isVaild() {
        return new Date().getTime() >= (createTime + validTime) ? false : true;
    }
}
