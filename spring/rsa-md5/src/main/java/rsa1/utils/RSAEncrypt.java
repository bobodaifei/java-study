package rsa1.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


import sun.misc.BASE64Encoder;

public class RSAEncrypt implements IEncrypt {
    private PublicKey publicKey;
    private PrivateKey privateKey;
    private String publicKeyString;
    private String privateKeyString;
    // 加解密类
    private Cipher cipher;

    public RSAEncrypt(PublicKey publicKey, PrivateKey privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    /**
     * 加密
     */
    public String encrypt(String string) {
        String afterEncrypt = null;
        try {

            // 加解密类
            cipher = Cipher.getInstance("RSA");// Cipher.getInstance("RSA/ECB/PKCS1Padding");
            // 明文
            byte[] plainText = string.getBytes();
            // 公钥加密
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] enBytes = cipher.doFinal(plainText); 
            afterEncrypt = HexUtil.bytes2Hex(enBytes);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return afterEncrypt;
    }

    /**
     * 解密
     */
    public String decrypt(String string) {
        byte[] bytes = HexUtil.hex2Bytes(string);
        String afterDecrypt = null;
        // 加解密类
        try {
            cipher = Cipher.getInstance("RSA");// Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] deBytes = cipher.doFinal(bytes);
            publicKeyString = getKeyString(publicKey);
            privateKeyString = getKeyString(privateKey);
            afterDecrypt = new String(deBytes);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return afterDecrypt;
    }

    /**
     * 得到密钥字符串（经过base64编码）
     * 
     * @return
     */
    public static String getKeyString(Key key) throws Exception {
        byte[] keyBytes = key.getEncoded();
        return (new BASE64Encoder()).encode(keyBytes);

    }

    public static void main(String[] args) throws Exception {
        byte[] byteArray = new byte[] {97, 20, 30, 40, 50};
        // 将字节数组转换为16进制字符串
        StringBuilder hexString = new StringBuilder();
        for (byte b : byteArray) {
            // 将字节转换为两位16进制数，并添加到StringBuilder中
            hexString.append(String.format("%02x", b));
        }

        System.out.println("16进制字符串: " + hexString.toString());
    }
}
