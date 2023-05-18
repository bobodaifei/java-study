// package com.bobo.utils.http;

// import java.io.IOException;
// import java.io.InputStream;
// import java.io.OutputStream;
// import java.net.Socket;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// import com.alibaba.fastjson.JSON;

// /**
//  * @author qq
//  * @create 2023-05-15 11:15
//  */
// public class SocketClient {
//   public static void main(String[] args) throws IOException {
//     Socket socket = new Socket("localhost", 8082);

//     Map<String, Object> map = new HashMap<>();
//     map.put("username", "lihang");
//     map.put("password", "123456");
//     String body = JSON.toJSONString(map);

    
//     String param = gson.toJson(map);
//     int length = param.length();

//     OutputStream writer = socket.getOutputStream();

//     // String str = "GET /JavaTeam/showAll?username=lihang HTTP/1.1\r\n"+"Host:
//     // localhost:8082\r\n\r\n";
//     String str = "POST /JavaTeam/showAll HTTP/1.1\r\n" + "Host: localhost:8082\r\n" + "Content-length:" + length
//         + "\r\n\r\n" + param;
//     System.out.println(str);
//     System.out.println("**********************************");

//     writer.write(str.getBytes());
//     writer.flush();
//     socket.shutdownOutput();

//     InputStream inputStream = socket.getInputStream();
//     byte[] bytes = new byte[1024];
//     String reqStr = "";
//     int count = 0;
//     while ((count = inputStream.read(bytes)) != -1) {
//       reqStr = new String(bytes, 0, count, "UTF-8");
//     }

//     reqStr = reqStr.split("\r\n\r\n")[1];
//     // reqStr = reqStr.split("\n\r\n\r")[0];

//     EmpPageRes res = gson.fromJson(reqStr, EmpPageRes.class);
//     System.out.println(res.getPageNo());
//     System.out.println(res.getPageSize());
//     List<Emp> empList = res.getEmpList();
//     for (Emp emp : empList) {
//       System.out.println(emp.getEname());
//     }
//   }
// }
