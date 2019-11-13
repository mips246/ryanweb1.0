<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>TesT</title>

<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>

<script type="text/javascript">
	function loadInfoTable(){
		$.ajax({
			url:"/MIPS246/TestServlet",
			type:"POST",
			data:{
            	message:"TEST!!!",
            },
            dataType:"json",
            success:function(data){  
            	//data = eval("("+data+")"); //数组     
                $.each(data, function (index) {  
                    //循环获取数据    
                	var stu_name = data[index].sname;
                    var stu_no = data[index].sno;
                    var stu_age = data[index].sage;
                    tt =	"<tr>"
                    		+"<td>"+stu_no+"</td>"
                    		+"<td>"+stu_name+"</td>"
                        	+"<td>"+stu_age+"</td>"
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
				<th>学号</th>
                <th>姓名</th>
                <th>年龄</th>
            </tr>
        </thead>
        
        <tbody id="insertPlace">
        </tbody>
	</table>
	<div>
	<a href="\WebRoot\CS111111\123456\ResNet.pptx" download="resnet">resnet</a>
	</div>
</body>
</html>