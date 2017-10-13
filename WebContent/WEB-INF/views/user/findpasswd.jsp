<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<c:set value="<%=path%>" var="path" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="images/favicon.png" type="image/png">

<title>自留地·知识库</title>

<link href="css/style.default.css" rel="stylesheet">
<script type="text/javascript">
	//点击切换验证码
	function changeVerifyCode() {
		$("#yzmImg").attr("src",
				"Kaptcha.jpg?" + Math.floor(Math.random() * 1000000));
	}
</script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
  <script src="js/html5shiv.js"></script>
  <script src="js/respond.min.js"></script>
  <![endif]-->
</head>

<body class="signin">

	<section>

		<div class="signinpanel">

			<div class="row">

				<div class="col-md-7">

					<div class="signin-info">
						<div class="logopanel">
							<h1>
								<span>[</span> 自留地·知识库 <span>]</span>
							</h1>
						</div>
						<!-- logopanel -->

						<div class="mb20"></div>

						<h5>
							<strong>欢迎来到“我的”知识库</strong>
						</h5>
						<ul>
							<li><i class="fa fa-arrow-circle-o-right mr5"></i> 乌云知识库</li>
							<li><i class="fa fa-arrow-circle-o-right mr5"></i>
								Freebuf精华文章</li>
							<li><i class="fa fa-arrow-circle-o-right mr5"></i> CSDN</li>
							<li><i class="fa fa-arrow-circle-o-right mr5"></i> 安全脉搏</li>
							<li><i class="fa fa-arrow-circle-o-right mr5"></i> 更多...</li>
						</ul>
						<div class="mb20"></div>
						<strong>还不是会员? <a href="${path }/user.do?method=register">点我注册</a></strong>
					</div>
					<!-- signin0-info -->

				</div>
				<!-- col-sm-7 -->

				<div class="col-md-5">

					<form method="post" action="${path }/user.do?method=doFindPasswd">
						<h4 class="nomargin">找回密码</h4>
						<p class="mt5 mb20">
							<span style="color: ">请输入您注册时候的邮箱，新密码将会发送到您的邮箱</span>
						</p>
						
						<p class="mt5 mb20">
							<span style="color: Red">${message }</span>
						</p>

						<input type="email" class="form-control uname" placeholder="邮 箱" name="email" /> 
						<input type='text' name="verifyCode" id="verifyCode" value='' class="form-control checkcode" placeholder="验证码"> 
						<img src="Kaptcha.jpg" onclick="changeVerifyCode()" id="yzmImg" style="cursor: pointer;"> &nbsp;&nbsp;&nbsp;&nbsp; 
						<a href="javascript:void(0)" onclick="changeVerifyCode()"><small>换一张</small></a>
						<p class="mt5 mb20">
							<a href="${path }/user.do"><small>登 录</small></a>
						</p>
						<button class="btn btn-success btn-block" >找&nbsp;&nbsp;&nbsp;&nbsp;回</button>

					</form>
				</div>
				<!-- col-sm-5 -->

			</div>
			<!-- row -->

			<div class="signup-footer">
				<div class="pull-left">&copy; 2016. 版权所有. 北方的河</div>
				<div class="pull-right">
					Created By: <a href="http://drops.dns.bike/" target="_blank"
						title="qq: 2280274936">北方的河</a>
				</div>
			</div>

		</div>
		<!-- signin -->

	</section>


	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/jquery-migrate-1.2.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/modernizr.min.js"></script>
	<script src="js/jquery.sparkline.min.js"></script>
	<script src="js/jquery.cookies.js"></script>

	<script src="js/toggles.min.js"></script>
	<script src="js/retina.min.js"></script>

	<script src="js/custom.js"></script>
	<script>
		jQuery(document).ready(
				function() {

					// Please do not use the code below
					// This is for demo purposes only
					var c = jQuery.cookie('change-skin');
					if (c && c == 'greyjoy') {
						jQuery('.btn-success').addClass('btn-orange')
								.removeClass('btn-success');
					} else if (c && c == 'dodgerblue') {
						jQuery('.btn-success').addClass('btn-primary')
								.removeClass('btn-success');
					} else if (c && c == 'katniss') {
						jQuery('.btn-success').addClass('btn-primary')
								.removeClass('btn-success');
					}
				});
	</script>

</body>
</html>
