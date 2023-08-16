package org.example;

public class SafeEncoder {
	public static byte[][] encodeMany(final String... strs){
		byte[][] many = new byte[strs.length][];
		for(int i=0;i<strs.length;i++){
			many[i] = encode(strs[i]);
		}
		return many;
	}

    public static byte[] encode(final String str) {
        try {
            if (str == null) {
                throw new RuntimeException(
                        "value sent to redis cannot be null");
            }
            return str.getBytes("utf-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String encode(final byte[] data) {
        try {
            return new String(data, "utf-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}