<#-- @ftlvariable name="loginInfo" type="com.jeiglobal.domain.auth.LoginInfo" -->
<#-- @ftlvariable name="hongkongUrl" type="java.lang.String" -->
<#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title?default('JEI-GLOBAL')}</title>
<link rel="stylesheet" href="${cssPath }/common.css">
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
</head>
<body id="login">
<#if error?default("") == "true" >
	<script type="text/javascript">
	$(function(){
		alert("계정과 암호가 일치하지 않습니다.");
		$("#loginFrm").attr("action","/login");
		$("#loginFrm").submit();
	});
	</script>
</#if>
	<div id="loginWrapper">
		<!-- header -->
		<div id="header">
		</div>
		<!-- //header -->
	<#if loginInfo??>
		<#assign permLevel = loginInfo.userType?lower_case>
		<#if permLevel == "fa">
			<#assign url = hongkongUrl + '/' + permLevel/>
		<#elseif permLevel == "ja">
			<#assign url = hongkongUrl + '/' + permLevel/>
		<#elseif permLevel == "ma">
			<#assign url = '/' + permLevel/>
		</#if>
	</#if>
		<!-- container -->
		<div id="loginContainer">
		<h2><@security.authorize access="isAuthenticated()">&nbsp;&nbsp;<@security.authentication property="principal.empKey" /> 님 환영합니다. <a style="float:right;" href="${url }">GO!</a></@security.authorize></h2>
			<form name="loginFrm" id="loginFrm" method="post" action="/loginCheck" style="margin:0px;">
				<div class="login-box">
				<@security.authorize access="! isAuthenticated()">
					<dl>
					</dl>
						<fieldset>
							<legend>JEI GLOBAL</legend>
							<div>
								<p><input type="text" placeholder="아이디" name="memberId" maxlength="10" /></p>
								<p><input type="password" placeholder="비밀번호"  name="memberPassword" maxlength="100"/></p>
								<span class="button btn-login"><input type="submit" value="로그인" style="cursor:pointer;"/></span>
							</div>
							<ul>
								<li>비밀번호가 기억이 안나신다구요? 본사로 연락주시기 바랍니다!</li>
								<li>통합관리자 계정 정보를 입력해 주시고 관련 정보는 유출되지 않도록 보안사항을 지켜주세요.</li>
							</ul>
						</fieldset>
				</@security.authorize>
				<@security.authorize access="isAuthenticated()">
					<a href="/logout">로그아웃!</a>
				</@security.authorize>
					<input type="hidden" name="returl" value="${returl?default('')}" />
				</div>
			</form>
		</div>
		<!-- //container -->
	</div>
</body>
</html>