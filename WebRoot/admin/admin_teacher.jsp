<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.bootcss.com/normalize/8.0.1/normalize.css">
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <title>管理员_老师操作</title>

    <script type="text/javascript">
        function loadInfoTable(){
            $.ajax({
                url:"/MIPS246/AdminTeacherServlet",
                type:"POST",
                data:{
                    method:"selectAllTeacher"
                },
                dataType:"json",
                success:function(data){
                    $.each(data, function (index) {
                        var teacherid = data[index].teacherid;
                        var teachername = data[index].teachername;
                        var password = data[index].password;
                        var description = data[index].description;
                        tt =	"<tr>"
                            +"<td>"+teacherid+"</td>"
                            +"<td>"+teachername+"</td>"
                            +"<td>"+password+"</td>"
                            +"<td>"+description+"</td>"
                            +"</tr>";
                        $("#insertPlace").append(tt);
                    });
                }
            });
        }
    </script>

    <script>
        $(document).ready(function(){
            $("#insertTeacher").click(function(){

                var tid 		 = $("#teacherid").val();
                var tname 		 = $("#teachername").val();
                var tpassword 	 = $("#password").val();
                var tdescription = $("#description").val();
                if(tid==""){//null是空字符串，""是没有填写该信息
                    alert("工号不能为空！");
                }
                else if(tname==""){
                    alert("姓名不能为空！");
                }
                else{
                    InsertTeacher(tid,tname,tpassword,tdescription);
                }
            })
        })
    </script>

    <script type="text/javascript">
        function InsertTeacher(tid,tname,tpassword,tdescription){
            $.ajax({
                url:"/MIPS246/AdminTeacherServlet",
                type:"POST",
                dataType:"json",
                data:{
                    method:"insertTeacher",
                    teacherid:tid,
                    teachername:tname,
                    password:tpassword,
                    description:tdescription
                },
                success:function(){
                    alert("成功添加！");
                },
                error:function(){
                    alert("添加失败！");
                }
            });
        }
    </script>
</head>
<body onload="loadInfoTable()">
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
                    <a href=""><button type="button" class="btn btn-primary">退出</button></a>
                </div>
            </div>
        </div>
        <div class="container clearf">
            <div>
                <table border="1">
                    <thead>
                    <tr>
                        <th>工号</th>
                        <th>姓名</th>
                        <th>密码</th>
                        <th>简介</th>
                    </tr>
                    </thead>

                    <tbody id="insertPlace">
                    </tbody>
                </table>
            </div>
        </div>
        <div class="container clearf">
            <h3>添加教师</h3>
            工号：<input id="teacherid" name="teacherid"type="text"value="">
            姓名：<input id="teachername" name="teachername"type="text"value="">
            密码：<input id="password" name="password"type="text"value="">
            简介：<input id="description" name="description"type="text"value="">
            <br>
            <button id="insertTeacher" type="submit">添加教师</button>
        </div>
    </div>
</div>
</body>
</html>
