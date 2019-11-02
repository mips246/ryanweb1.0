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
</body>
</html>