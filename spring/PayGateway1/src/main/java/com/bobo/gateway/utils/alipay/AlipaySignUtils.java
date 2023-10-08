package com.bobo.gateway.utils.alipay;

import com.bobo.gateway.exception.CustomException;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Map;

//RSA2加签工具类
public class AlipaySignUtils {
  private static final String SIGN_ALGORITHM = "SHA256withRSA";

  private static final String ALGORITHM = "RSA";

  /**
   * 对参数进行RSA2签名
   *
   * @param params     待签名参数
   * @param privateKey 商户私钥
   * @param charset    字符集
   * @return 签名结果
   */
  public static String rsa2Sign(Map<String, String> params, RSAPrivateKey privateKey, String charset) throws CustomException {
    String signContent = AlipaySignature.getSignContent(params);
    try {
      Signature signature = Signature.getInstance(SIGN_ALGORITHM);
      signature.initSign(privateKey);
      signature.update(signContent.getBytes(charset));
      byte[] signBytes = signature.sign();
      return Base64.getEncoder().encodeToString(signBytes);
    } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException | UnsupportedEncodingException e) {
      throw new CustomException("-1","加签出错，请联系网关");
    }
  }


  public static String rsa2Sign(String signContent, String privateKeyStr, String charset) throws CustomException {
    try {
      byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyStr);
      PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

      Signature signature = Signature.getInstance(SIGN_ALGORITHM);
      signature.initSign(privateKey);
      signature.update(signContent.getBytes(charset));
      byte[] signBytes = signature.sign();
      return Base64.getEncoder().encodeToString(signBytes);
    } catch (Exception e) {
      throw new CustomException("-1","加签出错，请联系网关");
    }
  }

  /**
   * 对参数进行RSA2解签
   *
   * @param params    待解签参数
   * @param publicKey 支付宝公钥
   * @param charset   字符集
   * @param sign      签名结果
   * @return 解签结果
   */
  public static boolean rsa2Verify(Map<String, String> params, String publicKey, String charset, String sign) throws CustomException {
    String signContent = AlipaySignature.getSignContent(params);
    try {
      Signature signature = Signature.getInstance(SIGN_ALGORITHM);
      signature.initVerify(getPublicKeyFromX509("RSA", publicKey));
      signature.update(signContent.getBytes(charset));
      return signature.verify(Base64.getDecoder().decode(sign));
    } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException | UnsupportedEncodingException e) {
      throw new CustomException("-1","解签出错，请联系网关");
    }
  }

  public static boolean rsa2Verify(String signContent, String publicKey, String charset, String sign) throws CustomException {
    try {
      Signature signature = Signature.getInstance(SIGN_ALGORITHM);
      signature.initVerify(getPublicKeyFromX509("RSA", publicKey));
      signature.update(signContent.getBytes(charset));
      return signature.verify(Base64.getDecoder().decode(sign));
    } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException | UnsupportedEncodingException e) {
      throw new CustomException("-1","解签出错，请联系网关");
    }
  }


  /**
   * 从X509证书中获取公钥
   *
   * @param algorithm 加密算法
   * @param publicKeyStr 公钥字符串
   * @return 公钥
   */
  public static PublicKey getPublicKeyFromX509(String algorithm, String publicKeyStr) {
    try {
      byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyStr);
      X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
      KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
      return keyFactory.generatePublic(keySpec);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}