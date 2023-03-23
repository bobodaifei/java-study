<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

    <meta charset="utf-8">

<html>
<body>
<h2>Hello World!</h2>
<form method="post" action="uploadServlet" enctype="multipart/form-data">
  uname:<input type="text" name="uname"/>
  file:<input type="file" name="myFile"/>
  <button type="submit">提交</button>
</form>
<a href="downLoad/aaa.txt" download>downtxt</a>
<a href="downLoad/bbb.png" download="ppp.png">downpng</a>
<a href="downLoad/ccc.zip">downzip</a>
<form action="downServlet" >
  fileName:<input type="text" name="fileName"/>
  <button type="submit">提交</button>
</form>
</body>
</html>
