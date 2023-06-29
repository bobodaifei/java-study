package rsa1;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class Md5Test {

    static final String TARGET = "123456";
      
    /*  
     * 不可逆算法  MD5  
     */    
    @org.junit.Test
    public void md5()
    {    
        String str = DigestUtils.md5Hex(TARGET);
        String str1 = DigestUtils.md5Hex(TARGET);
        System.out.println("md5Hex:     "+str);
        System.out.println("md5Hex:     "+str1);
        System.out.println(str.length());
    }    
    /*  
     * 不可逆算法  SHA1  
     */    
    @Test
    public void Sha1()    
    {    
        String str = DigestUtils.shaHex(TARGET);
        print("shaHex:     "+str);
        System.out.println(str.length());
        str = DigestUtils.sha256Hex(TARGET);
        print("sha256Hex:  "+str);
        str = DigestUtils.sha384Hex(TARGET);
        print("sha384Hex:  "+str);
        str = DigestUtils.sha512Hex(TARGET);
        print("sha512Hex:  "+str);
    }    
        
       
     /*  
      * 可逆算法  BASE64  
      */  
         
    @Test
    public void Base64()    
    {    
        //加密    17345  39567
        byte[] b = Base64.encodeBase64(TARGET.getBytes(), true);
        String str = new String(b);    
        print("BASE64:     "+str);    
            
        //解密    
        byte[] b1 = Base64.decodeBase64(str);    
        print("解密之后内容为：  "+new String(b1));    
    }    
    public void print(Object obj)    
    {    
        System.out.println(obj);    
    }    
}