package com.example.tomcat.entity;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.example.tomcat.static_.Constants;

public class Response extends AbstractHttpServletResponse {
  private int status;// 状态码
  private String message;// 状态信息
  private Map<String, String> headers;// 响应头
  private OutputStream socketOutPutStream;
  private ResponseServletOutputStream responseServletOutputStream;

  {
    
    this.status = 200;
    this.message = "OK";
    this.headers = new HashMap<>();
    this.responseServletOutputStream = new ResponseServletOutputStream();
  }

  public Response(OutputStream outputStream) throws IOException {
    this.socketOutPutStream = outputStream;
  }

  @Override
  public int getStatus() {
    return getStatus();
  }

  @Override
  public void addHeader(String key, String value) {
    headers.put(key, value);
  }

  @Override
  public ResponseServletOutputStream getOutputStream() throws IOException {
    return responseServletOutputStream;
  }

  // 完成响应,发送响应内容 响应行 响应头 响应体
  public void complete() {

    sendResponseLine();
    sendResponseHeader();
    sendResponseBody();
    try {
      this.socketOutPutStream.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  private void sendResponseLine() {
    try {
      socketOutPutStream.write("HTTP/1.1".getBytes());
      socketOutPutStream.write(Constants.SP);
      socketOutPutStream.write((byte) status);
      socketOutPutStream.write(Constants.SP);
      socketOutPutStream.write(message.getBytes());
      socketOutPutStream.write(Constants.CR);
      socketOutPutStream.write(Constants.LF);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void sendResponseHeader() {
    if (!headers.containsKey("Content-Length")) {
      addHeader("Content-Length", responseServletOutputStream.getPos() + "");
    }
    if (!headers.containsKey("Content-Type")) {
      addHeader("Content-Type", "text/plain;charset=utf-8");
    }

    Iterator<Entry<String, String>> iterator = headers.entrySet().iterator();
    while (iterator.hasNext()) {
      Entry<String, String> next = iterator.next();
      String key = next.getKey();
      String value = next.getValue();
      try {
        socketOutPutStream.write(key.getBytes());
        socketOutPutStream.write(":".getBytes());
        socketOutPutStream.write(value.getBytes());
        socketOutPutStream.write(Constants.CR);
        socketOutPutStream.write(Constants.LF);
      } catch (IOException e) {
        e.printStackTrace();
      }

    }
    try {
      socketOutPutStream.write(Constants.CR);
      socketOutPutStream.write(Constants.LF);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void sendResponseBody() {
    try {
      socketOutPutStream.write(responseServletOutputStream.getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
