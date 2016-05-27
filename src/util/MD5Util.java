package util;

import java.security.MessageDigest;

import sun.misc.BASE64Encoder;

public class MD5Util {
	public static String MD5Encode(String str){
		try {
			MessageDigest md5 = MessageDigest.getInstance("md5");
			byte[] cipherdata = md5.digest(str.getBytes());
			StringBuilder sb = new StringBuilder();
			for(byte cipher : cipherdata){
				String toHexStr = Integer.toHexString(cipher & 0xff);
				toHexStr = (toHexStr.length() == 1 ) ? "0" + toHexStr : toHexStr;
				sb.append(toHexStr);
			}		
			BASE64Encoder base64en = new BASE64Encoder();
			String rstr = base64en.encode(sb.toString().getBytes());
			return rstr;
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
