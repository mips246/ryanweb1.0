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
	<script>
	    function check(){                 
	        var ids = document.getElementsByName("role");                  
	        var flag = false ;                
	        for(var i=0;i<ids.length;i++){  
	            if(ids[i].checked){  
	                flag = true ;  
	                break ;  
	            }  
	        }  
	        if(!flag){  
	            alert("请选择身份！");  
	            return false ;  
	        }  
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
          <button type="button" class="btn btn-primary">联系我们</button>
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
		<form action='LoginServletNew' method='post' onsubmit="return validate(this)">
			<div class="col-5 inputbutton" align="center">
		        <input type='text' class="form_input" placeholder="Enter your account" name='userid' value=''/><br/>
			    <input type='password' class="form_input" placeholder="Enter your password" name='userpass' value=''/><br/>
			    <input type='submit' value='登录'/>  <br/>
			</div>
			<div class="col-5" align="center">
			    <input type="radio" name="role" value="student" checked="checked">学生
	            <input type="radio" name="role" value="teacher">教师
	            <input type="radio" name="role" value="admin">管理员
			</div>
		</form>
	</div>


	<div class="footer clearf">
		<div class="container">
			<p align="center">同济大学国家计算机实验示范中心</p>
		</div>
	</div>
</body>
</html>