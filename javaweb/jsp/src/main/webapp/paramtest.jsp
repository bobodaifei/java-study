
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<meta charset="utf-8">
<html>
<body>
${pageScope.name}
${requestScope.name} 
${sessionScope.name} 
${applicationScope.name} 

1
${requestScope.username} 
2
<%=request.getAttribute("username")%>
3
${param.username} 
4
<%=request.getParameter("username")%>
</body>
</html>

