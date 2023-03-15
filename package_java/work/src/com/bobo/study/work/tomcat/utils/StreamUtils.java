package com.bobo.study.work.tomcat.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamUtils {
  public static byte[] streamToByteArray(InputStream inputStream) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    byte[] bytes = new byte[1024];
    int count;
    while ((count = inputStream.read(bytes))!=-1) {
      baos.write(bytes, 0, count);
    }
    byte[] byteArr = baos.toByteArray();
    baos.close();
    return byteArr;
  } 
}
