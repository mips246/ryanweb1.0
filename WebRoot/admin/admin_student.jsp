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
    <title>管理员_学生操作</title>

    <script type="text/javascript">
        $(document).ready(function(){
            $("#insertstudent").click(function(){
                var id=$("#id").val();
                var name=$("#name").val();
                var password=$("#password").val();
                console.log("进入insertstudent"+id+" "+name+" "+password);
                if (id==""){
                    alert("请输入学号");
                    window.location.reload();
                }

                else if(password==""){
                    password=id;
                }
                    addStudent(id,name,password);
            })
        })
        function addStudent(id,name,password) {
        	console.log("进入addStudent")
            $.ajax({
                url:"/MIPS246/AdminStudentServlet",
                type:"POST",
                dataType:"json",
                data:{
                    method:"addStudent",
                    studentid:id,
                    name:name,
                    password:password
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
        function loadStudentTable() {
            $.ajax({
                url:"/MIPS246/AdminStudentServlet",
                type:"POST",
                dataType:"json",
                data:{
                    method:"loadStudentTable"
                },
                success:function (data) {
                    console.log("进入success");
                    dealData(data);
                },
                error:function(jqXHR,textStatus,errorThrown){
                	console.log("进入error");
                    dealData(jqXHR.responseText);
                }
            });
        }
        function dealData(data) {
            $("#insertPlace").html("");
            var tt="<tr class=''>"
                +"<td class=''>id</td>"
                +"<td class=''>姓名</td>"
                +"<td class=''>密码</td>"
                +"<td class=''>选项</td></tr>";
            $("#insertPlace").append(tt);
            $.each(data,function (index) {
                var id=data[index].sid;
                var name=data[index].sname;
                var password=data[index].spassword;
                tt="<tr class=''>"
                    +"<td class=''>"+id+"</td>"
                    +"<td class=''>"+name+"</td>"
                    +"<td class=''>"+password+"</td>"
                    +"<td class=''><button onclick='deleteStudent("+id+")'>删除</button></td></tr>";
                $("#insertPlace").append(tt);
            })
        }
        function deleteStudent(id){
            $.ajax({
                url:"/MIPS246/AdminStudentServlet",
                type:"post",
                dataType:"JSON",
                data: {
                    method:"deleteStudent",
                    studentid:id
                },
                success:function (data) {
                    var deletestudentid=id;
                    alert("删除成功"+deletestudentid);
                    loadStudentTable();
                },
                error:function (jqXHR,testStatus,errorThrown) {
                    alert("删除失败");
                }
            })
        }
    </script>

</head>
<body onload="loadStudentTable()">
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
                	学生id<input id="id" type="text" name="id"><br/>
                	姓名<input id="name" type="text" name="name"><br/>
                password<input id="password" type="password" name="password"><br/>
                <button id="insertstudent" type="submit">添加</button>
        </div>
    </div>
</div>
</body>
</html>
