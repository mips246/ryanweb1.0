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
        $(document).ready(function(){
            $("#insertcourse").click(function(){
            	
                var cid=$("#courseid").val();
                var cname=$("#coursename").val();
                var csc=$("#studentcount").val();
                console.log(cid+" "+cname+" "+csc);
                if (csc==""){
                    csc="0";
                }
                if(cid==""){
                    alert("课程id不能为空");
                    window.location.reload();
                }
                else if(cname==""){
                    alert("课程名称不能为空");
                    window.location.reload();
                }
                else{
                    addCourse(cid,cname,csc);
                }
            })
        })
        function getTime(){
            var time=new Date();
            var year=time.getFullYear();
            var month=time.getMonth()+1;
            var day=time.getDate();
            var hour=time.getHours();
            return year+"-"+month+"-"+day+"-"+hour;
        }
        function addCourse(courseid,coursename,studentcount) {
            $.ajax({
                url:"/MIPS246/AdminCourseServlet",
                type:"POST",
                dataType:"json",
                data:{
                    method:"addCourse",
                    courseid:courseid,
                    coursename:coursename,
                    studentcount:studentcount,
                    createtime:getTime()
                },
                success:function () {
                    alert("成功添加");
                    window.location.reload();
                },
                error:function () {
                    alert("添加失败");
                    window.location.reload();
                }
            });
        }
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
                },
                error:function(jqXHR,textStatus,errorThrown){
                    dealData(jqXHR.responseText);
                }
            });
        }
        function dealData(data) {
            $("#insertPlace").html("");
            var tt="<tr class=''>"
                +"<td class=''>课程号</td>"
                +"<td class=''>课程名</td>"
                +"<td class=''>人数</td>"
                +"<td class=''>创建时间</td>"
                +"<td class=''>选项</td></tr>";
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
                    +"<td class=''>"+createtime+"</td>"
                    //+"<td class=''><button onclick='deleteCourse("+courseid+")'>删除</button></td></tr>";
                    +'<td class=""><button onclick="deleteCourse(\''+courseid+'\')">删除</button></td></tr>';
                $("#insertPlace").append(tt);
            })
        }
        function deleteCourse(courseid){
            $.ajax({
                url:"/MIPS246/AdminCourseServlet",
                type:"post",
                dataType:"JSON",
                data: {
                    method:"deleteCourse",
                    courseid:courseid
                },
                success:function (data) {
                    var deletecourseid=courseid;
                    alert("删除成功"+courseid);
                    loadCourseTable();
                },
                error:function (jqXHR,testStatus,errorThrown) {
                    alert("删除失败");
                }
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
    </div>
    <div class="container">
        <div class="col-5">
            <ul></ul>
            <table>
                <tbody id="insertPlace">
                </tbody>
            </table>
        </div>
        <div class="col-5">
                课程id<input id="courseid" type="text" name="courseid"><br/>
                课程名称<input id="coursename" type="text" name="coursename"><br/>
                学生人限<input id="studentcount" type="text" name="studentcount"><br/>
                <button id="insertcourse" type="submit">添加课程</button>
        </div>
    </div>
</div>
</body>
</html>