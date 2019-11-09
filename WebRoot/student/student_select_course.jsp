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
    <title>学生选课</title>
    <script type="text/javascript">
        function loadCourseTable() {
            $.ajax({
                url:"",
                type:"POST",
                data:{
                    method:"loadCourseTable"
                },
                dataType:"json",
                success:function (data) {
                    $.each(data,function (index) {
                        var courseid=data[index].courseid;
                        var coursename=data[index].coursename;
                        var limitcount=data[index].limitcount;
                        var studentcount=data[index].studentcount;
                        var teachername=data[index].teachername;
                        var tt="<tr>"
                        +"<td>"+courseid+"</td>"
                        +"<td>"+coursename+"</td>"
                        +"<td>"+limitcount+"</td>"
                        +"<td>"+studentcount+"</td>"
                        +"<td>"+teachername+"</td>"
                        +"<td><button onclick='selectCourse(\''+courseid+'\',\''+studentid+'\',\''+teacherid+'\')'>选课</button></td></tr>";
                        $("#insertPlace").append(tt);
                    })
                }
            })
        }
        function selectCourse(courseid,studentid,teacherid) {
            $.ajax({
                url:"",
                type:"POST",
                dataType:"json",
                data:{
                    method:"selectCourse",
                    courseid:courseid,
                    studentid:studentid,
                    teacherid:teacherid
                },
                success:function () {
                    alert(studentid+":选课成功！ "+courseid);
                    window.location.reload();
                },
                error:function () {
                    alert(studentid+":选课失败！ "+courseid);
                    window.location.reload();
                }
            })
        }
        function quitCourse(courseid,studentid,teacherid) {
            $.ajax({
                url:"",
                type:"POST",
                dataType:"json",
                data:{
                    method:"quitCourse",
                    courseid:courseid,
                    studentid:studentid,
                    teacherid:teacherid
                },
                success:function () {
                    alert(studentid+":退选成功！ "+courseid);
                    window.location.reload();
                },
                error:function () {
                    alert(studentid+":退选失败！ "+courseid);
                    window.location.reload();
                }
            })
        }
        function loadSelectedCourseTable() {
            $.ajax({
                url:"",
                type:"POST",
                dataType:"json",
                data:{
                    method:"loadCourse"
                },
                success:function (data) {
                    dealData(data);
                },
                error:function(jqXHR,textStatus,errorThrown){
                    dealData(jqXHR.responseText);
                }
            });
        }
        function dealData(data) {
            $("#insertPlace2").html("");
            var tt="<tr class=''>"
                +"<td class=''>课程id</td>"
                +"<td class=''>课程名</td>"
                +"<td class=''>总成绩</td></tr>";
            $("#insertPlace2").append(tt);
            $.each(data,function (index) {
                var courseid=data[index].courseid;
                var coursename=data[index].coursename;
                var wholegrade=data[index].wholegrade;
                tt="<tr class=''>"
                    +"<td class=''>"+courseid+"</td>"
                    +"<td class=''>"+coursename+"</td>"
                    +"<td class=''>"+wholegrade+"</td>"
                    +"<td><button onclick='quitCourse(\''+courseid+'\',\''+studentid+'\',\''+teacherid+'\')'>退课</button></td></tr>";
                $("#insertPlace2").append(tt);
            })
        }
    </script>
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
                <a href="#"><button type="button" class="btn btn-primary ">课程资料</button></a>
            </div>
            <div class="item">
                <a href="#"><button type="button" class="btn btn-primary ">作业</button></a>
            </div>
        </div>
        <div class="fr">
            <div class="item">
                <!--<button type="button" class="btn btn-primary"><a href="logout.jsp">退出</a></button>只能a标签套button，button不能套a  -->
                <a href="logout.jsp"><button type="button" class="btn btn-primary ">退出</button></a>
                <a href="info.jsp"><button type="button" class="btn btn-primary ">个人信息修改</button></a>
            </div>
        </div>
    </div>
</div>
<div class="container clearf">
    <div class="col-5">
        <h3 align="center">课程列表</h3>
        <table border="1" align="center">
            <thead>
            <tr>
                <th>课程ID</th>
                <th>课程名</th>
                <th>限选人数</th>
                <th>已选人数</th>
                <th>授课教师</th>
                <th>选课</th>
            </tr>
            </thead>
            <tbody id="insertPlace">
            </tbody>
        </table>
    </div>
    <div class="col-5">
        <h3 align="center">已选列表</h3>
        <table border="1" align="center">
            <thead>
            <tr>
                <th>课程ID</th>
                <th>课程名</th>
                <th>总成绩</th>
                <th>选项</th>
            </tr>
            </thead>
            <tbody id="insertPlace2">
            </tbody>
        </table>
    </div>
</div>
</body>
</html>