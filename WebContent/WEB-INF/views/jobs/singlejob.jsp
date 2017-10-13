<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="北方的河">
	<link rel="shortcut icon" href="images/favicon.png" type="image/png">

	<title>自留地·知识库</title>

	<link href="css/style.default.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
  <script src="js/html5shiv.js"></script>
  <script src="js/respond.min.js"></script>
  <![endif]-->
</head>

<body>

	<!-- Preloader -->
	<div id="preloader">
		<div id="status">
			<i class="fa fa-spinner fa-spin"></i>
		</div>
	</div>

	<section>

		<div class="leftpanel">

			<div class="logopanel">
				<h1>
					<span>[</span> 知识库 <span>]</span>
				</h1>
			</div>
			<!-- logopanel -->

			<div class="leftpanelinner">

				<!-- This is only visible to small devices -->
				<div class="visible-xs hidden-sm hidden-md hidden-lg">
					<div class="media userlogged">
						<img alt="" src="images/photos/loggeduser.png"
							class="media-object">
						<div class="media-body">
							<h4>${fn:escapeXml(sessionScope.user.userName) }</h4>
						</div>
					</div>

					<h5 class="sidebartitle actitle">账 户</h5>
					<ul class="nav nav-pills nav-stacked nav-bracket mb30">
						<li><a href="./user.do?method=userCenter"><i class="fa fa-user"></i> <span>个人中心</span></a></li>
						<li><a href="./user.do?method=changePasswd"><i class="fa fa-cog"></i> <span>修改密码</span></a></li>
						<li><a href=""><i class="fa fa-question-circle"></i> <span>Help</span></a></li>
						<li><a href="./user.do?method=logout"><i class="fa fa-sign-out"></i><span>退出</span></a></li>
					</ul>
				</div>

				<h5 class="sidebartitle">导航</h5>
				<ul class="nav nav-pills nav-stacked nav-bracket">
					<li class="active"><a href="./drops.do?method=getDrops"> <i
							class="fa fa-home"></i> <span>主页</span>
					</a></li>
					<li><a href="./drops.do?method=getDrops&category=dev">
							<i class="fa fa-pencil"></i> <span>dev</span>
					</a></li>
					<li><a href="./drops.do?method=getDrops&category=database">
							<i class="fa fa-database"></i> <span>database</span>
					</a></li>
					<li><a href="./drops.do?method=getDrops&category=binary">
							<i class="fa fa-stack-overflow"></i> <span>binary</span>
					</a></li>
					<li><a href="./drops.do?method=getDrops&category=wireless">
							<i class="fa fa-signal"></i> <span>wireless</span>
					</a></li>
					<li><a href="./drops.do?method=getDrops&category=mobile">
							<i class="glyphicon glyphicon-phone"></i> <span>mobile</span>
					</a></li>
					<li><a href="./drops.do?method=getDrops&category=pentesting">
							<i class="fa fa-warning"></i> <span>pentesting</span>
					</a></li>
					<li><a href="./drops.do?method=getDrops&category=web"> <i
							class="fa fa-globe"></i> <span>web</span>
					</a></li>
					<li><a href="./drops.do?method=getDrops&category=运维安全"> <i
							class="fa fa-laptop"></i> <span>运维安全</span>
					</a></li>
					<li><a href="./drops.do?method=getDrops&category=news"> <i
							class="fa fa-weibo"></i> <span>news</span>
					</a></li>
					<li><a href="./drops.do?method=getDrops&category=tools"> <i
							class="fa fa-wrench"></i> <span>tools</span>
					</a></li>
					<li><a href="./drops.do?method=getDrops&category=tips"> <i
							class="fa fa-leaf"></i> <span>tips</span>
					</a></li>
					<li><a href="./drops.do?method=getDrops&category=papers">
							<i class="fa fa-file"></i> <span>papers</span>
					</a></li>
				</ul>
				
				<ul class="nav nav-pills nav-stacked nav-bracket">
					<li class="active">
					  <a href="#">
					    <i class="fa fa-home"></i>
					    <span>文章管理</span>
					  </a>
					</li>
					
					<li class="">
					  <a href="./jobs.do?method=addJob">
					    <i class="fa glyphicon-plus"></i>
					    <span>爬取文章</span>
					  </a>
					</li>
					<li class="">
					  <a href="./jobs.do?method=getJobs">
					    <i class="fa fa-list"></i>
					    <span>爬取文章管理</span>
					  </a>
					</li>
				<c:if test="${sessionScope.user.isAdmin == 1}">
					<li class="">
					  <a href="./jobs.do?method=manageJobs">
					    <i class="fa fa-cogs"></i>
					    <span>所有文章管理</span>
					  </a>
					</li>
				</c:if>
				</ul>


			</div>
			<!-- leftpanelinner -->
		</div>
		<!-- leftpanel -->

		<div class="mainpanel">

			<div class="headerbar">

				<a class="menutoggle"><i class="fa fa-bars"></i></a>

				<form class="searchform" action="./drops.do?method=searchDrops"
					method="post">
					<input type="text" class="form-control" name="keyword"
						placeholder="Search here..." /> <input type="hidden"
						name="pageNo" value="1" />
				</form>

				<div class="header-right">
					<ul class="headermenu">
						<li>
							<div class="btn-group">
								<button type="button" class="btn btn-default dropdown-toggle"
									data-toggle="dropdown">
									<img src="images/photos/loggeduser.png" alt="" />
									${fn:escapeXml(sessionScope.user.userName) } <span
										class="caret"></span>
								</button>
								<ul class="dropdown-menu dropdown-menu-usermenu pull-right">
									<li><a href="./user.do?method=userCenter"><i
											class="glyphicon glyphicon-user"></i> 个人中心</a></li>
									<li><a href="./user.do?method=changePasswd"><i
											class="glyphicon glyphicon-cog"></i> 修改密码</a></li>
									<li><a href="#"><i
											class="glyphicon glyphicon-question-sign"></i> Help</a></li>
									<li><a href="./user.do?method=logout"><i
											class="glyphicon glyphicon-log-out"></i> 退 出</a></li>
								</ul>
							</div>
						</li>
					</ul>
				</div>
				<!-- header-right -->

			</div>
			<!-- headerbar -->

			<div class="pageheader">
				<h2>
					<i class="fa glyphicon-plus"></i> 爬取文章 <span>添加爬取文章</span>
				</h2>
			</div>

			<div class="contentpanel">

				<div class="panel panel-default">

					<div class="panel-heading">
						<div class="panel-btns">
							<a class="panel-close" href="">×</a> <a class="minimize" href="">−</a>
						</div>
						<h4 class="panel-title">添加文章</h4>
						<p class="mt5 mb20">
							<span style="color: Red">${message }</span>
						</p>
					</div>

					<form class="form-horizontal form-bordered" action="./jobs.do?method=doManageJobs" method="post">

						<div class="panel-body panel-body-nopadding">

							<div class="form-group">
								<label class="col-sm-3 control-label">文章标题： </label>
								<div class="col-sm-6">
									<input name="title" type="text" class="form-control" value="${fn:escapeXml(job.title)}" required />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label">文章地址： </label>
								<div class="col-sm-6">
									<a href="${fn:escapeXml(job.originUrl)}" target="_blank"><input name="url" type="url" class="form-control" value="${fn:escapeXml(job.originUrl)}" required /></a>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label">建议类别： </label>
								<div class="col-sm-6">
									<select name="category">
										<c:if test="${job.category == 'dev' }"><option value="dev" selected="selected">dev</option></c:if>
										<option value="dev">dev</option>
										<c:if test="${job.category == 'database' }"><option value="database" selected="selected">database</option></c:if>
										<option value="database">database</option>
										<c:if test="${job.category == 'binary' }"><option value="binary" selected="selected">binary</option></c:if>
										<option value="binary">binary</option>
										<c:if test="${job.category == 'wireless' }"><option value="wireless" selected="selected">wireless</option></c:if>
										<option value="wireless">wireless</option>
										<c:if test="${job.category == 'mobile' }"><option value="mobile" selected="selected">mobile</option></c:if>
										<option value="mobile">mobile</option>
										<c:if test="${job.category == 'pentesting' }"><option value="pentesting" selected="selected">pentesting</option></c:if>
										<option value="pentesting">pentesting</option>
										<c:if test="${job.category == 'web' }"><option value="web" selected="selected">web</option></c:if>
										<option value="web">web</option>
										<c:if test="${job.category == '运维安全' }"><option value="运维安全" selected="selected">运维安全</option></c:if>
										<option value="运维安全">运维安全</option>
										<c:if test="${job.category == 'news' }"><option value="news" selected="selected">news</option></c:if>
										<option value="news">news</option>
										<c:if test="${job.category == 'tools' }"><option value="tools" selected="selected">tools</option></c:if>
										<option value="tools">tools</option>
										<c:if test="${job.category == 'tips' }"><option value="tips" selected="selected">tips</option></c:if>
										<option value="tips">tips</option>
										<c:if test="${job.category == 'papers' }"><option value="papers" selected="selected">papers</option></c:if>
										<option value="papers">papers</option>
									</select>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label">提交者： </label>
								<div class="col-sm-6">
									<input name="submitUserName" type="text" class="form-control" value="${fn:escapeXml(job.submitUserName)}" required />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label">作 者： </label>
								<div class="col-sm-6">
									<input name="author" type="text" class="form-control"  required />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label">文章发布时间： </label>
								<div class="col-sm-6">
									<input name=createTime type="datetime" class="form-control"  required />
								</div>
							</div>
							
							<input name="id" type="hidden" class="form-control" value=${job.id} } required />
						</div>

						<div class="panel-footer">
							<div class="row">
								<div class="col-sm-6 col-sm-offset-3">
									<button class="btn btn-primary">提 交</button>
									&nbsp;
									<button class="btn btn-default">取 消</button>
								</div>
							</div>
						</div>
					</form>

				</div>
				<!-- contentpanel -->

			</div>
		</div>
		<!-- mainpanel -->

		<div class="rightpanel">
			<!-- Nav tabs -->
			<ul class="nav nav-tabs nav-justified">
				<li class="active"><a href="#rp-alluser" data-toggle="tab"><i
						class="fa fa-users"></i></a></li>
				<li><a href="#rp-favorites" data-toggle="tab"><i
						class="fa fa-heart"></i></a></li>
				<li><a href="#rp-history" data-toggle="tab"><i
						class="fa fa-clock-o"></i></a></li>
				<li><a href="#rp-settings" data-toggle="tab"><i
						class="fa fa-gear"></i></a></li>
			</ul>

			<!-- Tab panes -->
			<div class="tab-content">
				<div class="tab-pane active" id="rp-alluser">
					<h5 class="sidebartitle">Online Users</h5>
					<ul class="chatuserlist">
						<li class="online">
							<div class="media">
								<a href="#" class="pull-left media-thumb"> <img alt=""
									src="images/photos/userprofile.png" class="media-object">
								</a>
								<div class="media-body">
									<strong>Eileen Sideways</strong> <small>Los Angeles, CA</small>
								</div>
							</div> <!-- media -->
						</li>
						<li class="online">
							<div class="media">
								<a href="#" class="pull-left media-thumb"> <img alt=""
									src="images/photos/user1.png" class="media-object">
								</a>
								<div class="media-body">
									<span class="pull-right badge badge-danger">2</span> <strong>Zaham
										Sindilmaca</strong> <small>San Francisco, CA</small>
								</div>
							</div> <!-- media -->
						</li>
						<li class="online">
							<div class="media">
								<a href="#" class="pull-left media-thumb"> <img alt=""
									src="images/photos/user2.png" class="media-object">
								</a>
								<div class="media-body">
									<strong>Nusja Nawancali</strong> <small>Bangkok,
										Thailand</small>
								</div>
							</div> <!-- media -->
						</li>
						<li class="online">
							<div class="media">
								<a href="#" class="pull-left media-thumb"> <img alt=""
									src="images/photos/user3.png" class="media-object">
								</a>
								<div class="media-body">
									<strong>Renov Leongal</strong> <small>Cebu City,
										Philippines</small>
								</div>
							</div> <!-- media -->
						</li>
						<li class="online">
							<div class="media">
								<a href="#" class="pull-left media-thumb"> <img alt=""
									src="images/photos/user4.png" class="media-object">
								</a>
								<div class="media-body">
									<strong>Weno Carasbong</strong> <small>Tokyo, Japan</small>
								</div>
							</div> <!-- media -->
						</li>
					</ul>

					<div class="mb30"></div>

					<h5 class="sidebartitle">Offline Users</h5>
					<ul class="chatuserlist">
						<li>
							<div class="media">
								<a href="#" class="pull-left media-thumb"> <img alt=""
									src="images/photos/user5.png" class="media-object">
								</a>
								<div class="media-body">
									<strong>Eileen Sideways</strong> <small>Los Angeles, CA</small>
								</div>
							</div> <!-- media -->
						</li>
						<li>
							<div class="media">
								<a href="#" class="pull-left media-thumb"> <img alt=""
									src="images/photos/user2.png" class="media-object">
								</a>
								<div class="media-body">
									<strong>Zaham Sindilmaca</strong> <small>San Francisco,
										CA</small>
								</div>
							</div> <!-- media -->
						</li>
						<li>
							<div class="media">
								<a href="#" class="pull-left media-thumb"> <img alt=""
									src="images/photos/user3.png" class="media-object">
								</a>
								<div class="media-body">
									<strong>Nusja Nawancali</strong> <small>Bangkok,
										Thailand</small>
								</div>
							</div> <!-- media -->
						</li>
						<li>
							<div class="media">
								<a href="#" class="pull-left media-thumb"> <img alt=""
									src="images/photos/user4.png" class="media-object">
								</a>
								<div class="media-body">
									<strong>Renov Leongal</strong> <small>Cebu City,
										Philippines</small>
								</div>
							</div> <!-- media -->
						</li>
						<li>
							<div class="media">
								<a href="#" class="pull-left media-thumb"> <img alt=""
									src="images/photos/user5.png" class="media-object">
								</a>
								<div class="media-body">
									<strong>Weno Carasbong</strong> <small>Tokyo, Japan</small>
								</div>
							</div> <!-- media -->
						</li>
						<li>
							<div class="media">
								<a href="#" class="pull-left media-thumb"> <img alt=""
									src="images/photos/user4.png" class="media-object">
								</a>
								<div class="media-body">
									<strong>Renov Leongal</strong> <small>Cebu City,
										Philippines</small>
								</div>
							</div> <!-- media -->
						</li>
						<li>
							<div class="media">
								<a href="#" class="pull-left media-thumb"> <img alt=""
									src="images/photos/user5.png" class="media-object">
								</a>
								<div class="media-body">
									<strong>Weno Carasbong</strong> <small>Tokyo, Japan</small>
								</div>
							</div> <!-- media -->
						</li>
					</ul>
				</div>
				<div class="tab-pane" id="rp-favorites">
					<h5 class="sidebartitle">Favorites</h5>
					<ul class="chatuserlist">
						<li class="online">
							<div class="media">
								<a href="#" class="pull-left media-thumb"> <img alt=""
									src="images/photos/user2.png" class="media-object">
								</a>
								<div class="media-body">
									<strong>Eileen Sideways</strong> <small>Los Angeles, CA</small>
								</div>
							</div> <!-- media -->
						</li>
						<li>
							<div class="media">
								<a href="#" class="pull-left media-thumb"> <img alt=""
									src="images/photos/user1.png" class="media-object">
								</a>
								<div class="media-body">
									<strong>Zaham Sindilmaca</strong> <small>San Francisco,
										CA</small>
								</div>
							</div> <!-- media -->
						</li>
						<li>
							<div class="media">
								<a href="#" class="pull-left media-thumb"> <img alt=""
									src="images/photos/user3.png" class="media-object">
								</a>
								<div class="media-body">
									<strong>Nusja Nawancali</strong> <small>Bangkok,
										Thailand</small>
								</div>
							</div> <!-- media -->
						</li>
						<li class="online">
							<div class="media">
								<a href="#" class="pull-left media-thumb"> <img alt=""
									src="images/photos/user4.png" class="media-object">
								</a>
								<div class="media-body">
									<strong>Renov Leongal</strong> <small>Cebu City,
										Philippines</small>
								</div>
							</div> <!-- media -->
						</li>
						<li class="online">
							<div class="media">
								<a href="#" class="pull-left media-thumb"> <img alt=""
									src="images/photos/user5.png" class="media-object">
								</a>
								<div class="media-body">
									<strong>Weno Carasbong</strong> <small>Tokyo, Japan</small>
								</div>
							</div> <!-- media -->
						</li>
					</ul>
				</div>
				<div class="tab-pane" id="rp-history">
					<h5 class="sidebartitle">History</h5>
					<ul class="chatuserlist">
						<li class="online">
							<div class="media">
								<a href="#" class="pull-left media-thumb"> <img alt=""
									src="images/photos/user4.png" class="media-object">
								</a>
								<div class="media-body">
									<strong>Eileen Sideways</strong> <small>Hi hello,
										ctc?... would you mind if I go to your...</small>
								</div>
							</div> <!-- media -->
						</li>
						<li>
							<div class="media">
								<a href="#" class="pull-left media-thumb"> <img alt=""
									src="images/photos/user2.png" class="media-object">
								</a>
								<div class="media-body">
									<strong>Zaham Sindilmaca</strong> <small>This is to
										inform you that your product that we...</small>
								</div>
							</div> <!-- media -->
						</li>
						<li>
							<div class="media">
								<a href="#" class="pull-left media-thumb"> <img alt=""
									src="images/photos/user3.png" class="media-object">
								</a>
								<div class="media-body">
									<strong>Nusja Nawancali</strong> <small>Are you willing
										to have a long term relat...</small>
								</div>
							</div> <!-- media -->
						</li>
					</ul>
				</div>
				<div class="tab-pane pane-settings" id="rp-settings">

					<h5 class="sidebartitle mb20">Settings</h5>
					<div class="form-group">
						<label class="col-xs-8 control-label">Show Offline Users</label>
						<div class="col-xs-4 control-label">
							<div class="toggle toggle-success"></div>
						</div>
					</div>

					<div class="form-group">
						<label class="col-xs-8 control-label">Enable History</label>
						<div class="col-xs-4 control-label">
							<div class="toggle toggle-success"></div>
						</div>
					</div>

					<div class="form-group">
						<label class="col-xs-8 control-label">Show Full Name</label>
						<div class="col-xs-4 control-label">
							<div class="toggle-chat1 toggle-success"></div>
						</div>
					</div>

					<div class="form-group">
						<label class="col-xs-8 control-label">Show Location</label>
						<div class="col-xs-4 control-label">
							<div class="toggle toggle-success"></div>
						</div>
					</div>

				</div>
				<!-- tab-pane -->

			</div>
			<!-- tab-content -->
		</div>
		<!-- rightpanel -->
	</section>


	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/jquery-migrate-1.2.1.min.js"></script>
	<script src="js/jquery-ui-1.10.3.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/modernizr.min.js"></script>
	<script src="js/jquery.sparkline.min.js"></script>
	<script src="js/toggles.min.js"></script>
	<script src="js/retina.min.js"></script>
	<script src="js/jquery.cookies.js"></script>

	<script src="js/flot/jquery.flot.min.js"></script>
	<script src="js/flot/jquery.flot.resize.min.js"></script>
	<script src="js/flot/jquery.flot.spline.min.js"></script>
	<script src="js/morris.min.js"></script>
	<script src="js/raphael-2.1.0.min.js"></script>

	<script src="js/custom.js"></script>
	<script src="js/dashboard.js"></script>


</body>
</html>
