package rsa1;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 *
 */
public class Rsa {

    public static void main(String[] args) throws Exception {
        // 随机生成一对 RAS 密钥（包含公钥和私钥）
        KeyPair keyPair = RSASignUtils.generateKeyPair();
        // 获取 公钥 和 私钥
        PublicKey pubKey = keyPair.getPublic();
        PrivateKey priKey = keyPair.getPrivate();

        // 原始数据
        String data = "你好, World";

        // 私钥签名（数据）: 对数据进行签名, 返回签名结果
        byte[] signInfo = RSASignUtils.sign(data.getBytes(), priKey);
        System.out.println("数据签名信息:" + new BASE64Encoder().encode(signInfo));

        // 公钥验签（数据）: 用公钥校验数据的签名是否来自公钥对应的私钥
        String publicKeyString = new String(Base64.encodeBase64(pubKey.getEncoded()));
        boolean verify = RSASignUtils.verify(data.getBytes(), signInfo,publicKeyString);
        System.out.println("数据验签结果:" + verify);

        /*
         * 对文件进行签名和验签
         */

        // 私钥签名（文件）: 对文件进行签名, 返回签名结果
        byte[] fileSignInfo = RSASignUtils.sign(data.getBytes(), priKey);
        System.out.println("文件签名信息:" + new BASE64Encoder().encode(fileSignInfo));

        // 公钥验签（文件）: 用公钥校验文件的签名是否来自公钥对应的私钥

        boolean fileVerify = RSASignUtils.verify(data.getBytes(), signInfo, publicKeyString);
        System.out.println("文件验签结果:" + fileVerify);
    }

}
