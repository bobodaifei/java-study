package com.bobo.utils.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.bobo.common.Result;
import com.bobo.entity.Emp;

import cn.hutool.json.JSONUtil;

public class HttpUtils<T> {

  private static String method = "POST";
  private static String protocol = "HTTP/1.1";

  static final String CRLF = "\r\n";
  static final String CR = "\r";
  static final String LF = "\n";
  static final String COLON = ":";
  static final String SP = " ";
  static final String QUESTION = "?";

  public static <T> T socketClient(String url, Map<String, Object> paramMap, Class<T> c) throws IOException {

    String[] split = url.split("://");
    String[] split2 = split[1].split("/", 2);
    String host = split2[0];
    String uri = split2[1];

    String body = JSON.toJSONString(paramMap);

    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(method);
    stringBuffer.append(SP);
    stringBuffer.append("/"+uri);
    stringBuffer.append(SP);
    stringBuffer.append(protocol);
    stringBuffer.append(CRLF);

    stringBuffer.append("Host");
    stringBuffer.append(COLON);
    stringBuffer.append(host);
    stringBuffer.append(CRLF);
    stringBuffer.append("Content-length");
    stringBuffer.append(COLON);
    stringBuffer.append(body.length());
    stringBuffer.append(CRLF);

    stringBuffer.append(CRLF);

    stringBuffer.append(body);

    System.out.println(stringBuffer.toString());
    Socket socket = getSocket();

    OutputStream writer = socket.getOutputStream();
    writer.write(stringBuffer.toString().getBytes());
    writer.flush();
    socket.shutdownOutput();

    InputStream in = socket.getInputStream();
    byte[] bytes = StreamUtils.streamToByteArray(in);
    String resp = new String(bytes, "UTF-8");

    String respBody = resp.split(CRLF+ CRLF)[1];

    return JSONUtil.toBean(respBody, c);

  }

  private static Socket getSocket() throws UnknownHostException, IOException {
    return new Socket("localhost", 8080);
  };
  

  public static void main(String[] args) throws IOException {
    Map<String, Object> map = new HashMap<>();
    map.put("ename", "213123");
    map.put("empno", "1033");
    Result res = HttpUtils.socketClient("http://localhost:8080/ajaxtest/emp?method=login", map, Result.class);
    
    System.out.println(JSONUtil.toBean(res.getData().toString(), Emp.class).getJob());
  }

}
