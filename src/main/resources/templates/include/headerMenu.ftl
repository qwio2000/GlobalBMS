<div class="headerWrap">
	<div class="header">
		<div class="logout"><strong>Logout</strong></div>
		<h1><img src="/public/img/logo.png" alt="JEI Corporate HQ(JEI Korea)" /><span>${loginInfo.deptName }(${loginInfo.userFirstName }${loginInfo.userLastName?default('') })</span></h1>
		<#assign today = .now>
		<span class="utilInfo">Server Time : ${today?string.medium_short} <br>System Week #2[9/6, 15-9, 12/15]</span>
<!-- 		<span class="utilInfo">Server Time : Sep 6, 2015 10:07 PM <br>System Week #2[9/6, 15-9, 12/15]</span> -->
		<ul class="gnb">
			<#list menuMap as menuList>
			<li>
				<#list menuList as menu>
					<#if menu.MParentIdx == 1>
						<a href="${menu.MMenuLink }" class="">${menu.MMenuName }<span></span></a>
						<#if menu.MHasChildren == "1">
							<ul class="gnbsub">
						</#if>
					<#else>
						<li><a href="${menu.MMenuLink }">${menu.MMenuName }</a></li>
					</#if>
				</#list>
				<#if menuList?size != 1>
					</ul>
				</#if>
			</li>
			</#list>
		</ul>
	</div>
</div>