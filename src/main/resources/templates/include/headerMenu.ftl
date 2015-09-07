	<!-- header -->
	<div id="header">
		<div class="content">
			<h1 class="logo"><a href="/"><img src="/public/img/common/logo_header.gif" alt="재능Global" /></a></h1>
			<div class="notice">
			</div>
			<div class="login-info">
				<dl>
				</dl>
				<@security.authorize ifAnyGranted="SUPERADMIN">
				<div class="btn-join-state">
					<a href="/adminManager/menuIndex"><img src="/public/img/common/btn_go.gif" alt="메뉴관리" /></a>
				</div>
				</@security.authorize>
				<div class="btn-join-state">
					<a href="/logout"><img src="/public/img/common/btn_gnb_logout.gif" alt="로그아웃" /></a>
				</div>
			</div>
			<ul class="lnb">
			<#if headerMenuList?has_content>
				<#list headerMenuList as headerMenu>
					<li><a href="${headerMenu.MMenuLink }"><span style="margin-right: 20px;font-size: 15px;color: white;<#if headerMenu.MMenuCode == menuFirstCode>font-weight:bold;</#if>">${headerMenu.MMenuName }</span></a></li>
				</#list>
			</#if>
			</ul>
		</div>
	</div>
	<!-- //header -->