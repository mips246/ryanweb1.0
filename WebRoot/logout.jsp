<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>logout</title>

  </head>
  
  <body>
  <%
  	session.invalidate();
  	response.sendRedirect("login.jsp");
   %>
   <h3>成功退出，2秒后跳转</h3>
  </body>
</html>
