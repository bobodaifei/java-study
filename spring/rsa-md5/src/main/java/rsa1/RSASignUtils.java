package rsa1;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import sun.misc.BASE64Decoder;

/**
 * RSA 签名验签工具类
 *
 * @author xietansheng
 */
public class RSASignUtils {

    /** 秘钥对算法名称 */
    private static final String ALGORITHM = "RSA";

    /** 密钥长度 */
    private static final int KEY_SIZE = 2048;

    /** 签名算法 */
    private static final String SIGNATURE_ALGORITHM = "Sha1WithRSA";

    /**
     * 随机生成 RSA 密钥对（包含公钥和私钥）
     */
    public static KeyPair generateKeyPair() throws Exception {
        // 获取指定算法的密钥对生成器
        KeyPairGenerator gen = KeyPairGenerator.getInstance(ALGORITHM);

        // 初始化密钥对生成器（指定密钥长度, 使用默认的安全随机数源）
        gen.initialize(KEY_SIZE);

        // 随机生成一对密钥（包含公钥和私钥）
        return gen.generateKeyPair();
    }
    
    @Test
    public  void signUn() throws Exception {
        KeyPair keyPair = generateKeyPair();
        PrivateKey aPrivate = keyPair.getPrivate();
        PublicKey aPublic = keyPair.getPublic();
//        String name = "lisi";
//        int age = 10;
//        //name=lisi&age=10
//        TreeMap treeMap = new TreeMap();
//        treeMap.put("ab",3);
//        treeMap.put("wa","2");
//        System.out.println(treeMap);
        //age=20&name=lisi    555555555555555555555555555555555
        //age=10&name=lisi&m=123456   888888888888888888888888888888888
//        new String(Base64.encodeBase64(priKey.getEncoded()))
        String pub=new String(Base64.encodeBase64(aPublic.getEncoded()));
        String pri=new String(Base64.encodeBase64(aPrivate.getEncoded()));
        byte[] data = sign("age=19&name=lisi".getBytes(), aPrivate);
        boolean verify = verify("name=lisi&age=19".getBytes(), data, aPublic);

        System.out.println(verify);

    }

    public static final String CHARSET = "UTF-8";
    public static final String RSA_ALGORITHM = "RSA"; // ALGORITHM ['ælgərɪð(ə)m] 算法的意思


    @Test
    public void init(){
        KeyPairGenerator kpg;
        try {
            kpg = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("No such algorithm-->[" + RSA_ALGORITHM + "]");
        }

        // 初始化KeyPairGenerator对象,密钥长度
        kpg.initialize(2048);
        // 生成密匙对
        KeyPair keyPair = kpg.generateKeyPair();
        // 得到公钥
        Key publicKey = keyPair.getPublic();
        String publicKeyStr =  new String(Base64.encodeBase64(publicKey.getEncoded()));
        // 得到私钥
        Key privateKey = keyPair.getPrivate();
        String privateKeyStr =  new String(Base64.encodeBase64(privateKey.getEncoded()));
        // map装载公钥和私钥
        System.out.println(publicKeyStr);
        System.out.println("-----------");
        System.out.println(privateKeyStr);
    }
    /**
     * 私钥签名（数据）: 用私钥对指定字节数组数据进行签名, 返回签名信息
     */
    public static byte[] sign(byte[] data, PrivateKey priKey) throws Exception {
        // 根据指定算法获取签名工具
        Signature sign = Signature.getInstance(SIGNATURE_ALGORITHM);

        // 用私钥初始化签名工具
        sign.initSign(priKey);

        // 添加要签名的数据
        sign.update(data);

        // 计算签名结果（签名信息）
        byte[] signInfo = sign.sign();

        return signInfo;
    }
    /**
     * 私钥签名（数据）: 用私钥对指定字节数组数据进行签名, 返回签名信息
     */
    public static byte[] sign(byte[] data, String priKey) throws Exception {
        // 根据指定算法获取签名工具
        Signature sign = Signature.getInstance(SIGNATURE_ALGORITHM);
        PrivateKey privateKey = getPrivateKey(priKey);
        // 用私钥初始化签名工具
        sign.initSign(privateKey);

        // 添加要签名的数据
        sign.update(data);

        // 计算签名结果（签名信息）
        byte[] signInfo = sign.sign();

        return signInfo;
    }

    /**
     * 公钥验签（数据）: 用公钥校验指定数据的签名是否来自对应的私钥
     */
    public static boolean verify(byte[] data, byte[] signInfo, String pubKey) throws Exception {
        // 根据指定算法获取签名工具
        Signature sign = Signature.getInstance(SIGNATURE_ALGORITHM);
        PublicKey publicKey = getPublicKey(pubKey);
        // 用公钥初始化签名工具
        sign.initVerify(publicKey);

        // 添加要校验的数据
        sign.update(data);

        // 校验数据的签名信息是否正确,
        // 如果返回 true, 说明该数据的签名信息来自该公钥对应的私钥,
        // 同一个私钥的签名, 数据和签名信息一一对应, 只要其中有一点修改, 则用公钥无法校验通过,
        // 因此可以用私钥签名, 然后用公钥来校验数据的完整性与签名者（所有者）
        boolean flag = sign.verify(signInfo);

        return flag;
    }

    /**
     * 公钥验签（数据）: 用公钥校验指定数据的签名是否来自对应的私钥
     */
    public static boolean verify(byte[] data, byte[] signInfo, PublicKey pubKey) throws Exception {
        // 根据指定算法获取签名工具
        Signature sign = Signature.getInstance(SIGNATURE_ALGORITHM);
        // 用公钥初始化签名工具
        sign.initVerify(pubKey);

        // 添加要校验的数据
        sign.update(data);

        // 校验数据的签名信息是否正确,
        // 如果返回 true, 说明该数据的签名信息来自该公钥对应的私钥,
        // 同一个私钥的签名, 数据和签名信息一一对应, 只要其中有一点修改, 则用公钥无法校验通过,
        // 因此可以用私钥签名, 然后用公钥来校验数据的完整性与签名者（所有者）
        boolean flag = sign.verify(signInfo);

        return flag;
    }

    /**
     * 私钥签名（文件）: 用私钥对文件进行签名, 返回签名信息
     */
    public static byte[] signFile(File file, PrivateKey priKey) throws Exception {
        // 根据指定算法获取签名工具
        Signature sign = Signature.getInstance(SIGNATURE_ALGORITHM);

        // 用私钥初始化签名工具
        sign.initSign(priKey);

        InputStream in = null;

        try {
            in = new FileInputStream(file);

            byte[] buf = new byte[1024];
            int len = -1;

            while ((len = in.read(buf)) != -1) {
                // 添加要签名的数据
                sign.update(buf, 0, len);
            }

        } finally {
            close(in);
        }

        // 计算并返回签名结果（签名信息）
        return sign.sign();
    }

    /**
     * 公钥验签（文件）: 用公钥校验指定文件的签名是否来自对应的私钥
     */
    public static boolean verifyFile(File file, byte[] signInfo, PublicKey pubKey) throws Exception {
        // 根据指定算法获取签名工具
        Signature sign = Signature.getInstance(SIGNATURE_ALGORITHM);

        // 用公钥初始化签名工具
        sign.initVerify(pubKey);

        InputStream in = null;

        try {
            in = new FileInputStream(file);

            byte[] buf = new byte[1024];
            int len = -1;

            while ((len = in.read(buf)) != -1) {
                // 添加要校验的数据
                sign.update(buf, 0, len);
            }

        } finally {
            close(in);
        }

        // 校验签名
        return sign.verify(signInfo);
    }


    /**
     * String转公钥PublicKey
     * @param key
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    /**
     * String转私钥PrivateKey
     * @param key
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = new BASE64Decoder().decodeBuffer(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }


    private static void close(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
                // nothing
            }
        }
    }

}
