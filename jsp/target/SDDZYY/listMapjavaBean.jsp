<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<meta charset="utf-8">
<jsp:directive.page import="java.util.*" />
<jsp:directive.page import="com.xxx.entry.*" />
<html>
<body>
<%
  List<String> list = new ArrayList<>();
  list.add("aa1");
  list.add("aa2");
  list.add("aa3");
  request.setAttribute("list",list);

  Map map = new HashMap();
  map.put("col1","123");
  map.put("col2",123);
  map.put("col3",12.5);
  request.setAttribute("map",map);

  User user = new User();
  user.setName("aolig");
  user.setAge(12);
  request.setAttribute("user",user);
%>
${list[0]}
${list.size()}

${map["col1"]}
${map.col1}

${user.getName()}
${user.age}
</body>
</html>