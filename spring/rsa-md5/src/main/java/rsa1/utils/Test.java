package rsa1.utils;

import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class Test {
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String pwd = "thisispwd";
        System.out.println("MD5加密后\t" + new MD5Encrypt().encrypt(pwd));

        String ascKey = "this is ascKey";
        String afterASCEncrypt = new AESEncrypt(ascKey).encrypt(pwd);

        System.out.println("asc加密后:\t" + afterASCEncrypt);

        System.out.println("asc解密后:\t" + new AESEncrypt(ascKey).decrypt(afterASCEncrypt));

        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 密钥位数
        keyPairGen.initialize(1024);
        // 密钥对
        KeyPair keyPair = keyPairGen.generateKeyPair();
        // 公钥
        PublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        // 私钥
        PrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        RSAEncrypt rsaEncrypt = new RSAEncrypt(publicKey, privateKey);
        String afterRSAEncrypt = rsaEncrypt.encrypt(pwd);
        System.out.println("rsa加密后\t" + afterRSAEncrypt);
        System.out.println("rsa解密后:\t" + rsaEncrypt.decrypt(afterRSAEncrypt));

    }

}
