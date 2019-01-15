package com.fyj.weixin.Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class CheckoutUtil {
	
    // ��ӿ�������Ϣ�е�TokenҪһ��
    private static String token = "yunxi20130416";

    /**
     * ��֤ǩ��
     * 
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce) {

        String[] arr = new String[] { token, timestamp, nonce };
        // ��token��timestamp��nonce�������������ֵ�������
        // Arrays.sort(arr);
        sort(arr);

        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }

        MessageDigest md = null;
        String tmpStr = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
            // �����������ַ���ƴ�ӳ�һ���ַ�������sha1����
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        log.debug("content:"+content);
        content = null;
        log.debug("feng:"+tmpStr);
        log.debug("ten:"+signature);
        // ��sha1���ܺ���ַ�������signature�Աȣ���ʶ��������Դ��΢��
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;

    }

 

    /**
     * ���ֽ�����ת��Ϊʮ�������ַ���
     * 
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {

    	String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }

        return strDigest;
    }

 

    /**
     * ���ֽ�ת��Ϊʮ�������ַ���
     * 
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {

        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        String s = new String(tempArr);
        return s;
    }

    public static void sort(String a[]) {

        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[j].compareTo(a[i]) < 0) {
                    String temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
    }
}