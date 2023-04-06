
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<meta charset="utf-8">
<html>
<body>
  <c:if test="${1==1}" >
    hello word
  </c:if>
  <c:if test="${1==1}" var="flag" scope="request">
    1等于1
  </c:if>
</body>
</html>

