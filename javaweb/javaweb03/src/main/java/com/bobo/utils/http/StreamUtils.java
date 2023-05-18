package com.bobo.utils.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamUtils {
  private final static int BYTE_LENGTH = 1024;

  public static byte[] streamToByteArray(InputStream inputStream) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    byte[] bytes = new byte[BYTE_LENGTH];
    int count;
    // 此处阻塞
    while ((count = inputStream.read(bytes)) != -1) {
      baos.write(bytes, 0, count);
      if (count < BYTE_LENGTH) {
        break;
      }
    }
    byte[] byteArr = baos.toByteArray();
    baos.close();
    return byteArr;
  }
}
