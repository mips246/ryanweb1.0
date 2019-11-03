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
    <title>admin_course</title>
    <script type="text/javascript">
        function loadCourseTable() {
            $.ajax({
                url:"/MIPS246/AdminCourseServlet",
                type:"POST",
                dataType:"json",
                data:{
                    method:"loadCourse"
                },
                success:function (data) {
                    dealData(data);
                }
            });
        }
        function dealData(data) {
            var tt="<tr class=''>"
                +"<td class=''>课程号</td>"
                +"<td class=''>课程名</td>"
                +"<td class=''>人数</td>"
                +"<td class=''>创建时间</td></tr>";
            $("#insertPlace").append(tt);
            $.each(data,function (index) {
                var courseid=data[index].courseid;
                var coursename=data[index].coursename;
                var studentcount=data[index].studentcount;
                var createtime=data[index].createtime;
                tt="<tr class=''>"
                    +"<td class=''>"+courseid+"</td>"
                    +"<td class=''>"+coursename+"</td>"
                    +"<td class=''>"+studentcount+"</td>"
                    +"<td class=''>"+createtime+"</td></tr>";
                $("#insertPlace").append(tt);
            })
        }
    </script>
</head>
<body onload="loadCourseTable()">
<%
request.setCharacterEncoding("GBK");
%>
<div class="container clearf">
    <div class="container clearf">
        <div class="top-nav clearf">
            <div class="fl">
                <div class="item">
                    <a href=""><button type="button" class="btn btn-primary">首</button></a>
                </div>
                <div class="item">
                    <a href=""><button type="button" class="btn btn-primary">课程</button></a>
                </div>
                <div class="item">
                    <a href=""><button type="button" class="btn btn-primary">老师</button></a>
                </div>
                <div class="item">
                    <a href=""><button type="button" class="btn btn-primary">学生</button></a>
                </div>
            </div>
            <div class="fr">
                <div class="item">
                    <a href=""><button type="button" class="btn btn-primary">退出</button></a>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div>
            <ul></ul>
            <table>
                <tbody id="insertPlace">
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>