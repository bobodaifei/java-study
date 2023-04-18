<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:directive.page import="java.util.*" /> 
<jsp:directive.page import="com.bobo.entity.*" />
<meta charset="utf-8">
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
  <table border="1">
    <tr>
      <th>商品编号</th>
      <th>商品名称</th>
      <th>库存</th>
      <th>上下架</th>
      <th>价格</th>
    </tr>
    <tr>
      <th>${stock.good_no}</th>
      <th>${stock.good_name}</th>
      <th>${stock.stock}</th>
      <th>
        <c:if test="${stock.is_online==1}">已上架</c:if>
        <c:if test="${stock.is_online==0}">已下架</c:if>
      </th>
      <th>${stock.price}</th>
    </tr>
  </table>
  <table border="1">
      <tr>
        <th>订单编号</th>
        <th>下单姓名</th>
        <th>手机号</th>
        <th>地址</th>
        <th>创建时间</th>
        <th>状态</th>
        <th>数量</th>
        <th>价格</th>
        <th>总价格</th>
      </tr>
      <c:forEach items="${list}" var="item">
        <tr>
          <th>${item.order_no}</th>
          <th>${item.name}</th>
          <th>${item.mobile}</th>
          <th>${item.address}</th>
          <th>${item.create_date}</th>
          <th>
            <c:if test="${item.status==1}">待支付</c:if>
            <c:if test="${item.status==2}">待发货</c:if>
            <c:if test="${item.status==3}">待接收</c:if>
            <c:if test="${item.status==4}">已完成</c:if>
          </th>
          <th>${item.num}</th>
          <th>${item.price}</th>
          <th>${item.totalPrice}</th>
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
  console.log(currentPage,pageSize);
  window.location.href='/userwork/orderDetail?method=selectPageByGoodNo&good_no='+'${good_no}'+'&currentPage='+currentPage+'&pageSize='+pageSize
}
</script>
</html> 