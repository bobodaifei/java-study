/**
 * Created at 2014年7月10日, 上午11:39:50.
 */
package com.example.redis.utils.reids;

/**
 * @author liangqiushi@jd.com
 *
 */
public interface Hashing {
	public static final Hashing MURMUR_HASH = new MurmurHash();
	public static final Hashing MD5 = new MD5Hash();
	public static final Hashing CRC32 = new CRC32();

	long hash(String key);

	long hash(byte[] key);
}
