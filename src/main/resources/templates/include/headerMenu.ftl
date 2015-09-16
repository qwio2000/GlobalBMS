<div class="headerWrap">
	<div class="header">
		<a href="/logout"/><div class="logout"><strong>Logout</strong></div></a>
		<h1>
			<a href="/ma/records"><img src="${imgPath }/logo.png" alt="JEI Corporate HQ(JEI Korea)" /></a>
			<span>${loginInfo.deptName }(${loginInfo.userFstName }${loginInfo.userLstName?default('') })</span>
		</h1>
		<span class="utilInfo">Server Time : ${.now?string.medium_short} <br>System Week #2[9/6, 15-9, 12/15]</span>
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