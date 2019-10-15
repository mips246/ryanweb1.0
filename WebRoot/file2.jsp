<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="stylesheet" href="https://cdn.bootcss.com/normalize/8.0.1/normalize.css">
  <link rel="stylesheet" href="css/main.css">
  <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
  <script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
  <title>同济大学计算机实验平台</title>
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
</body>
</html>
