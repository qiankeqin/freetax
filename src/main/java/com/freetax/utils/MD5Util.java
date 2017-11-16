package com.freetax.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.util.ArrayList;

/**
 * @Author shuxf
 * @Date 2017/11/13 11:04
 */
public class MD5Util {

    private static Logger log = LoggerFactory.getLogger(MD5Util.class);

    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * 用所选编码格式进行MD5加密
     *
     * @param origin
     * @param charsetname
     * @return
     */
    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString;
    }

    /**
     * 以UTF-8格式MD5加密
     *
     * @param origin
     * @return
     */
    public static String MD5EncodeByUTF8(String origin) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");

            resultString = byteArrayToHexString(md.digest(resultString
                    .getBytes("UTF-8")));
        } catch (Exception exception) {
            log.error("MD5加密失败, origin = " + origin);
        }
        return resultString;
    }

    /**
     * 将MD5加密的字符串拼装成jsonString类型：["e","1","0","a","d","c","3","9","4","9","b","a","5","9","a","b","b","e","5","6","e","0","5","7","f","2","0","f","8","8","3","e"]
     * @param chstr
     */
    public static String toJsonString(char[] chstr){
        StringBuffer strb = new StringBuffer();
        strb.append("[");
        for (int i = 0; i < chstr.length; i++){
            strb.append("\"");
            strb.append(chstr[i]);
            strb.append("\"");
            strb.append(",");
        }
        String str = strb.toString().substring(0, strb.toString().length()-1) + "]";
        return str;
    }

    /**
     * ArrayList转化为String
     * @param lsit
     */
    public static String listToString(ArrayList lsit){
        StringBuffer strb = new StringBuffer();
        strb.append("[");
        for (int i=0; i<lsit.size(); i++){
            strb.append("\"");
            strb.append(lsit.get(i).toString());
            strb.append("\"");
            strb.append(",");
        }
        String str = strb.toString().substring(0, strb.toString().length()-1) + "]";
        return str;
    }


    public static void main(String[] args) {
        System.out.println(MD5EncodeByUTF8("123"));
    }

}
