<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<%@ page language="java" contentType="text/html; charset=UTF-8"
    	pageEncoding="UTF-8"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>管理员 教师选课操作界面</title>
	<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	
	<script type="text/javascript">
		function getUrlParams(name){
     		var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     		var r = window.location.search.substr(1).match(reg);
     		if(r!=null) return unescape(r[2]); 
     		return null;
		}
	</script>
	
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
                },
                error:function(jqXHR,textStatus,errorThrown){
                    dealData(jqXHR.responseText);
                }
            });
        }
        function dealData(data) {
            $("#courseInsertPlace").html("");
            var tt="<tr class=''>"
                +"<td class=''>课程号</td>"
                +"<td class=''>课程名</td>"
                +"<td class=''>人数</td>"
                +"<td class=''>创建时间</td>"
                +"<td class=''>点击选课</td></tr>";
            $("#courseInsertPlace").append(tt);
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
                    +"<td class=''><button onclick='teacherSelectCourse("+courseid+","+coursename+")'>选择</button></td></tr>";
                $("#courseInsertPlace").append(tt);
            })
        }
	</script>
	
	<script type="text/javascript">
		function teacherSelectCourse(courseid,coursename){
			var teacherid = getUrlParams("teacherid");
			var teachername = getUrlParams("teachername");
            $.ajax({
                url:"/MIPS246/AdminTeacherCourseServlet",
                type:"POST",
                dataType:"json",
                data:{
                    method:"selectCourse",
                    teacherid:teacherid,
                    teachername:teachername,
                    courseid:courseid,
                    coursename:coursename
                },
                success:function(){
                    alert("成功添加！");
                    window.location.reload();
                },
                error:function(){
                    alert("添加失败！");
                    window.location.reload();
                }
            });
        }
	</script>
	
</head>
  
<body onload="loadCourseTable()">
	<h3>课程表</h3>
	<table border="1">
		<tbody id="courseInsertPlace">
        </tbody>
    </table>
</body>
</html>