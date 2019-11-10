<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<base href="http://localhost:8020/admin/">
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>大麦网后台管理系统</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">
<link rel="apple-touch-icon-precomposed" href="images/icon/damaiz_icon.png">
<link rel="shortcut icon" href="images/icon/damaiz_icon.png">
<script src="js/jquery-2.1.4.min.js"></script>
<!--[if gte IE 9]>
  <script src="js/jquery-1.11.1.min.js" type="text/javascript"></script>
  <script src="js/html5shiv.min.js" type="text/javascript"></script>
  <script src="js/respond.min.js" type="text/javascript"></script>
  <script src="js/selectivizr-min.js" type="text/javascript"></script>
<![endif]-->
<!--[if lt IE 9]>
  <script>window.location.href='upgrade-browser.html';</script>
<![endif]-->


<style type="text/css">
		body{
			 font-family:"Hanzipen SC";
		}

</style>
</head>

<body class="user-select">
<section class="container-fluid">
  <header>
    <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false"> <span class="sr-only">切换导航</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
          <a class="navbar-brand" href="">DaMaiCMS</a> </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="">消息 <span class="badge">1</span></a></li>
           
            <li class="dropdown"> 
            
            <c:if test="${not empty manageuser }">
            
            
           		 <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
           			 ${manageuser.name }
            		 <span class="caret">
            		 </span>
            	</a>
            </c:if>
              <ul class="dropdown-menu dropdown-menu-left" style="z-index:1000000000">
                <li><a title="查看或修改个人信息" data-toggle="modal" data-target="#seeUserInfo" style="z-index:1000000000">个人信息</a></li>
                <li><a title="查看您的登录记录" data-toggle="modal" data-target="#seeUserLoginlog" styyle="z-index:1000000000">登录记录</a></li>
              </ul> 
            </li>
            <li><a href="/Semester_Demo/ManageUserLogout" onClick="if(!confirm('是否确认退出？'))return false;">退出登录</a></li>
            <li><a data-toggle="modal" data-target="#WeChat">帮助</a></li>
          </ul>
          <form action="" method="post" class="navbar-form navbar-right" role="search">
            <div class="input-group">
              <input type="text" class="form-control" autocomplete="off" placeholder="键入关键字搜索" maxlength="15">
              <span class="input-group-btn">
              <button class="btn btn-default" type="submit">搜索</button>
              </span> </div>
          </form>
        </div>
      </div>
    </nav>
  </header>
  <div class="row">
    <aside class="col-sm-3 col-md-2 col-lg-2 sidebar">
      <ul class="nav nav-sidebar">
        <li class="active"><a href="index.jsp">报告</a></li>
      </ul>
      <ul class="nav nav-sidebar">
      <c:if test="${manageuser.book==1}">
      	 <li><a href="/Semester_Demo/AdminGetInitBookInfoServlet?curPage=1">票务</a></li>
      </c:if>
       <c:if test="${manageuser.order==1}">
      	  <li><a href="/Semester_Demo/FindAllordersServlet?curPage=1">订单</a></li>
      </c:if>
      <c:if test="${manageuser.mark==1}">
      	<li><a href="/Semester_Demo/MarkListServlet?curPage=1">评论</a></li>
      </c:if>
      <c:if test="${manageuser.vote==1}">
      	  <li><a href="/Semester_Demo/InitVoteListServlet?curPage=1">投票</a></li>
      </c:if>
       
      </ul>
      <ul class="nav nav-sidebar">
       <c:if test="${manageuser.mark==1}">
      	 <li><a href="/Semester_Demo/getCategoryType.do?curPage=1">栏目</a></li>
      </c:if>
     
        <li><a class="dropdown-toggle" id="otherMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">其他</a>
          <ul class="dropdown-menu" aria-labelledby="otherMenu">
            <li><a href="flink.html">友情链接</a></li>
            <li><a data-toggle="modal" data-target="#areDeveloping">访问记录</a></li>
          </ul>
        </li>
      </ul>
      <ul class="nav nav-sidebar">
        <li><a class="dropdown-toggle" id="userMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">用户</a>
          <ul class="dropdown-menu" aria-labelledby="userMenu">
            <li><a href="/Semester_Demo/ManageUserListServlet?curPage=1">管理管理员</a></li>
            <li><a href="/Semester_Demo/UserListServlet?curPage=1">管理用户</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="loginlog.html">管理登录日志</a></li>
          </ul>
        </li>
        <li><a class="dropdown-toggle" id="settingMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">设置</a>
          <ul class="dropdown-menu" aria-labelledby="settingMenu">
            <li><a href="setting.html">基本设置</a></li>
            <li><a href="readset.html">阅读设置</a></li>
            <li role="separator" class="divider"></li>
            <li><a data-toggle="modal" data-target="#areDeveloping">安全配置</a></li>
            <li role="separator" class="divider"></li>
            <li class="disabled"><a>扩展菜单</a></li>
          </ul>
        </li>
      </ul>
    </aside>
    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-lg-10 col-md-offset-2 main" id="main">
      <h1 class="page-header">信息总览</h1>
      <div class="row placeholders">
        <div class="col-xs-6 col-sm-3 placeholder">
          <h4>票务</h4>
          <span class="text-muted badge" id="book">${booktotalCount } 条</span> </div>
        <div class="col-xs-6 col-sm-3 placeholder">
          <h4>投票</h4>
          <span class="text-muted badge" id="vote">条</span> </div>
        <div class="col-xs-6 col-sm-3 placeholder">
          <h4>评论</h4>
          <span class="text-muted badge"  id="mark">${sessionScope.marktotalCount }条</span> </div>
        <div class="col-xs-6 col-sm-3 placeholder">
          <h4>订单</h4>
          <span class="text-muted badge" id="order">${sessionScope.ordertotalCount } 条</span> </div>
      </div>
      
      
      <script type="text/javascript">
      
      		$(function(){
      			
      			
      			$.post(
      				"/Semester_Demo/IndexJspInitServlet",
      				function(data)
      				{
      					$("#book").html(data.bookcount);
      					
      					$("#vote").html(data.votecount);
      					$("#mark").html(data.markcount);
      					$("#order").html(data.ordercount);
      					
      					$("#time").html(new Date());
      				},
      				"json"
      				
      			)
      			
      			
      		})
      </script>
      
      
      <h1 class="page-header">状态</h1>
      <div class="table-responsive">
        <table class="table table-striped table-hover">
          <tbody>
            <tr>
              <td>登录者: <span>admin</span>，这是您第 <span>13</span> 次登录</td>
            </tr>
            <tr>
              <td>上次登录时间: 2016-01-08 15:50:28 , 上次登录IP: ::1:55570</td>
            </tr>
          </tbody>
        </table>
      </div>
      <h1 class="page-header">系统信息</h1>
      <div class="table-responsive">
        <table class="table table-striped table-hover">
          <thead>
            <tr> </tr>
          </thead>
          <tbody>
            <tr>
              <td>管理员个数:</td>
              <td>2 人</td>
              <td>服务器软件:</td>
              <td>Apache/2.4.10 (Win32) OpenSSL/1.0.1i mod_fcgid/2.3.9</td>
            </tr>
            <tr>
              <td>浏览器:</td>
              <td>Chrome47</td>
              <td>PHP版本:</td>
              <td>5.6.1</td>
            </tr>
            <tr>
              <td>操作系统:</td>
              <td>Windows 10</td>
              <td>PHP运行方式:</td>
              <td>CGI-FCGI</td>
            </tr>
            <tr>
              <td>登录者IP:</td>
              <td>::1:55570</td>
              <td>MYSQL版本:</td>
              <td>5.5.40</td>
            </tr>
            <tr>
              <td>程序版本:</td>
              <td class="version">iBookCMS 1.0 <font size="-6" color="#BBB">(20160108160215)</font></td>
              <td>上传文件:</td>
              <td>可以 <font size="-6" color="#BBB">(最大文件：2M ，表单：8M )</font></td>
            </tr>
            <tr>
              <td>程序编码:</td>
              <td>UTF-8</td>
              <td>当前时间:</td>
              <td id="time">2016-01-08 15:50:30</td>
            </tr>
          </tbody>
          <tfoot>
            <tr></tr>
          </tfoot>
        </table>
      </div>
      <footer>
        <h1 class="page-header">程序信息</h1>
        <div class="table-responsive">
        <table class="table table-striped table-hover">
          <tbody>
            <tr>
              <td><span style="display:inline-block; width:8em">版权所有</span> POWERED BY WY ALL RIGHTS RESERVED</td>
            </tr>
            <tr>
              <td><span style="display:inline-block;width:8em">页面加载时间</span> PROCESSED IN 1.0835s  SECONDS 更多模板：<a href="http://www.mycodes.net/" target="_blank">源码之家</a></td>
            </tr>
          </tbody>
        </table>
        </div>
      </footer>
    </div>
  </div>
</section>
<!--个人信息模态框-->
<div class="modal fade" id="seeUserInfo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" >
  <div class="modal-dialog" role="document">
    <form action="/Semester_Demo/UpdateManagerUserInfo" method="post">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title" >个人信息</h4>
        </div>
        <div class="modal-body">
          <table class="table" style="margin-bottom:0px;">
            <thead>
              <tr> </tr>
            </thead>
            <tbody>
              <tr>
                <td wdith="20%">姓名:</td>
                <td width="80%"><input type="text" value="${manageuser.name }" class="form-control" name="name" maxlength="10" autocomplete="off" /></td>
              </tr>
            
              <tr>
                <td wdith="20%">新密码:</td>
                <td width="80%"><input type="password" class="form-control" name="password" maxlength="18" autocomplete="off" /></td>
              </tr>
              <tr>
                <td wdith="20%">确认密码:</td>
                <td width="80%"><input type="password" class="form-control" name="new_password" maxlength="18" autocomplete="off" /></td>
              </tr>
            </tbody>
            <tfoot>
              <tr>
              		<td>
              			<input type="hidden" name="manageuserid" value="${manageuser.ID }">
              		</td>
              	</tr>
            </tfoot>
          </table>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          <button type="submit" class="btn btn-primary">提交</button>
        </div>
      </div>
    </form>
  </div>
</div>
<!--个人登录记录模态框-->
<div class="modal fade" id="seeUserLoginlog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" >登录记录</h4>
      </div>
      <div class="modal-body">
          <table class="table" style="margin-bottom:0px;">
            <thead>
              <tr>
                <th>登录IP</th>
                <th>登录时间</th>
                <th>状态</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>::1:55570</td>
                <td>2016-01-08 15:50:28</td>
                <td>成功</td>
              </tr>
              <tr>
                <td>::1:64377</td>
                <td>2016-01-08 10:27:44</td>
                <td>成功</td>
              </tr>
              <tr>
                <td>::1:64027</td>
                <td>2016-01-08 10:19:25</td>
                <td>成功</td>
              </tr>
              <tr>
                <td>::1:57081</td>
                <td>2016-01-06 10:35:12</td>
                <td>成功</td>
              </tr>
            </tbody>
          </table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">朕已阅</button>
      </div>
    </div>
  </div>
</div>
<!--微信二维码模态框-->
<div class="modal fade user-select" id="WeChat" tabindex="-1" role="dialog" aria-labelledby="WeChatModalLabel">
  <div class="modal-dialog" role="document" style="margin-top:120px;max-width:280px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="WeChatModalLabel" style="cursor:default;">微信扫一扫</h4>
      </div>
      <div class="modal-body" style="text-align:center"> <img src="images/weixin.jpg" alt="" style="cursor:pointer"/> </div>
    </div>
  </div>
</div>
<!--提示模态框-->
<div class="modal fade user-select" id="areDeveloping" tabindex="-1" role="dialog" aria-labelledby="areDevelopingModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="areDevelopingModalLabel" style="cursor:default;">该功能正在日以继夜的开发中…</h4>
      </div>
      <div class="modal-body"> <img src="images/baoman/baoman_01.gif" alt="深思熟虑" />
        <p style="padding:15px 15px 15px 100px; position:absolute; top:15px; cursor:default;">很抱歉，程序猿正在日以继夜的开发此功能，本程序将会在以后的版本中持续完善！</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">朕已阅</button>
      </div>
    </div>
  </div>
</div>
<!--右键菜单列表-->
<div id="rightClickMenu">
  <ul class="list-group rightClickMenuList">
    <li class="list-group-item disabled">欢迎访问异清轩博客</li>
    <li class="list-group-item"><span>IP：</span>172.16.10.129</li>
    <li class="list-group-item"><span>地址：</span>河南省郑州市</li>
    <li class="list-group-item"><span>系统：</span>Windows10 </li>
    <li class="list-group-item"><span>浏览器：</span>Chrome47</li>
  </ul>
</div>
<script src="js/bootstrap.min.js"></script> 
<script src="js/admin-scripts.js"></script>
</body>
</html>
