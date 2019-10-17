<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="stylesheet" href="https://cdn.bootcss.com/normalize/8.0.1/normalize.css">
  <link rel="stylesheet" href="css/main.css">
  <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
  <script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
  <title>同济大学计算机实验平台</title>
</head>
<body>
  <div class="container clearf">
    <div class="top-nav clearf">
      <div class="fl">
        <div class="item">
          <a href="main.jsp"><button type="button" class="btn btn-primary ">首页</button></a>
        </div>
        <div class="item">
          <a href="study_menu.jsp"><button type="button" class="btn btn-primary" >学习</button></a>
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
            <a class="dropdown-item" href="homework2.jsp">计算机组成原理</a>
            <a class="dropdown-item" href="homework3.jsp">计算机体系结构</a>
          </div>
        </div>
      </div>
      <div class="fr">
        <div class="item">
          <!--<button type="button" class="btn btn-primary"><a href="logout.jsp">退出</a></button>  -->
          <a href="logout.jsp"><button type="button" class="btn btn-primary ">退出</button></a>
          </div>
      </div>
    </div>
  </div>
  <div class="header clearf">
    <div class="container">
      <div class="col-5">
        <h3>同济大学计算机实验平台</h3>
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
        <br/>
        
        <p>针对现今计算机专业实验教学过程中存在的验证性实验为主、实验技术孤立、内容分散等问题，我们提出了一种面向计算机专业的贯通式实验改革方案。该实验方案从设计处理器部件出发，到实现自己的处理器，然后围绕设计的处理器展开对处理器的优化，在该处理器上设计编译器以及操作系统。实验内容覆盖多门计算机专业基础课和专业课，前后联系紧密，建立了“前继课程实验结果作为后续课程实验的基础，后续课程实验的过程作为前继课程实验的反馈”的实验平台。</p>
	  </div>
    </div>
  </div>
</body>
</html>
