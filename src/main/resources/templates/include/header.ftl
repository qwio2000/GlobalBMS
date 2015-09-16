<#setting locale="en_US">
<#-- @ftlvariable name="loginInfo" type="com.jeiglobal.domain.auth.LoginInfo" -->
<#-- @ftlvariable name="hongkongUrl" type="java.lang.String" -->
<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<!DOCTYPE html>
<!--[if IE 7]><html lang="ko" class="ie7"><![endif]-->
<!--[if IE 8]><html lang="ko" class="ie8"><![endif]-->
<html lang="ko">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title>${title?default('JEI-GLOBAL') }</title>
	<link rel="stylesheet" type="text/css" href="/public/css/common.css" />
	<link rel="stylesheet" type="text/css" href="/public/css/jquery-ui.1.11.4.min.css" />
	<#if headerCss?has_content>
	<#list headerCss as css>
		<link rel="stylesheet" type="text/css" href="/public/css/${css}.css" />
	</#list>
	</#if>
	<script type="text/javascript" src="/public/js/common/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="/public/js/common/handlebars-3.0.3-min.js"></script>
	<script type="text/javascript" src="/public/js/common/common.js"></script>
	<script type="text/javascript" src="/public/js/common/jquery-ui.1.11.4.min.js"></script>
	<#if headerScript?has_content>
	<#list headerScript as script>
		<script type="text/javascript" src="/public/js/${script}.js"></script>
	</#list>
</#if>
</head>
<body>
	<div class="wrap">
		<#include "/include/headerMenu.ftl">