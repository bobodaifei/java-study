package org.example.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class socketTest {
  public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = new ServerSocket(10001);
    while (true) {
      Socket socket = serverSocket.accept();
    InputStream inputStream = socket.getInputStream();
    OutputStream outputStream = socket.getOutputStream();
    outputStream.write("HTTP/1.1 200 OK\r\nContent-Type:text/html\r\nContent-Length:5\r\n\r\nhello".getBytes());
    outputStream.close();
    inputStream.close();
    socket.close();
    
    }

  }
}
