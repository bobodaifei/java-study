<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<meta charset="utf-8">
<jsp:directive.page import="java.util.*" />
<html>
<body>
<h3>数字格式化:</h3>
<c:set var="balance" value="120000.2309" />
<fmt:formatNumber value="${balance}" 
            type="number" var="num" scope="request"/></p> ${num}
<p>格式化数字 (1): <fmt:formatNumber value="${balance}" 
            type="currency"/></p>
<p>格式化数字 (2): <fmt:formatNumber type="number" 
            maxIntegerDigits="3" value="${balance}" /></p>
<p>格式化数字 (3): <fmt:formatNumber type="number" 
            maxFractionDigits="3" value="${balance}" /></p>
<p>格式化数字 (4): <fmt:formatNumber type="number" 
            groupingUsed="false" value="${balance}" /></p>
<p>格式化数字 (5): <fmt:formatNumber type="percent" 
            maxIntegerDigits="3" value="${balance}" /></p>
<p>格式化数字 (6): <fmt:formatNumber type="percent" 
            minFractionDigits="10" value="${balance}" /></p>
<p>格式化数字 (7): <fmt:formatNumber type="percent" 
            maxIntegerDigits="3" value="${balance}" /></p>
<p>格式化数字 (8): <fmt:formatNumber type="number" 
            pattern="###.###E0" value="${balance}" /></p>
<p>美元 :
<fmt:setLocale value="en_US"/>
<fmt:formatNumber value="${balance}" type="currency"/></p>


<%
    Date date=new Date();
    pageContext.setAttribute("now",date);
%>

<p>Formatted Date (1): <fmt:formatDate type="time" 
            value="${now}" /></p>
<p>Formatted Date (2): <fmt:formatDate type="date" 
            value="${now}" /></p>
<p>Formatted Date (3): <fmt:formatDate type="both" 
            value="${now}" /></p>
<p>Formatted Date (4): <fmt:formatDate type="both" 
            dateStyle="short" timeStyle="short" 
            value="${now}" /></p>
<p>Formatted Date (5): <fmt:formatDate type="both" 
            dateStyle="medium" timeStyle="medium" 
            value="${now}" /></p>
<p>Formatted Date (6): <fmt:formatDate type="both" 
            dateStyle="long" timeStyle="long" 
            value="${now}" /></p>
<p>Formatted Date (7): <fmt:formatDate pattern="yyyy-MM-dd" 
            value="${now}" /></p>

${pageScope.now}
<br>
<fmt:formatDate value="${pageScope.now}" pattern="yyyy-MM-dd hh:mm:ss"/>
</body>
</html>

