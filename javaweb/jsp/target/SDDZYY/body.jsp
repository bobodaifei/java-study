<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<meta charset="utf-8">
<html>
<body>

<%
  pageContext.setAttribute("name", "page1");
  request.setAttribute("name", "page2"); 
  session.setAttribute("name", "page3"); 
  application.setAttribute("name", "page4"); 
%>

<%@ include file="aside.jsp"%>
<h2>主体</h2>
欢迎<%=session.getAttribute("uname")%>


<jsp:include page="buttom.jsp">
  <jsp:param name="name" value="zhangsan"/>
</jsp:include>
</body>
</html>