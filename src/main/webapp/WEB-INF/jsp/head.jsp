<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- The above 2 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="stonejinyan">

<!-- Note there is no responsive meta tag here -->

<link rel="icon" href="image/favicon.ico">

<title>APS</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="css/custom.css" rel="stylesheet">
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="css/ie10-viewport-bug-workaround.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/non-responsive.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="js/html5shiv.min.js"></script>
      <script src="js/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<!-- /container -->

	<!--  Logo 部位        -->
	<!-- Fixed navbar -->
	<nav class="navbar navbar-default">
		<div>
			<div class="row logobar">

				<div class="col-xs-2 text-right" style="vertical-align: middle;">
					<img class="logo img-responsive" alt="logo" src="image/logo.png">
				</div>
				<div class="col-xs-7">
						<h3 class="display-5  title hmargin">Advanced Planning and Scheduling</h3>
						<h3 class="display-5  title hmargin">
							<small class="display-5  title">高级计划与排程</small>
						</h3>
				</div>
				<s:if test="%{#session.staff != null}">
					<div class="col-xs-2">
						<div class="row text-right">
							<h4 class="display-5  title text-right">${staff.name}</h4>
							<h5 class="display-5 title text-right">欢迎你！</h5>
						</div>
					</div>
					<div class="col-xs-1"
						style="display: table-cell; vertical-align: middle; text-align: center;">
						<img class="img-responsive" alt=""
							src="image/head.png" width="50px">
					</div>
				</s:if>
			</div>
		</div>
		<s:if test="%{#session.staff != null}">
			<div class="container">
				<div class="navbar-header">
					<!-- The mobile navbar-toggle button can be safely removed since you do not need it in a non-responsive implementation -->
				</div>
				<!-- Note that the .navbar-collapse and .collapse classes have been removed from the #navbar -->
				<div id="navbar">
					<ul class="nav navbar-nav">
						<li><a
							href="home">首页</a></li>	
						<li
							class="dropdown"><a
							href="#" class="dropdown-toggle" data-toggle="dropdown"
							role="button" aria-haspopup="true" aria-expanded="false">订单信息维护<span
								class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="#">订单创建</a></li>
								<li><a href="Batch">Batch维护</a></li>
								<li><a href="#">MO维护</a></li>
								<li role="separator" class="divider"></li>
								<li><a href="MissingParts">缺件查看</a></li>
								<li><a href="PO">PO查看</a></li>
								<!-- <li><a href="MissingPartsSummaryAction">交期确认</a></li> -->
							</ul></li>
						<li
							class="dropdown"><a
							href="#" class="dropdown-toggle" data-toggle="dropdown"
							role="button" aria-haspopup="true" aria-expanded="false">基础数据维护<span
								class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="#">拉动物料维护</a></li>
								<li><a href="#">包材物料维护</a></li>
							</ul></li>
							<li><a
							href="#">数据报表</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li><a
							href="">常见问题</a></li>
					</ul>

				</div>
				<!--/.nav-collapse -->
			</div>
		</s:if>
	</nav>
		<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="js/jquery-1.10.2.min.js"></script>
	<script>
		window.jQuery
				|| document.write('<script src="js/jquery.min.js"><\/script>')
	</script>
	<script src="js/bootstrap.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="js/ie10-viewport-bug-workaround.js"></script>
	<script type="text/javascript">
	</script>
</body>
</html>