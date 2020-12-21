package com.okky.utils;

import java.security.Key;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import org.apache.commons.codec.binary.Base64;
public class Crypto {

	public static String decrptLogin(String cookieValue) throws Exception {

		if(cookieValue == null || cookieValue.length() == 0) return "";
		
		String instance = "DESede/ECB/PKCS5Padding";
		javax.crypto.Cipher ciper = javax.crypto.Cipher.getInstance(instance);
		ciper.init(javax.crypto.Cipher.DECRYPT_MODE, getKey2("kiukkiukkiukkiukkiukkiuk"));
		
		byte[] inputBytes1 = Base64.decodeBase64(cookieValue.getBytes());
		byte[] outputBytes2 = ciper.doFinal(inputBytes1);
		
		String strResult = new String(outputBytes2, "UTF8");
		return strResult;
	}

	private static Key getKey2(String keyValue) throws Exception {
		DESedeKeySpec desKeySpec = new DESedeKeySpec(keyValue.getBytes());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
		Key key = keyFactory.generateSecret(desKeySpec);
		return key;
	}

	public static String encryptLogin(String cookieValue) throws Exception {
		

		if(cookieValue == null || cookieValue.length() == 0) return "";
		
		String instance = "DESede/ECB/PKCS5Padding";
		javax.crypto.Cipher ciper = javax.crypto.Cipher.getInstance(instance);
		ciper.init(javax.crypto.Cipher.ENCRYPT_MODE, getKey2("kiukkiukkiukkiukkiukkiuk"));
		
		byte[] inputBytes1 = cookieValue.getBytes("UTF8");
		byte[] outputBytes1 = ciper.doFinal(inputBytes1);
		
		return new String(Base64.encodeBase64(outputBytes1));
	}

}
