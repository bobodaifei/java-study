<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta charset="utf-8">
<jsp:directive.page import="java.util.*" /> 
<jsp:directive.page import="com.bobo.entity.*" />
<html>

<body>
  <% 
    List<ShopCarVO> list = (List<ShopCarVO>) request.getAttribute("list"); 
    int[] pageSizes = new int[]{5,10,15,20};
    int currentPage = (int)request.getAttribute("currentPage");
    long total = (long)request.getAttribute("total");
    int pageSize = (int)request.getAttribute("pageSize");
  %>
  <center>
    <button onclick="getGoodsList()">提交</button>
    <table border="1">
      <tr>
        <th></th>
        <th>商店名称</th>
        <th>商品名称</th>
        <th>数量</th>
        <th>单价</th>
        <th>总额</th>
        <th>操作</th>
      </tr>
      <c:forEach items="${list}" var="item">
        <tr>
          <th><input name="good" type="checkbox" value="${item.good_no}" /></th>
          <th>${item.shop_name}</th>
          <th>${item.good_name}</th>
          <th>${item.num}</th>
          <th>${item.price}</th>
          <th>${item.num * item.price}</th>
          <th>
              <button onclick="changeNum('${item.good_no}','${item.num}')">修改数量</button>
              <button onclick="submitGoods(['${item.good_no}'])">提交</button>
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
  window.location.href='/shopwork/shopCar?method=selectPage&currentPage='+currentPage+'&pageSize='+pageSize
}
function changeNum(good_no,num) {
  var newNum=prompt("请输入需要修改的数量",num);
  // if (newNum==0&&confirm("数量为0,确认删除该购物车的商品吗?")) {
  //   window.location.href='/shopwork/shopCar?method=delete&customer_no='+'${customer_no}'+'&good_no='+good_no
  //   return;
  // }
  if (newNum!=null && newNum!=""){
    window.location.href='/shopwork/shopCar?method=update&customer_no='+'${customerNo}'+'&good_no='+good_no+'&num='+newNum
  }
}
function submitGoods(goods) {
  if (goods.length==0) {
    return;
  }
  var url = "/shopwork/order?method=add"
  for(i = 0; i < goods.length; i++){
    url = url + "&good=" + goods[i]
  }
  window.location.href=url+""
}
function getGoodsList() {
  var goods = document.getElementsByName("good");
  var goodList = [];
  for(i = 0; i < goods.length; i++){
    if( goods[i].checked ){
      goodList.push(goods[i].value);
    }
  }
  submitGoods(goodList)
}

</script>
</html> 