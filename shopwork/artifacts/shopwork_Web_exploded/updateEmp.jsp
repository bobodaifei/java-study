<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.bobo.entity.Emp" %>
<%@ page import="com.bobo.entity.Dept" %>
<%@ page import="java.util.List" %>

<%
   Emp emp = (Emp) request.getAttribute("emp");
%>


<html>
<head>
    <title>Title</title>
</head>
<body>
    <center>
        <form action="/shopwork/emp?method=update" method="post">
            <input type="hidden" name="empno" value="${emp.getEmpno()}">
            员工姓名：<input type="text" name="ename" value="${emp.getEname()}"><br>
            职业：<input type="text" name="job" value="${emp.getJob()}"><br>
            上司姓名：<select name="mgr"><br>
<%--                        <%for(Emp mgr : mgrList){--%>
<%--                                if(mgr.getEname().equals(emp.getMgrname())){%>--%>
<%--                            <option value="<%=mgr.getEmpno()%>" selected="selected"><%=mgr.getEname()%></option>--%>
<%--                        <%}else{%>--%>
<%--                        <option value="<%=mgr.getEmpno()%>"><%=mgr.getEname()%></option>--%>
<%--                        <%}}%>--%>
                        <c:forEach items="${mgrList}" var="e" varStatus="i">
                            <c:if test="${e.getEmpno() == emp.getMgr()}">
                                <option value="${e.getEmpno()}" selected>${e.getEname()}</option>
                            </c:if>
                            <c:if test="${e.getEmpno() != emp.getMgr()}">
                                <option value="${e.getEmpno()}">${e.getEname()}</option>
                            </c:if>
                        </c:forEach>
                    </select><br>
            入职日期：<input type="date" name="hiredate" value="${emp.getHiredate()}"><br>
            工资：<input type="number" name="sal" value="${emp.getSal()}"><br>
            奖金：<input type="number" name="COMM" value="${emp.getCOMM()}"><br>
            部门编号：<select name="deptno"><br><br>
<%--                        <%for(Dept dept : deptnoList){--%>
<%--                            if(dept.getDeptno().equals(emp.getDeptno())){%>--%>
<%--                        <option value="<%=dept.getDeptno()%>" selected="selected"><%=dept.getDname()%></option>--%>
<%--                        <%}else{%>--%>
<%--                        <option value="<%=dept.getDeptno()%>"><%=dept.getDname()%></option>--%>
<%--                        <%}}%>--%>
                        <c:forEach items="${deptList}" var="d" varStatus="i">
                            <c:if test="${d.getDeptno() eq emp.getDeptno()}">
                                <option value="${d.getDeptno()}" selected>${d.getDname()}</option>
                            </c:if>
                            <c:if test="${d.getDeptno() != emp.getDeptno()}">
                                <option value="${d.getDeptno()}">${d.getDname()}</option>
                            </c:if>
                        </c:forEach>
                    </select>
            <input type="submit" name="提交"><br>
        </form>
    </center>
</body>
</html>
