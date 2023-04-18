<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta charset="utf-8">
<jsp:directive.page import="java.util.*" /> 
<jsp:directive.page import="com.bobo.entity.*" />
<html>
<body>
  <% 
    List<StockVO> list = (List<StockVO>) request.getAttribute("list"); 
    int[] pageSizes = new int[]{5,10,15,20};
    int currentPage = (int)request.getAttribute("currentPage");
    long total = (long)request.getAttribute("total");
    int pageSize = (int)request.getAttribute("pageSize");
  %>
  <div style="width:100%; height:30px; display:flex;justify-content:space-between">
    <div>欢迎店员${userName}登录</div>
    <div>${shopName}商店</div>
    <div><a href="/userwork/user?method=logout">退出登录</a></div>
  </div>
  
  <center>
    <a href="/userwork/stock?method=toAdd">新增</a>
    <a href="/userwork/order?method=selectPage">查看全部订单</a>
    <table border="1">
      <tr>
        <th>商品编号</th>
        <th>商品名称</th>
        <th>商品价格</th>
        <th>商品库存</th>
        <th>上架状态</th>
        <th>操作</th>
      </tr>
      <c:forEach items="${stocks}" var="item">
        <tr>
          <th>${item.good_no}</th>
          <th>${item.good_name}</th>
          <th>
            ${item.price}
          </th>
          <th>
            ${item.stock}
          </th>
          <th>
            <c:if test="${item.is_online==1}">已上架</c:if>
            <c:if test="${item.is_online==0}">已下架</c:if>
          </th>
          <th>
            <a href="/userwork/stock?method=selectOne&good_no=${item.good_no}">修改</a> 
            <a href="/userwork/stock?method=delete&good_no=${item.good_no}">删除</a>
            <a href="/userwork/orderDetail?method=selectPageByGoodNo&good_no=${item.good_no}">详细</a>
          </th>
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
  window.location.href='/userwork/stock?method=selectPage&currentPage='+currentPage+'&pageSize='+pageSize
}
</script>
</html> 