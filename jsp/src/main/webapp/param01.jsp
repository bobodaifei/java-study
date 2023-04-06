
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<meta charset="utf-8">
<html>
<body>
<%
  pageContext.setAttribute("name", "page1");
  request.setAttribute("name", "page2"); 
  session.setAttribute("name", "page3"); 
  application.setAttribute("name", "page4"); 
%>
  <form name="form1" method="post" action="paramtest.jsp">
   请输入用户姓名：<input type="text" name="username">
   <input type="submit" name="Submit" value="提交">
</form>
</body>
</html>

