<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>管理员 教师操作界面</title>

<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>

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
	<h3>添加教师</h3>
	工号：<input id="teacherid" name="teacherid"type="text"value="">
	姓名：<input id="teachername" name="teachername"type="text"value="">
	密码：<input id="password" name="password"type="text"value="">
	简介：<input id="description" name="description"type="text"value="">
	<br>
	<button id="insertTeacher" type="submit">添加教师</button>
</body>
</html>