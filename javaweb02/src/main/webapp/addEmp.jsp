<%@ page import="com.bobo.entity.Emp" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bobo.entity.Dept" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>添加用户</title>
</head>
<body>
    <form action="/javaweb02/emp?method=add" method="post">
        用户名：<input type="text" name="ename"><br>
        工作：<input type="text" name="job"><br>
        上司姓名：<select name="mgr"><br>
                    <c:forEach items="${mgrList}" var="emp" varStatus="i">
                        <option value="${emp.getEmpno()}">${emp.getEname()}</option>
                    </c:forEach>
                </select>
        入职日期：<input type="date" name="hiredate"><br>
        工资：<input type="number" name="sal"><br>
        奖金：<input type="number" name="COMM"><br>
        部门编号：<select name="deptno"><br>
                    <c:forEach items="${deptList}" var="no">
                            <option value="${no.getDeptno()}" selected>${no.getDname()}</option>
                    </c:forEach>
                </select>
        <input type="submit" name="提交"><br>
    </form>
</body>
</html>
