<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:directive.page import="java.util.*" /> 
<jsp:directive.page import="com.bobo.entity.*" />
<meta charset="utf-8">
<html>
<body>
<center>
  <form action="/userwork/stock?method=add" method="post">
    <select name="good_no">
      <c:forEach items="${list}" var="item">
        <option value="${item.good_no}" >${item.good_name}</option>
      </c:forEach>
    </select><br/>
    库存：<input type="text" name="stock"/><br/>
    上下架：
    <select name="is_online">
      <option value="1">上架</option>
      <option value="0">下架</option>
    </select>
    <br/>
    <button>新增</button>
  </form>
</center>

</body>
</html> 