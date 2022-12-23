package com.ding.study.util;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**

 *
 */
public class AESCommonUrlSafeUtil {

	private static Logger log = LoggerFactory.getLogger(AESCommonUrlSafeUtil.class);

	final static Base64 base64 = new Base64();

	private static String encrypt2(String plainText, String keyStr) {
		try {
			if (keyStr == null) {
				System.out.print("Key为空null");
				return null;
			}
			// 判断Key是否为16位
			if (keyStr.length() != 16) {
				System.out.print("Key长度不是16位");
				return null;
			}
			byte[] raw = keyStr.getBytes();
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// "算法/模式/补码方式"
			IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			byte[] encrypted = cipher.doFinal(plainText.getBytes("utf-8"));

			return Base64.encodeBase64URLSafeString(encrypted);// 此处使用BASE64做转码功能，同时能起到2次加密的作用。
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String decrypt2(String encryptData, String keyStr) {
		try {
			// 判断Key是否正确
			if (keyStr == null) {
				System.out.print("Key为空null");
				return null;
			}
			// 判断Key是否为16位
			if (keyStr.length() != 16) {
				System.out.print("Key长度不是16位");
				return null;
			}
			byte[] raw = keyStr.getBytes("UTF-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

			byte[] encrypted1 = Base64.decodeBase64(encryptData);// 先用base64解密

			try {
				byte[] original = cipher.doFinal(encrypted1);
				String originalString = new String(original, "utf-8");
				return originalString;
			} catch (Exception e) {
				System.out.println(e.toString());
				return null;
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return null;
		}
	}

    /**
     * 返回urlsafe的加密串
     *
     * @param plainText
     * @return
     */
    public static String encrypt2UrlSafe(String plainText, String encryptKey) {
        String value = null;
        try {
            value = encrypt2(plainText, encryptKey);
        } catch (Exception e) {
            log.error("encrypt2UrlSafeError {} {}", plainText, encryptKey);
        }
        return value;
    }

	/**
	 * 返回urlsafe的解密串
	 *
	 * @param encryptData
	 * @return
	 */
	public static String decrypt2UrlSafe(String encryptData, String encryptKey) {
		try {
			return decrypt2(encryptData, encryptKey);
		} catch (Exception e) {
			log.error("encrypt2UrlSafeError " + encryptData + "  " + encryptKey);
		}
		return null;
	}
	public static String getUserIdDesUrlSafe(String userIdAes) {
		//return AESUtil.decrypt2UrlSafe(userIdAes, userIdAesKey);

		//先urldecode  兼容历史已经存在的数据
		try {
			userIdAes = URLDecoder.decode(userIdAes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error("getUserIdDesUrlSafeError " + userIdAes, e);
			return null;
		}
		return decrypt2UrlSafe(userIdAes,"");
	}



}
