package com.Service.Util.VaildCode;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 绘制验证码所用的类
 *
 * @author Waki
 */
public class RandomValidateCodeUtil {
    public static final String RANDOMCODEKEY = "RANDOMVALIDATECODEKEY";
    private String randString = "0123456789AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
    private int width = 100;  //图片宽
    private int height = 50; //图片高度
    private int lineSize = 100;    //干扰线数量
    private int stringNum = 4;    //随机产生的字符数量
    private Random random = new Random();

    /**
     * 获得字体
     */
    private Font getFont() {
        return new Font("Fixedsys", Font.CENTER_BASELINE, 30);
    }

    /**
     * 获得颜色
     */
    private Color getRandColor(int fc, int bc) {
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }

    /**
     * 获取随机字符
     */
    private String getRandomString(int num) {
        return String.valueOf(randString.charAt(num));
    }

    /**
     * 绘制字符串
     */
    private String drowString(Graphics g, String randomString, int i) {
        g.setFont(getFont());
        g.setColor(new Color(random.nextInt(101), random.nextInt(111), random.nextInt(121)));
        String rand = String.valueOf(getRandomString(random.nextInt(randString.length())));
        randomString += rand;
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(rand, 13 * i, 30); //绘制字符在图片位置的坐标
        return randomString;
    }

    /**
     * 绘制干扰线
     */
    private void drowLine(Graphics g) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);

    }

    /**
     * 生成随机图片
     *
     * @return 只填了VailCode和Bi这俩属性
     */
    public ValidDateCode_Bean getRandcode(ValidDateCode_Bean vb) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();//产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        g.fillRect(0, 0, width, height);//生成的图片大小
        g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 30));//字体大小
        g.setColor(getRandColor(110, 133));//字体颜色
        //绘制干扰线
        for (int i = 0; i <= lineSize; i++) {
            drowLine(g);
        }
        //绘制随机字符
        String randomString = "";
        for (int i = 1; i <= stringNum; i++) {
            randomString = drowString(g, randomString, i);
        }
        g.dispose();
        vb.setVailCode(randomString);
        vb.setBi(image);
        return vb;
    }
}
