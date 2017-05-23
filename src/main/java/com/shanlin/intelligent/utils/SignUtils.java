package com.shanlin.intelligent.utils;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class SignUtils {
	private static String SIGN_KEY="f41cc06cb9ef8d4e662a6da4ba2ae2ec";

	/**
	 * 
	 * @param data：加签名、值集合
	 * @param key：
	 * @sign rule：密钥需加密数据：密钥+全部非空名、值+密钥（例：keyparam1value1param2value2key）
	 * 对需加密数据进行md5，然后转成大写16进制字符串
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static String getSign(Map<?, ?> map, String key)  {
		ObjectMapper objectMapper = new ObjectMapper(); 
		List<String> list = new ArrayList<String>(map.size()) ;
		StringBuilder str = new StringBuilder() ;
		list.addAll((Collection<? extends String>) map.keySet()) ;
		str.append(key) ;
		for (String strName : list) {
			try {
				String strs = objectMapper.writeValueAsString(map.get(strName));
				
//				JSON obj = JSON.parseObject(data.get(strName));
				str.append(strName).append(strs) ;
			} catch (Exception e) {
				str.append(strName).append(map.get(strName)) ;
			}
		}
		str.append(key) ;
		byte[] bytes = getMD5Digest(str.toString()) ;
		String sign = byte2hex(bytes) ;
//		System.out.println(sign);
		Logger.info(SignUtils.class,sign );
		return sign ;
	}
	
	private static byte[] getMD5Digest(String data) {
		
		byte[] bytes = null ;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5") ;
			bytes = md.digest(data.getBytes("UTF-8")) ;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return bytes ;
	}
	
	private static String byte2hex(byte[] bytes) {
		
		StringBuilder stringBuilder = new StringBuilder() ;
		for (int i=0; i<bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF) ;
			if (hex.length() == 1) {
				stringBuilder.append("0") ;
			}
			stringBuilder.append(hex.toUpperCase()) ;
		}
		return stringBuilder.toString() ;
	}

	/**
	 * 通过bean 生成签名
	 * @param obj java bean
	 * @return
	 * @throws Exception 
	 */
	public static String createSign(Object obj){
		String objStr = JSONObject.toJSONString(obj);
		Map<?, ?> map = JSONObject.parseObject(objStr,TreeMap.class);
		if(map.containsKey("sign")){
			map.remove("sign");
		}
//		System.out.println(map.toString());
		Logger.info(SignUtils.class,map.toString() );
		 String sign;
		try {
			sign = getSign(map,SIGN_KEY);
			return sign;
		} catch (Exception e) {
			Logger.error(SignUtils.class, "**************签名生成错误************",e);
		}
		return null;
	}
	
	/**
	 * 通过传入的JSON字符串 生成签名
	 * @param params 前台传入的字符串
	 * @return
	 * @throws Exception 
	 */
	public static String paramsToSign(String params){
		Map<?, ?> map = JSONObject.parseObject(params,TreeMap.class);
		if(map.containsKey("sign")){
			map.remove("sign");
		}
//		System.out.println(map.toString());
		Logger.info(SignUtils.class,map.toString() );
		 String sign;
		try {
			sign = getSign(map,SIGN_KEY);
			return sign;
		} catch (Exception e) {
			Logger.error(SignUtils.class, "**************签名生成错误************",e);
		}
		return null;
	}
}
