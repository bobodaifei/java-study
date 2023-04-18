<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta charset="utf-8">
<jsp:directive.page import="java.util.*" /> 
<jsp:directive.page import="com.bobo.entity.*" />
<html>
<body>
  <% 
    List<OrderVO> list = (List<OrderVO>) request.getAttribute("list"); 
    int[] pageSizes = new int[]{5,10,15,20};
    int currentPage = (int)request.getAttribute("currentPage");
    long total = (long)request.getAttribute("total");
    int pageSize = (int)request.getAttribute("pageSize");
  %>
  <center>
    <select name="status" onchange="loadStatus(this.value)">
      <option value="0">全部</option>
      <option value="1" <c:if test="${status==1}">selected</c:if>>待支付</option>
      <option value="2" <c:if test="${status==2}">selected</c:if>>待发货</option>
      <option value="3" <c:if test="${status==3}">selected</c:if>>待接收</option>
      <option value="4" <c:if test="${status==4}">selected</c:if>>已完成</option>
    </select>
    <table border="1">
      <tr>
        <th>订单编号</th>
        <th>下单人</th>
        <th>收货地址</th>
        <th>手机号</th>
        <th>创建时间</th>
        <th>状态</th>
        <th>价格</th>
        <th>操作</th>
      </tr>
      <c:forEach items="${list}" var="item">
        <tr>
          <th>${item.order_no}</th>
          <th>${item.shop_name}</th>
          <th>${item.address}</th>
          <th>${item.mobile}</th>
          <th>${item.create_date}</th>
          <th>
            <c:if test="${item.status==1}">待支付</c:if>
            <c:if test="${item.status==2}">待发货</c:if>
            <c:if test="${item.status==3}">待接收</c:if>
            <c:if test="${item.status==4}">已完成</c:if>
          </th>
          <th>${item.price}</th>
          <th><a href="/userwork/orderDetail?method=selectPage&order_no=${item.order_no}">查看详细</a>  </th>
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
  window.location.href='/userwork/order?method=selectPage&currentPage='+currentPage+'&pageSize='+pageSize
}
function loadStatus(status) {
  if (status==0) {
    window.location.href='/userwork/order?method=selectPage'
    return;
  }
  window.location.href='/userwork/order?method=selectPage&status='+status
}
</script>
</html> 