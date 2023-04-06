<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<meta charset="utf-8">
<jsp:directive.page import="java.util.*" />
<jsp:directive.page import="com.xxx.entry.*" />
<html>
<body>
  <%
    request.setAttribute("msg","aoligei");
    request.setAttribute("msg1","");
    request.setAttribute("msg2",null);

    List<String> list1 = null;
    List<String> list2 = new ArrayList<>();
    List<String> list3 = new ArrayList<>();
    list3.add("aa1");
    request.setAttribute("list1",list1);
    request.setAttribute("list2",list2);
    request.setAttribute("list3",list3);

    Map map1 = null;
    Map map2 = new HashMap();
    Map map3 = new HashMap();
    map3.put("col1","123");
    request.setAttribute("map1",map1);
    request.setAttribute("map2",map2);
    request.setAttribute("map3",map3);

    User user1 = null;
    User user2 = new User();
    User user3 = new User();
    user3.setName("aolig");
    request.setAttribute("user1",user1);
    request.setAttribute("user2",user2);
    request.setAttribute("user3",user3);
  %>
  ${empty msg}
  ${empty msg1}
  ${empty msg2}
  ${empty msg3}
  <br>
  ${empty list1}
  ${empty list2}
  ${empty list3}
  <br>

  ${empty map1}
  ${empty map2}
  ${empty map3}
  <br>

  ${empty user1}
  ${empty user2}
  ${empty user3}
</body>
</html>
