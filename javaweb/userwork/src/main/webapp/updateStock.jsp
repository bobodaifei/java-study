<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:directive.page import="java.util.*" /> 
<jsp:directive.page import="com.bobo.entity.*" />
<meta charset="utf-8">
<html>
<body>
<% 
    StockVO stock = (StockVO) request.getAttribute("stock"); 
  %>
<center>
  <form action="/userwork/stock?method=update" method="post">
    <input type="hidden" name="good_no" value="${stock.good_no}">
    商品名称：${stock.good_name}<br>
    库存：<input type="text" name="stock" value="${stock.stock}"/><br/>
    上下架：
    <select name="is_online">
      <option value="1" <c:if test="${stock.is_online==1}">selected</c:if>>上架</option>
      <option value="0" <c:if test="${stock.is_online==0}">selected</c:if>>下架</option>
    </select>
    <br/>
    <button>修改</button>
  </form>
</center>

</body>
</html> 