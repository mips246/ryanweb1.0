<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<base href="<%=basePath%>">
	<meta charset="UTF-8">
	<title>计算机系统设计实验平台</title>
	<link rel="stylesheet" href="https://cdn.bootcss.com/normalize/8.0.1/normalize.css">
	<link rel="stylesheet" href="css/main.css">
  	<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
  	<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
  	<script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
  	<script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
  <title>同济大学计算机实验平台</title>
	<script language="javascript">
  		function validate(f){
    		if(!(/^\w{5,15}$/.test(f.userid.value))){
      			alert("用户id必须是5-15位");
      			f.userid.focus();
      			return false;
    		}
    		return true;
 		}
	</script>
</head>
<body>
<%
	request.setCharacterEncoding("GBK");
%>
<div class="container clearf">
    <div class="container clearf">
    <div class="top-nav clearf">
      <div class="fl">
        <div class="item">
          <a href="main.jsp"><button type="button" class="btn btn-primary ">首页</button></a>
        </div>
        <div class="item">
          <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">学习</button>
          <div class="dropdown-menu">
            <a class="dropdown-item" href="study1_1.jsp">数字逻辑理论</a>
            <a class="dropdown-item" href="study1_2.jsp">数字逻辑实验</a>
            <a class="dropdown-item" href="study2_1.jsp">计算机组成原理理论</a>
            <a class="dropdown-item" href="study2_2.jsp">计算机组成原理实验</a>
            <a class="dropdown-item" href="study3_1.jsp">计算机体系结构理论</a>
            <a class="dropdown-item" href="study3_2.jsp">计算机体系结构实验</a>
          </div>
        </div>
        <div class="item">
          <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">课程资料</button>
          <div class="dropdown-menu">
            <a class="dropdown-item" href="file1.jsp">数字逻辑</a>
            <a class="dropdown-item" href="file2.jsp">计算机组成原理</a>
            <a class="dropdown-item" href="file3.jsp">计算机体系结构</a>
          </div>
        </div>
        <div class="item">
          <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">作业</button>
          <div class="dropdown-menu">
            <a class="dropdown-item" href="homework1.jsp">数字逻辑</a>
            <a class="dropdown-item" href="homework1.jsp">计算机组成原理</a>
            <a class="dropdown-item" href="homework1.jsp">计算机体系结构</a>
          </div>
        </div>
      </div>
      <div class="fr">
        <div class="item">
          <button type="button" class="btn btn-primary ">登陆</button>
        </div>
        <div class="item">
          <button type="button" class="btn btn-primary ">注册</button>
        </div>
      </div>
    </div>
</div>
<%
 	List<String>info=(List<String>)request.getAttribute("info");
 	if(info!=null){
 		Iterator<String>iter=info.iterator();
 		while(iter.hasNext()){
%>
			<h4><%=iter.next()%></h4>
<%
		}
	}
%>
	<div class="inputstyle">
		<div class="col-5 inputbutton" align="center">
			<form action='LoginServlet' method='post' onsubmit="return validate(this)">
		        <input type='text' class="form_input" placeholder="Enter your account" name='userid' value=''/><br/>
			    <input type='password' class="form_input" placeholder="Enter your password" name='userpass' value=''/><br/>
			    <input type='submit' value='登录'/>  <br/>
			</form>
		</div>
		<div class="col-5" align="center">
			<select>
               <option name="usertype" value="1">学生</option>
               <option name="usertype" value="2">教师</option>
               <option name="usertype" value="3">助教</option>
               <option name="usertype" value="0">管理员</option>
            </select>
		</div>
	</div>


	<div class="footer clearf">
		<div class="container">
			<p align="center">同济大学国家计算机实验示范中心</p>
		</div>
	</div>
</body>
</html>