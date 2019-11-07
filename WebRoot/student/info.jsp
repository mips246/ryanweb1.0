<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://cdn.bootcss.com/normalize/8.0.1/normalize.css">
    <link rel="stylesheet" href="../css/main.css">
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
                <a href="student_main.jsp"><button type="button" class="btn btn-primary ">首页</button></a>
            </div>
            <div class="item">
                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">课程</button>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="#">选课</a>
                    <a class="dropdown-item" href="#">上课</a>
                </div>
            </div>
            <div class="item">
                <a href="#"><button type="button" class="btn btn-primary ">首页</button></a>
            </div>
            <div class="item">
                <a href="#"><button type="button" class="btn btn-primary ">作业</button></a>
            </div>
        </div>
        <div class="fr">
            <div class="item">
                <!--<button type="button" class="btn btn-primary"><a href="logout.jsp">退出</a></button>  -->
                <a href="logout.jsp"><button type="button" class="btn btn-primary ">退出</button></a>
                <a href="info.jsp"><button type="button" class="btn btn-primary ">个人信息</button></a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
