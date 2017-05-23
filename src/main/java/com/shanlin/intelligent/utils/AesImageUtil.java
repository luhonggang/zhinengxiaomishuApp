/**
 * 
 */
package com.shanlin.intelligent.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 图片加密类解密
 * 
 * @author AesImageUtil
 *
 */
public class AesImageUtil {

	private static final String KEY = "123456789";

	/*public static void main(String[] args) {
		File file = new File("D:\\tp1.png");
		decodeImage(file);
	}*/

	/**
	 * 图片加密
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static File encryptImage(File file) {
		InputStream in = null;
		FileOutputStream out = null;
		try {
			List<byte[]> datas = new ArrayList<byte[]>();
			byte[] buf = new byte[1000];
			try {
				in = new FileInputStream(file);
				while ((in.read(buf)) != -1) {
					datas.add(buf);
					buf = new byte[1024];
				}
				out = new FileOutputStream(file);

				List<byte[]> outdatas = new ArrayList<byte[]>();
				outdatas.add(KEY.getBytes("utf-8"));
				for (byte[] bs : datas) {
					outdatas.add(encryptImage(bs));
				}
				for (byte[] b : outdatas) {
					out.write(b);
				}

			} catch (Exception e) {
			} finally {
				out.close();
				in.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return file;

	}

	/**
	 * 加密 每一位加1
	 * 
	 * @param data
	 * @return
	 */
	private static byte[] encryptImage(byte[] data) {
		byte[] redata = new byte[data.length];
		int len;
		for (int i = 0; i < data.length; i++) {
			len = data[i] + 1;
			redata[i] = (byte) len;
		}

		return redata;

	}

	/**
	 * 加密
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] encryptData(byte[] data) {
		try {
			byte[] keydata = KEY.getBytes("utf-8");
			byte[] redata = new byte[keydata.length + data.length];
			System.arraycopy(keydata, 0, redata, 0, keydata.length);
			data = encryptImage(data);
			System.arraycopy(data, 0, redata, keydata.length, data.length);
			data = redata;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return data;

	}

	/**
	 * 图片解密
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static File decodeImage(File file) {
		InputStream in = null;
		FileOutputStream out = null;
		try {
			List<byte[]> datas = new ArrayList<byte[]>();
			byte[] buf = new byte[KEY.getBytes("utf-8").length];
			try {
				in = new FileInputStream(file);
				while ((in.read(buf)) != -1) {
					datas.add(buf);
					buf = new byte[1024];
				}
				out = new FileOutputStream(file);
				List<byte[]> data = new ArrayList<byte[]>();
				if (new String(datas.get(0)).equals(KEY)) {
					datas.remove(0);
					for (byte[] bs : datas) {
						data.add(decodeImage(bs));
					}

				} else {
					// 没有加密
					data = datas;
				}
				for (byte[] b : data) {
					out.write(b);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				out.close();
				in.close();
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		return file;

	}

	/**
	 * 解密
	 * 
	 * @param data
	 * @return
	 */
	private static byte[] decodeImage(byte[] data) {
		byte[] redata = new byte[data.length];
		int len;
		for (int i = 0; i < data.length; i++) {
			len = data[i] - 1;
			redata[i] = (byte) len;
		}

		return redata;

	}

	/**
	 * 数据解密
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] decodeData(byte[] data) {
		try {
			int len = KEY.getBytes("utf-8").length;
			if (data.length > len) {
				byte[] b = new byte[len];
				System.arraycopy(data, 0, b, 0, b.length);
				len = data.length - b.length;
				if (new String(b).equals(KEY)) {
					b = new byte[len];
					len = KEY.getBytes("utf-8").length;
					System.arraycopy(data, len, b, 0, b.length);
					data = decodeImage(b);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return data;

	}
}
