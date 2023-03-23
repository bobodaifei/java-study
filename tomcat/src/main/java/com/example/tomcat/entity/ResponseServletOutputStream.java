package com.example.tomcat.entity;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

public class ResponseServletOutputStream extends ServletOutputStream {

  private byte[] bytes = new byte[1024];
  private int pos = 0;

  @Override
  public boolean isReady() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'isReady'");
  }

  @Override
  public void setWriteListener(WriteListener arg0) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setWriteListener'");
  }

  @Override
  public void write(int b) throws IOException {
    bytes[pos++] = (byte) b;
  }

  // @Override
  // public void write(byte[] b) throws IOException {
  //   for (int i = 0; i < b.length; i++) {
  //     bytes[pos++] = b[i];
  //   }
  // }

  public byte[] getBytes() {
    return bytes;
  }
  public int getPos() {
    return pos;
  }
}
