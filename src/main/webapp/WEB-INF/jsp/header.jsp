<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--输出,条件,迭代标签库-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fmt"%>
<!--数据格式化标签库-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="sql"%>
<!--数据库相关标签库-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fn"%>
<!--常用函数标签库-->
<%@ page isELIgnored="false"%>
<!--支持EL表达式，不设的话，EL表达式不会解析-->
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
		<meta name="description" content="">
		<meta name="author" content="Frank Song">
		<meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
		<link rel="icon" href="/image/favicon.ico">

		<title>APS</title>
		<!-- Bootstrap core CSS -->
		<link href="/css/bootstrap.css" rel="stylesheet">
		<link href="/css/bootstrap-table.css" rel="stylesheet">
		<link href="/css/common.css" rel="stylesheet">
		<link href="/css/footer.css" rel="stylesheet">
		<link href="/css/bootstrap-datetimepicker.css" rel="stylesheet">

		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
	</head>

	<body>
		<!-- -->
		<script src="/js/jquery.min.js"></script>
		<script src="/js/jquery.timers.js"></script>
		<script src="/js/bootstrap.min.js"></script>
		<script src="/js/bootstrap-table.js"></script>
		<script src="/js/bootstrap-table-zh-CN.js"></script>
		<script src="/js/common.js"></script>
		<script src="/js/echarts.js"></script>
		<script src="/js/echarts-themes.js"></script>
		<script src="/js/moment-with-locales.js"></script>
		<script src="/js/bootstrap-datetimepicker.min.js"></script>
		<!-- Fixed navbar -->
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<shiro:authenticated>
						<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false"
						 aria-controls="navbar">
							<span class="sr-only">Toggle navigation</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
					</shiro:authenticated>
					<div class="navbar-header">
						<a class="navbar-brand navbar-brand-logo" href="/index">
							<img src="/image/lifeIsOn.png" />
							<a class="navbar-brand" href="/index">Advanced Planning And Scheduling</a>
						</a>
					</div>
				</div>
				<shiro:authenticated>
					<div id="navbar" class="navbar-collapse collapse">
						<ul class="nav navbar-nav">
							<li id="nav_home"><a href="/index">订单排产</a></li>
							<li id="nav_home"><a href="/index">订单预排产</a></li>
							<!-- <li id="nav_production" class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
								 aria-haspopup="true" aria-expanded="false">制造管理<span class="caret"></span></a>
								<ul id="production" class="dropdown-menu">
								</ul>
							</li> -->
							<li id="nav_project" class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">订单管理<span
									 class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="/project/page">项目信息</a></li>
									<li><a href="/batch/page">Batch信息</a></li>
									<li><a href="/mo/page">MO信息</a></li>
									<li role="separator" class="divider"></li>
								</ul>
							</li>
							<li id="nav_dashboard" class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">数据报表<span
									 class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="/dashboard/production">Dashboard1</a></li>
									<li><a href="#">Dashboard2</a></li>
									<li><a href="#">Dashboard3</a></li>
								</ul>
							</li>
							<!-- 	<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">个人中心<span
									 class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="#">密码修改</a></li>
								</ul>
							</li> -->
						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li class="active nav_welcome white_text center-block">
								<shiro:authenticated>
									&#12288;
									<shiro:principal /> Welcome！</shiro:authenticated>&nbsp;
							</li>
						</ul>
					</div>
					<!--/.nav-collapse -->
				</shiro:authenticated>
			</div>
		</nav>
		<br><br>
		<shiro:authenticated>
			<script type="text/javascript">

			</script>
		</shiro:authenticated>
