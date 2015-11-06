<#-- @ftlvariable name="loginInfo" type="com.jeiglobal.domain.auth.LoginInfo" -->
<#-- @ftlvariable name="jisaAuthInfo" type="com.jeiglobal.hk.auth.Authority" -->
<#-- @ftlvariable name="bmsAuthInfo" type="com.jeiglobal.hk.auth.Authority" -->
<#-- @ftlvariable name="imgPath" type="java.lang.String" -->
<#-- @ftlvariable name="cssPath type="java.lang.String" -->
<#-- @ftlvariable name="jsPath" type="java.lang.String" -->
<#-- @ftlvariable name="mainWeek" type="java.util.List" -->
<#import "/spring.ftl" as spring/>
<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<!DOCTYPE html>
<!--[if IE 7]><html lang="ko" class="ie7"><![endif]-->
<!--[if IE 8]><html lang="ko" class="ie8"><![endif]-->
<html lang="ko">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<title>${title?default('JEI-GLOBAL') }</title>
	<link rel="stylesheet" type="text/css" href="${cssPath }/common.css" />
	<link rel="stylesheet" type="text/css" href="${cssPath }/jquery-ui.1.11.4.min.css" />
	<#if headerCss?has_content>
	<#list headerCss as css>
		<link rel="stylesheet" type="text/css" href="${cssPath }/${css}.css" />
	</#list>
	</#if>
	<script type="text/javascript" src="${jsPath }/common/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="${jsPath }/common/handlebars-3.0.3-min.js"></script>
	<script type="text/javascript" src="${jsPath }/common/common.js"></script>
	<script type="text/javascript" src="${jsPath }/common/jquery-ui.1.11.4.min.js"></script>
	<#if headerScript?has_content>
	<#list headerScript as script>
		<script type="text/javascript" src="${jsPath }/${script}.js"></script>
	</#list>
	</#if>
	<script type="text/javascript">
		var imgPath = '${imgPath}';
		var jisaCD = '${loginInfo.jisaCD}';
		var deptCD = '${loginInfo.deptCD}';
		var userType = '${loginInfo.userType}';
	</script>
</head>
<body>