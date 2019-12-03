<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    
    <link rel="stylesheet" href="https://cdn.bootcss.com/normalize/8.0.1/normalize.css">
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <title>管理员 教师操作界面</title>

	<script type="text/javascript">
    	function teacherJumpToCourseSelect(teacherid,teachername){
    		var url = "admin_teacher_course.jsp?teacherid=" + teacherid +"&teachername="+ teachername;
    		//window.location.href=url;
    		window.open(url);
    	}
    </script>

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
                        tt = "<tr>"
                            +"<td>"+teacherid+"</td>"
                            +"<td>"+teachername+"</td>"
                            +"<td>"+password+"</td>"
                            +"<td>"+description+"</td>"
                            //+"<td><button onclick='deleteTeacher("+teacherid+")'>删除</button></td>"
                            +'<td><button onclick="deleteTeacher(\''+teacherid+'\')">删除</button></td>'
                            //下面两句都能拼出来，第一句是单引号，第二句是双引号(存在bug)
                            //+"<td><button οnclick=\"teacherJumpToCourseSelect('"+teacherid+"','"+teachername+"')\">选课</button></td>"
                            //+'<td><button onclick=teacherJumpToCourseSelect("'+teacherid+'","'+teachername+'")>选课</button></td>'
                            +'<td><button onclick="teacherJumpToCourseSelect(\''+teacherid+'\',\''+teachername+'\')">选课</button></td>'
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
        	var msg=["老师添加失败，您输入的职工号为空", "老师添加失败，您输入的职工号已存在", "老师添加成功"];
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
                success:function(data){
                	var statusCode = data[0].statusCode; 
                    alert(msg[statusCode]);
                    window.location.reload();
                },
                error:function(){
                    alert("添加失败！");
                    window.location.reload();
                }
            });
        }
    </script>

    <script type="text/javascript">
        function deleteTeacher(teacherid){
            $.ajax({
                url:"/MIPS246/AdminTeacherServlet",
                type:"post",
                dataType:"JSON",
                data: {
                    method:"deleteTeacher",
                    teacherid:teacherid
                },
                success:function (data) {
                    var deleteteacherid=teacherid;
                    alert(deleteteacherid+"删除成功！");
                    window.location.reload();
                },
                error:function (jqXHR,testStatus,errorThrown) {
                    alert("删除失败！");
                    window.location.reload();
                }
            })
        }
    </script>
    
</head>


<body onload="loadInfoTable()">
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
    <div class="container clearf">
    	<div class="col-5">
	    	<table border="1">
	            <thead>
	            <tr>
	                <th>工号</th>
	                <th>姓名</th>
	                <th>密码</th>
	                <th>简介</th>
	                <th>点击删除</th>
	                <th>点击选课</th>
	            </tr>
	            </thead>
	
	            <tbody id="insertPlace">
	            </tbody>
	        </table>
    	</div>
        <div class="col-5">
	        <h3>添加教师</h3>
	        	工号：<input id="teacherid" name="teacherid"type="text"value=""><br>
	        	姓名：<input id="teachername" name="teachername"type="text"value=""><br>
	       	 	密码：<input id="password" name="password"type="text"value=""><br>
	        	简介：<input id="description" name="description"type="text"value=""><br>
	        <br>
	        <button id="insertTeacher" type="submit">添加教师</button>
        </div>
    </div>
</div>
</body>
</html>