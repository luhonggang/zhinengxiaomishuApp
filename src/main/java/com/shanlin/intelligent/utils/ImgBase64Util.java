package com.shanlin.intelligent.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import javax.imageio.ImageIO;


/**
 *
 * @author yuanpan
 * @version 2016年7月8日
 */
public class ImgBase64Util {
	public static String getImageStr(BufferedImage buffImg) throws IOException
	{
		// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		InputStream in = null;
		ByteArrayOutputStream os = null;
		byte[] data = null;
		// 读取图片字节数组
		try
		{
			os = new ByteArrayOutputStream();
			ImageIO.write(buffImg, "jpeg", os);
			in = new ByteArrayInputStream(os.toByteArray());
			data = new byte[in.available()];
			in.read(data);

 			// 对图片解密
			data = AesImageUtil.decodeData(data);
			// 对字节数组Base64编码
			return Base64.getEncoder().encodeToString(data);// 返回Base64编码过的字节数组字符串
		} 
		catch (IOException e) 
		{
			throw new RuntimeException(e);
		} 
		finally 
		{
			if (in != null) 
			{
				in.close();
			}
			if (os != null) 
			{
				os.close();
			}
		}
	}

}
