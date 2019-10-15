<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>数字逻辑作业</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/normalize/8.0.1/normalize.css">
	<link rel="stylesheet" href="css/main.css">
  	<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
  	<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
  	<script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
  	<script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
  </head>
  <body>
  	<div class="container clearf">
    <div class="top-nav clearf">
      <div class="fl">
        <div class="item">
        	<a href="main.jsp"><button type="button" class="btn btn-primary ">首页</button></a>
        </div>
        <div class="item">
          <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">学习</button>
          <div class="dropdown-menu">
            <a class="dropdown-item" href="study1_1.jsp">数字逻辑理论</a>
            <a class="dropdown-item" href="study1_2.jsp">数字逻辑实验</a>
            <a class="dropdown-item" href="study2_1.jsp">计算机组成原理理论</a>
            <a class="dropdown-item" href="study2_2.jsp">计算机组成原理实验</a>
            <a class="dropdown-item" href="study3_1.jsp">计算机体系结构理论</a>
            <a class="dropdown-item" href="study3_2.jsp">计算机体系结构实验</a>
          </div>
        </div>
        <div class="item">
          <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">课程资料</button>
          <div class="dropdown-menu">
            <a class="dropdown-item" href="file1.jsp">数字逻辑</a>
            <a class="dropdown-item" href="file2.jsp">计算机组成原理</a>
            <a class="dropdown-item" href="file3.jsp">计算机体系结构</a>
          </div>
        </div>
        <div class="item">
          <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">作业</button>
          <div class="dropdown-menu">
            <a class="dropdown-item" href="homework1.jsp">数字逻辑</a>
            <a class="dropdown-item" href="homework1.jsp">计算机组成原理</a>
            <a class="dropdown-item" href="homework1.jsp">计算机体系结构</a>
          </div>
        </div>
      </div>
      <div class="fr">
        <div class="item">
          <a href="logout.jsp"><button type="button" class="btn btn-primary ">退出</button></a>
        </div>
      </div>
    </div>
</div>
  	<form method="post" action="UploadServlet" enctype="multipart/form-data">
    		选择一个文件:
		<input type="file" name="uploadFile" />

		<input type="submit" value="上传" />
		
	</form>
  </body>
</html>
