/**
 * Created at 2014年8月6日, 下午2:28:53.
 */
package com.example.redis.utils.reids;

/**
 * @author liangqiushi@jd.com
 */
public class CRC32 implements Hashing {

    @Override
    public long hash(String key) {
        return hash(SafeEncoder.encode(key));
    }

    @Override
    public long hash(byte[] key) {
        java.util.zip.CRC32 crc32 = new java.util.zip.CRC32();
        crc32.update(key);
        return crc32.getValue();
    }
}
