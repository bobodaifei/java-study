package com.bobo.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;


//该代码为支付宝签名工具类，用于生成和验证支付宝请求和响应的数字签名。以下是代码中的主要方法和常量：
//
//        方法：
//
//        1. sign(Map<String, String> params, String privateKey, String charset, String signType)：生成数字签名，参数为请求参数Map、私钥、字符编码和签名类型。
//
//        2. verify(Map<String, String> params, String publicKey, String charset, String signType)：验证数字签名，参数为请求参数Map、公钥、字符编码和签名类型。
//
//        3. encrypt(String content, String password)：使用AES算法对内容进行加密，参数为待加密内容和密码。
//
//        4. decrypt(String content, String password)：使用AES算法对内容进行解密，参数为待解密内容和密码。
//
//        常量：
//
//        1. ALGORITHM：加密算法，使用AES算法。
//
//        2. CHARSET：字符编码，使用UTF-8。
//
//        3. SIGN_TYPE_RSA：签名类型，使用RSA算法。
//
//        4. SIGN_TYPE_RSA2：签名类型，使用RSA2算法。
//
//        5. SIGN_ALGORITHMS：签名算法，使用SHA1WithRSA算法。
//
//        6. SIGN_SHA256RSA_ALGORITHMS：签名算法，使用SHA256WithRSA算法。
//
//        7. DEFAULT_TIME_FORMAT：时间格式，使用yyyy-MM-dd HH:mm:ss格式。
//
//        8. AES_ALGORITHM：AES算法加密方式，使用AES/ECB/PKCS5Padding方式。

public class AlipaySignature {
  private static final String ALGORITHM = "AES";
  private static final String CHARSET = "UTF-8";
  private static final String SIGN_TYPE_RSA = "RSA";
  private static final String SIGN_TYPE_RSA2 = "RSA2";
  private static final String SIGN_ALGORITHMS = "SHA1WithRSA";
  private static final String SIGN_SHA256RSA_ALGORITHMS = "SHA256WithRSA";
  private static final String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
  private static final String AES_ALGORITHM = "AES/ECB/PKCS5Padding";

  public static String sign(Map<String, String> params, String privateKey, String charset, String signType)
          throws SignatureException {
    String content = getSignContent(params);
    if (SIGN_TYPE_RSA.equals(signType)) {
      return rsaSign(content, privateKey, charset);
    } else if (SIGN_TYPE_RSA2.equals(signType)) {
      return rsa256Sign(content, privateKey, charset);
    } else {
      throw new SignatureException("Invalid sign_type: " + signType);
    }
  }

  public static boolean verify(Map<String, String> params, String publicKey, String charset, String signType)
          throws SignatureException {
    String content = getSignContent(params);
    String sign = params.get("sign");
    if (SIGN_TYPE_RSA.equals(signType)) {
      return rsaCheckContent(content, sign, publicKey, charset);
    } else if (SIGN_TYPE_RSA2.equals(signType)) {
      return rsa256CheckContent(content, sign, publicKey, charset);
    } else {
      throw new SignatureException("Invalid sign_type: " + signType);
    }
  }

  public static String encrypt(String content, String password) throws Exception {
    SecretKeySpec key = new SecretKeySpec(password.getBytes(CHARSET), ALGORITHM);
    Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
    cipher.init(Cipher.ENCRYPT_MODE, key);
    byte[] encrypted = cipher.doFinal(content.getBytes(CHARSET));
    return new String(Base64.encodeBase64(encrypted));
  }

  public static String decrypt(String content, String password) throws Exception {
    SecretKeySpec key = new SecretKeySpec(password.getBytes(CHARSET), ALGORITHM);
    Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
    cipher.init(Cipher.DECRYPT_MODE, key);
    byte[] decrypted = cipher.doFinal(Base64.decodeBase64(content.getBytes(CHARSET)));
    return new String(decrypted, CHARSET);
  }

  private static String getSignContent(Map<String, String> params) {
    if (params == null) {
      return null;
    }
    Map<String, String> sortedParams = new HashMap<String, String>(params);
    List<String> keys = new ArrayList<String>(sortedParams.keySet());
    Collections.sort(keys);
    StringBuilder signContent = new StringBuilder();
    for (int i = 0; i < keys.size(); i++) {
      String key = keys.get(i);
      String value = sortedParams.get(key);
      if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(value)) {
        signContent.append(key).append("=").append(value).append("&");
      }
    }
    return signContent.substring(0, signContent.length() - 1);
  }

  private static String rsaSign(String content, String privateKey, String charset) throws SignatureException {
    try {
      return RSA.sign(content, privateKey, charset);
    } catch (Exception e) {
      throw new SignatureException("RSA sign error: " + e.getMessage(), e);
    }
  }

  private static String rsa256Sign(String content, String privateKey, String charset) throws SignatureException {
    try {
      return RSA.sign256(content, privateKey, charset);
    } catch (Exception e) {
      throw new SignatureException("RSA256 sign error: " + e.getMessage(), e);
    }
  }

  private static boolean rsaCheckContent(String content, String sign, String publicKey, String charset)
          throws SignatureException {
    try {
      return RSA.checkContent(content, sign, publicKey, charset);
    } catch (Exception e) {
      throw new SignatureException("RSA check content error: " + e.getMessage(), e);
    }
  }

  private static boolean rsa256CheckContent(String content, String sign, String publicKey, String charset)
          throws SignatureException {
    try {
      return RSA.check256Content(content, sign, publicKey, charset);
    } catch (Exception e) {
      throw new SignatureException("RSA256 check content error: " + e.getMessage(), e);
    }
  }

  public static class RSA {
    private static final String KEY_ALGORITHM = "RSA";
    private static final String SIGNATURE_ALGORITHM = "SHA1WithRSA";
    private static final String SIGNATURE_ALGORITHM_SHA256 = "SHA256WithRSA";

    public static String sign(String content, String privateKey, String charset) throws Exception {
      PrivateKey priKey = getPrivateKeyFromPKCS8(KEY_ALGORITHM, privateKey);
      Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
      signature.initSign(priKey);
      if (StringUtils.isBlank(charset)) {
        signature.update(content.getBytes());
      } else {
        signature.update(content.getBytes(charset));
      }
      byte[] signed = signature.sign();
      return new String(Base64.encodeBase64(signed));
    }

    public static String sign256(String content, String privateKey, String charset) throws Exception {
      PrivateKey priKey = getPrivateKeyFromPKCS8(KEY_ALGORITHM, privateKey);
      Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM_SHA256);
      signature.initSign(priKey);
      if (StringUtils.isBlank(charset)) {
        signature.update(content.getBytes());
      } else {
        signature.update(content.getBytes(charset));
      }
      byte[] signed = signature.sign();
      return new String(Base64.encodeBase64(signed));
    }

    public static boolean checkContent(String content, String sign, String publicKey, String charset)
            throws Exception {
      PublicKey pubKey = getPublicKeyFromX509(KEY_ALGORITHM, publicKey);
      Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
      signature.initVerify(pubKey);
      if (StringUtils.isBlank(charset)) {
        signature.update(content.getBytes());
      } else {
        signature.update(content.getBytes(charset));
      }
      return signature.verify(Base64.decodeBase64(sign.getBytes()));
    }

    public static boolean check256Content(String content, String sign, String publicKey, String charset)
            throws Exception {
      PublicKey pubKey = getPublicKeyFromX509(KEY_ALGORITHM, publicKey);
      Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM_SHA256);
      signature.initVerify(pubKey);
      if (StringUtils.isBlank(charset)) {
        signature.update(content.getBytes());
      } else {
        signature.update(content.getBytes(charset));
      }
      return signature.verify(Base64.decodeBase64(sign.getBytes()));
    }

    private static PrivateKey getPrivateKeyFromPKCS8(String algorithm, String privateKey)
            throws Exception {
      if (privateKey == null) {
        throw new IllegalArgumentException("private key is null");
      }
      KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
      byte[] encodedKey = Base64.decodeBase64(privateKey.getBytes());
      return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
    }

    private static PublicKey getPublicKeyFromX509(String algorithm, String publicKey)
            throws Exception {
      if (publicKey == null) {
        throw new IllegalArgumentException("public key is null");
      }
      KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
      byte[] encodedKey = Base64.decodeBase64(publicKey.getBytes());
      return keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
    }
  }
}
