package rsa1;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5Utils {
    public static String stringToMD5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (
                Exception e) {
            throw new RuntimeException("没有这个md5算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

    public static void main(String[] args) {
        String s = MD5Utils.stringToMD5("123456");
        System.out.println(s);
        System.out.println(s.length());
//        byte[] bytes = DigestUtils.md5("123456".getBytes());
//        System.out.println(DESUtil.fromBytesToHex(bytes));
    }
}