<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.bootcss.com/normalize/8.0.1/normalize.css">
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <title>管理员首页</title>
</head>
<body>
<%
request.setCharacterEncoding("GBK");
%>
<div class="container clearf">
    <div class="container clearf">
        <div class="top-nav clearf">
            <div class="fl">
                <div class="item">
                    <a href="admin.jsp"><button type="button" class="btn btn-primary">首页</button></a>
                </div>
                <div class="item">
                    <a href="admin_course.jsp"><button type="button" class="btn btn-primary">课程</button></a>
                </div>
                <div class="item">
                    <a href="admin_teacher.jsp"><button type="button" class="btn btn-primary">老师</button></a>
                </div>
                <div class="item">
                    <a href="admin_student.jsp"><button type="button" class="btn btn-primary">学生</button></a>
                </div>
            </div>
            <div class="fr">
                <div class="item">
                    <a href="../logout.jsp"><button type="button" class="btn btn-primary">退出</button></a>
                </div>
            </div>
        </div>
        <h1>欢迎管理员</h1>
        <h3>本页面是管理员的主页，有三个模块功能，分别是:</h3>
        <h4>功能1</h4>
        <p>课程的增删改，指定某课程的老师</p>
        <h4>功能2</h4>
        <p>对学生的增删改，并且查看学生的选课信息</p>
        <h4>功能3</h4>
        <p>对老师的增删改，查看所有老师的信息</p>
    </div>
</div>
</body>
</html>