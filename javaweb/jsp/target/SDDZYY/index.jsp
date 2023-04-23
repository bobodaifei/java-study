<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<meta charset="utf-8">
<html>
<body>
<h2>Hello World!</h2>
&gt; &lt;
<%-- 源码中是在servlet的类体中 --%>
<%! 

  String quanju = "全局变量"; 
%>
<%-- 源码中是在servlet的方法中 --%>
<% 
  String jubu = "局部变量"; 
  System.out.printf("这是脚本段读取了全局变量和全局变量");
  out.print(jubu);
  out.print(quanju);
%>
<%-- 源码中是在servlet的类体中，相当于out.print(jubu); --%>
<%=quanju %>
</body>
</html>
