package com.shanlin.intelligent.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import org.springframework.util.StringUtils;
/**
 * Created by Administrator on 2016/6/30.
 */
public class VerificationCodeUtil {
    public static final String prefix = "data:image/png;base64,";
	public static String createCode(String code)
    {
        if(StringUtils.isEmpty(code))
        {
        	return "";
        }
        int length = code.length();
        char[] codechar = code.toCharArray();
        // 定义图像buffer
        BufferedImage buffImg = new BufferedImage(17 *length, 30,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffImg.createGraphics();
        // 创建一个随机数生成器类
        Random random = new Random();
        // 将图像填充为白色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 17 *length, 30);
        // 创建字体，字体的大小应该根据图片的高度来定。
        Font font = new Font("Fixedsys", Font.ITALIC, 24);
        // 设置字体。
        g.setFont(font);
        // 画边框。
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, 17 *length-1, 29);
        // 随机产生160条干扰线，使图象中的认证码不易被其它程序探测到。
        g.setColor(Color.BLACK);
        for (int i = 0; i < length; i++) {
            int x = random.nextInt(70);
            int y = random.nextInt(30);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
      g.drawChars(codechar, 0,length,0, 26);
      //生成base64编码字符串
      StringBuffer base64Str =  new StringBuffer();
      try{
	      base64Str.append(prefix).append(ImgBase64Util.getImageStr(buffImg));
	      return base64Str.toString();
      }catch(Exception e){
    	  return "";
      }
    }
}
