<#-- @ftlvariable name="loginInfo" type="com.jeiglobal.domain.auth.LoginInfo" -->
<#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] />
<!DOCTYPE html>
<!--[if IE 7]><html lang="ko" class="ie7"><![endif]-->
<!--[if IE 8]><html lang="ko" class="ie8"><![endif]-->
<html lang="ko">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title>글로벌 로그인</title>
	<link rel="stylesheet" type="text/css" href="/public/css/common.css" />
	<script src="/public/js/common/jquery-1.11.3.min.js" type="text/javascript"></script>
	<style type="text/css">
		/* login.css */
		html, body{height:100%;width:100%;}
		body{position:relative;z-index:1;min-width:960px;min-height:500px}
		.login_wrap{width:100%;height:100%;background:url(/public/img/bg_login.jpg) no-repeat;background-size:cover;}
		.login{width:960px;height:460px;margin:0 auto;position:absolute;z-index:10;left:50%;margin-left:-480px;top:50%;margin-top:-230px}
		.login h2{padding-bottom:49px;color:#fff;font-weight:normal;font-size:30px;text-transform:uppercase;text-align:center;line-height:36px}
		.login_form{width:765px;margin:0 auto;}
		.login_form .clearfix{height:45px;padding:6px 0 6px 75px;background:url(/public/img/logo_login.png) 0 50% no-repeat}
		.login_form .clearfix:after{content:'';display:block;clear:both;zoom:1}
		.login_form .input_login{width:230px;font-size:15px;color:#333333;height:22px;line-height:22px;padding:12px 40px 12px 25px;border:0;background:transparent url(/public/img/bg_loginbox.png) no-repeat;vertical-align:top}
		.input_wrap{display:block;float:left;padding-right:5px;position:relative;z-index:30}
		.input_wrap span.icon_chk{position:absolute;z-index:31;right:15px;top:13px;width:19px;height:19px;background:url(/public/img/icon_chk_off.png) no-repeat;}
		.input_wrap span.icon_chk.on{background:url(/public/img/icon_chk_on.png) no-repeat;}
		.btn_login{display:block;float:left;}
		.btn_login img{vertical-align:middle}
		.login_bg{display:none;} /* ie8이하 대응 이미지처리 */
		.login_footer{padding-top:263px}
		.login_footer p{text-align:center;line-height:18px;font-size:13px;color:#fff;opacity:0.3;-ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=30)"; filter:alpha(opacity=30);zoom:1}
	</style>
</head>
<body>
<#if error?default("") == "true" >
	<script type="text/javascript">
	$(function(){
		alert("계정과 암호가 일치하지 않습니다.");
		$("#loginFrm").attr("action","/login");
		$("#loginFrm").submit();
	});
	</script>
</#if>
	<div class="login_wrap">
		<h1 class="hidden">global login</h1>
			<div class="login">
			<h2>A better Life <br /> through better Education</h2>
			<form action="/loginCheck" name="loginFrm" id="loginFrm" method="post">
			<input type="hidden" name="returl" value="${returl?default('')}" />
			<@security.authorize access="! isAuthenticated()">
				<div class="login_form">
					<div class="clearfix">
						<div class="input_wrap">
							<label for="id" class="hidden">ID</label><input type="text" name="memberId" id="memberId" class="input_login input_login_id" />
							<span class="icon_chk"></span><!-- 아이디 input 박스 값 확인시 class="on" 추가 ( 체크 아이콘 색상 바뀜 ) -->
						</div>
						<div class="input_wrap">
							<label for="pw" class="hidden">PW</label><input type="password" name="memberPassword" id="memberPassword" class="input_login" />
							<span class="icon_chk"></span><!-- 패스워드 input 박스 값 확인시 class="on" 추가 ( 체크 아이콘 색상 바뀜 ) -->
						</div>
						<input type="image" src="/public/img/btn_login.png" alt="로그인" class="btn_login" />
					</div>
				</div>
			</@security.authorize>
			</form>
			<div class="login_footer">
				<p>GLOBAL JEI Corporation</p>
			</div>
		</div>
		<div class="login_bg">
			<img src="/public/img/bg_login.jpg" alt="" />
		</div>
	</div>
	<script type="text/javascript">
		
		$(document).ready(function(){
			setLoginBg(); //익스 8이하버전 백그라운드이미지처리
			
			$('.btn_login').click(function(){
				$('#loginFrm').submit();
			});
			
			$('.input_login').focus(function(){
				$('.icon_chk').removeClass('on');
				$(this).next().addClass('on');
			});
			
			$('.input_login_id').focus(); //페이지 로딩시 id값 입력란 focus();
		});
		$(window).resize(function(){
			setLoginBg(); //익스 8이하버전 백그라운드이미지처리
		});

		function setLoginBg(){
			//익스 8이하버전인 경우 백그라운드 이미지 처리
			if(chkBrower('msie 7') > -1 || chkBrower('msie 8') > -1){
				$('.login_bg').show(80, function(){
					$(this).children('img').css({
						display:'block',
						height:$(window).height(),
						width:'100%'
					});
				})
			}
		}
		//브라우저 체크
		function chkBrower(brower_version){
			var chk =navigator.userAgent.toLocaleLowerCase();
			return chk.indexOf(brower_version);	
		}
	</script>
</body>
</html>