<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<meta charset="utf-8">
<html>
<body>
  <form action="loginServlet" method="post">
    <input type="text" name="uname"/>
    <input type="password" name="upwd"/>
    <button>登录</button>
    <%-- <span><%=request.getAttribute("msg")%> </span> --%>
    <%
      String msg = "aoligei";
    %>
    <span>${msg}</span>
    <span>${pageScope.msg }</span>
  </form>
</body>
</html>
