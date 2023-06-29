package rsa1;

import java.security.*;

public class myDigest {
    public static void main(String[] args) {
        myDigest my = new myDigest();
        my.testDigest();
    }

    public void testDigest() {
        try {
            String myinfo = "123456";
            MessageDigest alga= MessageDigest.getInstance("MD5");
//            java.security.MessageDigest alga = java.security.MessageDigest.getInstance("SHA-1");
            alga.update(myinfo.getBytes());
            byte[] digesta = alga.digest();
            String s = DESUtil.fromBytesToHex(digesta);
            System.out.println(s.length());
            System.out.println("本信息摘要是:" + DESUtil.fromBytesToHex(digesta));

        } catch (NoSuchAlgorithmException ex) {
            System.out.println("非法摘要算法");
        }
    }

    public String byte2hex(byte[] b) //二行制转字符串
    {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) hs = hs + "0" + stmp;
            else hs = hs + stmp;
            if (n < b.length - 1) hs = hs + ":";
        }
        return hs.toUpperCase();
    }
}