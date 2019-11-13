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
    <title>教师主页</title>
    
    <script type="text/javascript">
		function getUrlParams(name){
     		var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     		var r = window.location.search.substr(1).match(reg);
     		//if(r!=null) return unescape(r[2]); 
     		if(r!=null) return decodeURI(r[2]);
     		return null;
		}
	</script>
	
	<script type="text/javascript">
		function writeData(){
     		var teacherid = '<%=session.getAttribute("userid")%>';
     		var courseid = getUrlParams("courseid");
     		document.homework.userid.value = teacherid;
     		document.homework.courseid.value = courseid;
     		
     		document.ppt.userid.value = teacherid;
     		document.ppt.courseid.value = courseid;
     		
     		document.video.userid.value = teacherid;
     		document.video.courseid.value = courseid;
		}
	</script>
    
</head>
<body onload="writeData()">
    <div class="container clearf">
        <div class="container clearf">
            <div class="top-nav clearf">
                <div class="fl">
                    <div class="item">
                        <a href="teacher.jsp"><button type="button" class="btn btn-primary">首页</button></a>
                    </div>
                    <div class="item">
                        <a href="teacher_course.jsp"><button type="button" class="btn btn-primary">课程管理</button></a>
                    </div>  
                </div>
                <div class="fr">
                	<div class="item">
                        <a href="updatepw.jsp"><button type="button" class="btn btn-primary">修改密码</button></a>
                    </div>
                    <div class="item">
                        <a href="teacher_course.jsp"><button type="button" class="btn btn-primary">返回</button></a>
                    </div>
                </div>
            </div>
        </div>
        <div class="container clearf">

			<h3>上传作业要求</h3><br>
				<form name="homework" method="post" action="/MIPS246/UploadServlet" enctype="multipart/form-data">
					<%-- 一定要把文件 放到最后，这样传到后台的数据也会将文件放到最后 --%>
					请输入对应的课程小节:
					<input type="text" name="coursesection"/>
    				<input type="hidden" name="filetype" value="0"/>
    				<input type="hidden" name="roletype" value="teacher"/>
    				<input type="hidden" name="courseid" value=""/>
    				<input type="hidden" name="userid" value=""/>
    				请选择一个文件:
    				<input type="file" name="uploadFile" /><br/><br/>
    				<input type="submit" value="上传" />
				</form>
			<h3>上传课程讲义</h3><br>
				<form name="ppt" method="post" action="/MIPS246/UploadServlet" enctype="multipart/form-data">
					请输入对应的课程小节:
					<input type="text" name="coursesection"/>
    				<input type="hidden" name="filetype" value="3"/>
    				<input type="hidden" name="roletype" value="teacher"/>
    				<input type="hidden" name="courseid" value=""/>
    				<input type="hidden" name="userid" value=""/>
    				请选择一个文件:
    				<input type="file" name="uploadFile" /><br/><br/>
    				<input type="submit" value="上传" />
				</form>
			<h3>上传课程视频</h3><br>
				<form name="video" method="post" action="/MIPS246/UploadServlet" enctype="multipart/form-data">
					请输入对应的课程小节:
					<input type="text" name="coursesection"/>
    				<input type="hidden" name="filetype" value="1"/>
    				<input type="hidden" name="roletype" value="teacher"/>
    				<input type="hidden" name="courseid" value=""/>
    				<input type="hidden" name="userid" value=""/>
    				请选择一个文件:
    				<input type="file" name="uploadFile" /><br/><br/>
    				<input type="submit" value="上传" />
				</form>

        </div>
    </div>
</body>
</html>