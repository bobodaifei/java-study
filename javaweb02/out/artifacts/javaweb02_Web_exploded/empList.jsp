<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta charset="utf-8">
<jsp:directive.page import="java.util.*" /> 
<jsp:directive.page import="com.bobo.entity.Emp" />
<html>
<body>
  <% 
    List<Emp> empList = (List<Emp>) request.getAttribute("empList"); 
    int[] pageSizes = new int[]{5,10,15,20};
    int currentPage = (int)request.getAttribute("currentPage");
    long total = (long)request.getAttribute("total");
    int pageSize = (int)request.getAttribute("pageSize");
  %>
  <center>
    <table border="1">
      <tr>
        <th>员工编号</th>
        <th>员工姓名</th>
        <th>员工岗位</th>
        <th>员工领导</th>
        <th>入职时间</th>
        <th>员工工资</th>
        <th>员工奖金</th>
        <th>部门编号</th>
      </tr>
      <c:forEach items="${empList}" var="item">
        <tr>
          <th>${item.empno}</th>
          <th>${item.ename}</th>
          <th>${item.job}</th>
          <th>${item.bossName}</th>
          <th>${item.hiredate}</th>
          <th>${item.sal}</th>
          <th>${item.comm}</th>
          <th>${item.deptno}</th>
        </tr>
      </c:forEach>
    </table>
    <div style="display:flex">
      <div style="margin-left:10px;">
        <span>Total:</span>${total}
      </div>
      <div style="margin-left:10px;">
        <select name="pageList" onchange="location('/javaweb02/emp?method=selectAll&currentPage=${currentPage}&pageSize='+this.text)">
          <%for (int i = 0; i<pageSizes.length; i++) {%>
          <option <%if (pageSizes[i]==pageSize){%> selected<%}%> ><%=pageSizes[i]%></option>
          <%}%>
        </select>
      </div>
      <div style="margin-left:10px;">
        <a href="/javaweb02/emp?method=selectAll&currentPage=${currentPage-1}&pageSize=${pageSize}" <c:if test="${currentPage==1}"> style="display:none" </c:if> >上一页</a>
      </div>
        当前页：${currentPage}
      <div style="margin-left:10px;">
        <a href="/javaweb02/emp?method=selectAll&currentPage=${currentPage+1}&pageSize=${pageSize}" <c:if test="${(currentPage * pageSize) >= total}"> style="display:none" </c:if> >下一页</a>
      </div>
      <div style="margin-left:10px;">
        Go To <input type="text" name="currentPage" value="${currentPage}"  style="width: 40px;">
      </div>
    </div>
  </center>
</body>
</html> 