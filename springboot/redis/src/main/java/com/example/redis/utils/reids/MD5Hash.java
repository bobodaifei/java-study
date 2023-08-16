package com.example.redis.utils.reids;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Modified version of
 * 
 * @author xiaofei
 */
class MD5Hash implements Hashing{
    final static ThreadLocal<MessageDigest> md5Holder = new ThreadLocal<MessageDigest>() {
        protected MessageDigest initialValue() {
            try {
                return MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                throw new IllegalStateException("MD5 Algorithm is not found");
            }
        };
    };
    
    public long hash(String key) {
        return hash(SafeEncoder.encode(key));
    }

    public long hash(byte[] key) {
        MessageDigest md5 = md5Holder.get();

        md5.reset();
        md5.update(key);
        byte[] bKey = md5.digest();
        long res = ((long) (bKey[3] & 0xFF) << 24)
                | ((long) (bKey[2] & 0xFF) << 16)
                | ((long) (bKey[1] & 0xFF) << 8) | (long) (bKey[0] & 0xFF);
        return res;
    }

}
