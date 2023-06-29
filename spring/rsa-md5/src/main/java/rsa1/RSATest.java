package rsa1;
 
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
 
import javax.crypto.Cipher;
 
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
 
public class RSATest {
 
    private static Map<Integer, String> keyMap = new HashMap<Integer, String>();
    /** 秘钥对算法名称 */
    private static final String ALGORITHM = "RSA";

    /** 密钥长度 */
    private static final int KEY_SIZE = 2048;

    /** 签名算法 */
    private static final String SIGNATURE_ALGORITHM = "Sha1WithRSA";
    
    public static final String CHARSET = "UTF-8";
    public static final String RSA_ALGORITHM = "RSA"; // ALGORITHM ['ælgərɪð(ə)m] 算法的意思

    @Test
    public void ccc() {

        System.out.println("name=%E5%9F%8E%E5%B8%82&status=1".length());
    }
    
    @Test
    public void testSignAndVerify() throws Exception {
        // 随机生成一对 RAS 密钥（包含公钥和私钥）
        KeyPair keyPair = RSATest.genKeyPair();
        // 获取 公钥 和 私钥
        PublicKey pubKey = keyPair.getPublic();
        PrivateKey priKey = keyPair.getPrivate();

        // 原始数据
        String data = "你好, World";

        // 私钥签名（数据）: 对数据进行签名, 返回签名结果
        byte[] signInfo = RSASignUtils.sign(data.getBytes(), new String(Base64.encodeBase64(priKey.getEncoded())));
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
//     name=lisi&age=19&
//    age=19&name=lisi&sign=990
    @Test
    public void cc() throws Exception{
        KeyPair keyPair = RSATest.genKeyPair();

        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 得到私钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        // 得到公钥
         String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
         // 得到私钥字符串
         String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
        // map装载公钥和私钥
        System.out.println(publicKeyString);
        System.out.println("-----------");
        System.out.println(privateKeyString);
        
        byte[] sign = sign("name=lisi&age=19".getBytes(), privateKeyString);
        
        boolean rs = verify("name=lisi&age=19".getBytes(),sign, publicKeyString);
        System.out.println(rs);
        // 返回map
    }
    
    @Test
    public   void TestEncrypt() throws Exception {
        // TODO Auto-generated method stub
 
        // 用于封装随机产生的公钥与私钥
        { // 生成公钥和私钥
            KeyPair keyPair = RSATest.genKeyPair();

            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            // 得到私钥
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            // 得到公钥
             String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
             // 得到私钥字符串
             String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));

            // 加密字符串
            String message = "df723820";
           // System.out.println("随机生成的公钥为:" + publicKeyString);
           // System.out.println("随机生成的私钥为:" + privateKeyString);
            String messageEn = encrypt(message, publicKeyString);
            System.out.println(message + "\t加密后的字符串为:" + messageEn);
            String messageDe = decrypt(messageEn, privateKeyString);
            System.out.println("还原后的字符串为:" + messageDe);
        }
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
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
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
//    name=lisi&age=19
//    age=19&abe=lisi
//    abe=lisi&age=19
//    sign=1232424
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
        boolean verify = sign.verify(signInfo);

        return verify;
    }


    @Test
    public   void TestgenKeyPair() throws Exception {
        genKeyPair();
    }
    public static   KeyPair genKeyPair() throws NoSuchAlgorithmException { // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(2048, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
//        // 得到私钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
//        // 得到公钥
        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
        System.out.println(Base64.encodeBase64(publicKey.getEncoded()));
        System.out.println(publicKeyString);
////        // 得到私钥字符串
        String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
//        // 将公钥和私钥保存到
//        keyMap.put(0, publicKeyString);
//        this.pubKey=publicKeyString;
//        // 0表示公钥
//        keyMap.put(1, privateKeyString);
//        this.priKey = privateKeyString;
        return keyPair;
        // 1表示私钥
    }
 
    public static String encrypt(String str, String publicKey) throws Exception {
        // base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA")
                .generatePublic(new X509EncodedKeySpec(decoded));
        // RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
        return outStr;
    }
 
    public static String decrypt(String str, String privateKey) throws Exception {
        // 64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
        // base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA")
                .generatePrivate(new PKCS8EncodedKeySpec(decoded));
        // RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }
 
}