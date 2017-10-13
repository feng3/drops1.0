<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">
  <link rel="shortcut icon" href="images/favicon.png" type="image/png">

  <title>自留地·知识库</title>

  <link href="css/style.default.css" rel="stylesheet">
  
  	<style>
		#yzmImg {
			border-radius: 2px;
			cursor: pointer;
			position: absolute;
			z-index: 3;
			left: 0;
			margin-top: 0px;
			margin-left:20px;
		}
	</style>
  
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
  
    <div class="signuppanel">
        
        <div class="row">
            
            <div class="col-md-6">
                
                <div class="signup-info">
                    <div class="logopanel">
                        <h1><span>[</span> 自留地·知识库 <span>]</span></h1>
                    </div><!-- logopanel -->
                
                    <div class="mb20"></div>
                
                    <h5><strong>打造自己的知识管理系统</strong></h5>
                    <p>[<span> 自留地·知识库 </span>] 致力于打造一个用户量身的知识管理系统</p>
                    <div class="mb20"></div>
                    
                    <div class="feat-list">
                        <i class="fa fa-wrench"></i>
                        <h4 class="text-success">干货多</h4>
                        <p>网罗了乌云知识库等高质量文章</p>
                        <p></p>
                    </div>
                    
                    <div class="feat-list">
                        <i class="fa fa-compress"></i>
                        <h4 class="text-success">众人拾柴</h4>
                        <p>在这里你可以推荐收录的文章也可以投稿</p>
                    </div>
                    
                    <div class="feat-list mb20">
                        <i class="fa fa-search-plus"></i>
                        <h4 class="text-success">知识管理</h4>
                        <p>你将拥有自身的知识管理</p>
                    </div>
                    
                    <h4 class="mb20">更多...</h4>
                
                </div><!-- signup-info -->
            
            </div><!-- col-sm-6 -->
            
            <div class="col-md-6">
                
                <form method="post" action="user.do?method=doRegister">
                    
                    <h3 class="nomargin">注 册</h3>
                    <p class="mt5 mb20">已经有账号? <a href="user.do"><strong>登 录</strong></a></p>
                	<p class="mt5 mb20"><span style="color:Red">${message }</span></p>
                    
                    <div class="mb10">
                        <label class="control-label">用户名</label>
                        <input type="text" class="form-control" name="username" required/>
                    </div>
                    
                    <div class="mb10">
                        <label class="control-label">密 码</label>
                        <input type="password" class="form-control" name="password" required/>
                    </div>
                    
                    <div class="mb10">
                        <label class="control-label">重复密码</label>
                        <input type="password" class="form-control" name="password1" required/>
                    </div>
                    
                    
                    <div class="mb10">
                        <label class="control-label">邮 箱</label>
                        <input type="email" class="form-control" name="email" required/>
                    </div>
                    
                    <div class="mb10">
                        <label class="control-label">手 机</label>
                        <input type="text" class="form-control" name="phone" required/>
                    </div>
                    
                    <div class="row row-pad-5">
			                <div class="col-lg-6">
								<input type='text' name="verifyCode" id="verifyCode" value='' class="form-control" placeholder="验证码" required/> 
			                </div>
			                <div class="col-lg-4">
								<img src="Kaptcha.jpg" onclick="changeVerifyCode()" id="yzmImg" style="cursor: pointer;" class="media-object" style="margin-left:20px;">  
			                </div>
<!-- 			                <div class="col-lg-4">
								<a href="javascript:void(0)" onclick="changeVerifyCode()"><small>换一张</small></a>
			                </div> -->
			         </div>
                    
                    <button class="btn btn-success btn-block">注 册</button>     
                </form>
            </div><!-- col-sm-6 -->
            
        </div><!-- row -->
        
        <div class="signup-footer">
            <div class="pull-left">
                &copy; 2016. 版权所有. 北方的河
            </div>
            <div class="pull-right">
                Created By: <a href="http://drops.dns.bike/" target="_blank" title="qq: 2280274936">北方的河</a>
            </div>
        </div>
        
    </div><!-- signuppanel -->
  
</section>


<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/jquery-migrate-1.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/modernizr.min.js"></script>
<script src="js/jquery.sparkline.min.js"></script>
<script src="js/jquery.cookies.js"></script>

<script src="js/toggles.min.js"></script>
<script src="js/retina.min.js"></script>

<script src="js/select2.min.js"></script>

<script src="js/custom.js"></script>
<script>
    jQuery(document).ready(function(){
        
        jQuery(".select2").select2({
            width: '100%',
            minimumResultsForSearch: -1
        });
        
        jQuery(".select2-2").select2({
            width: '100%'
        });
        
        
        // Please do not use the code below
        // This is for demo purposes only
        var c = jQuery.cookie('change-skin');
        if (c && c == 'greyjoy') {
            jQuery('.btn-success').addClass('btn-orange').removeClass('btn-success');
        } else if(c && c == 'dodgerblue') {
            jQuery('.btn-success').addClass('btn-primary').removeClass('btn-success');
        } else if (c && c == 'katniss') {
            jQuery('.btn-success').addClass('btn-primary').removeClass('btn-success');
        }
        
    });
</script>

</body>
</html>
