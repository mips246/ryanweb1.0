<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'uploadhomework.jsp' starting page</title>
    
	<meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.bootcss.com/normalize/8.0.1/normalize.css">
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function getUrlParams(name){
     		var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     		var r = window.location.search.substr(1).match(reg);
     		//if(r!=null) return unescape(r[2]); 
     		if(r!=null) return decodeURI(r[2]);
     		return null;
		}
	</script>
	<script type="text/javascript">
		function writeData(){
     		var courseid = getUrlParams("courseid");
     		var section = getUrlParams("section");
     		var userid = getUrlParams("userid");
     		document.homework.section.value = section;
     		document.homework.courseid.value = courseid;
     		document.homework.userid.value = userid;
		}
	</script>

  </head>
  
  <body onload="writeData()">
    <form name="homework" method="post" action="/MIPS246/UploadServlet" enctype="multipart/form-data">
					<%-- 一定要把文件 放到最后，这样传到后台的数据也会将文件放到最后 --%>
					<input type="hidden" name="section" value="">
    				<input type="hidden" name="filetype" value="0"/>
    				<input type="hidden" name="roletype" value="student"/>
    				<input type="hidden" name="courseid" value=""/>
    				<input type="hidden" name="userid" value=""/>
    				请选择一个文件:
    				<input type="file" name="uploadFile" /><br/><br/>
    				<input type="submit" value="上传" />
	</form>
  </body>
</html>
