<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta charset="utf-8">
<jsp:directive.page import="java.util.*" />
<jsp:directive.page import="com.bobo.entity.Emp" />
<html>
<body>
  <% List<Emp> empList = (List<Emp>) request.getAttribute("empList"); %>
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
  </center>
</body>
</html> 