package util;

import java.security.MessageDigest;


public class MD5Util {
	public static String MD5Encode(String str){
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			/*BASE64Encoder base64en = new BASE64Encoder();
			String rstr = base64en.encode(md5.digest(str.getBytes()));
			return rstr;*/
			return String.valueOf(md5.digest(str.getBytes()));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}		
	}
	
	public static void main(String[] args){
		System.out.println(MD5Encode("wudazihuan"));
	}
}
