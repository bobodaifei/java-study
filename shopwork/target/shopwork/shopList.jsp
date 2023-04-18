<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta charset="utf-8">
<jsp:directive.page import="java.util.*" /> 
<jsp:directive.page import="com.bobo.entity.*" />
<html>
<body>
  <% 
    List<Shop> list = (List<Shop>) request.getAttribute("list"); 
    int[] pageSizes = new int[]{5,10,15,20};
    int currentPage = (int)request.getAttribute("currentPage");
    long total = (long)request.getAttribute("total");
    int pageSize = (int)request.getAttribute("pageSize");
  %>
  <center>
 <a href="/shopwork/shopCar?method=selectPage">购物车</a>
 <a href="/shopwork/order?method=selectPage">订单列表</a>
    <table border="1">
      <tr>
        <th>商店名称</th>
        <th></th>
      </tr>
      <c:forEach items="${list}" var="item">
        <tr>
          <th>${item.shop_name}</th>
          <th><a href="/shopwork/good?method=selectPage&shop_no=${item.shop_no}">进入商店</a>  </th>
        </tr>
      </c:forEach>
    </table>
    <div style="display:flex">
      <div style="margin-left:10px;">
        <span>Total:</span>${total}
      </div>
      <div style="margin-left:10px;">
        <select name="pageList" onchange="load('${currentPage}',this.value)">
          <%for (int i = 0; i<pageSizes.length; i++) {%>
          <option value="<%=pageSizes[i]%>" <%if (pageSizes[i]==pageSize){%> selected<%}%> ><%=pageSizes[i]%></option>
          <%}%>
        </select>
      </div>
      <div style="margin-left:10px;">
        <button style="border-color: transparent;" onclick="load('${currentPage-1}','${pageSize}')" <c:if test="${currentPage==1}"> disabled </c:if> >上一页</button>
      </div>
        当前页：${currentPage} 
      <div style="margin-left:10px;">
        <button style="border-color: transparent;" onclick="load('${currentPage+1}','${pageSize}')" <c:if test="${(currentPage * pageSize) >= total}"> disabled </c:if> >下一页</button>
      </div>
      <div style="margin-left:10px;">
        Go To <input type="text" name="currentPage" value="${currentPage}"  style="width: 40px;" onblur="load(this.value,'${pageSize}')">
      </div>
    </div>
  </center>
</body>
<script>
function load(currentPage,pageSize) {
  window.location.href='/shopwork/shop?method=selectPage&currentPage='+currentPage+'&pageSize='+pageSize
}
</script>
</html> 