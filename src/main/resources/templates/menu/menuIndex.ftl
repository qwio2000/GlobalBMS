<#include "/include/header.ftl">

<body>
<a href="#primary_content" class="skip-nav">본문 컨텐츠 바로가기</a>
<div id="wrapper">
	<!-- header -->
	<#include "/include/headerMenu.ftl">
	<!-- //header -->

	<!-- container -->
	<div id="container">
		<div id="content">
			<div class="side-content">
				<#include "/include/leftContents.ftl">
				<#include "/include/leftMenu.ftl">
				<div class="banner-section">
					<a href="#none"><img src="/public/img/common/bn_side_02.gif" alt="" /></a>
					<a href="#none"><img src="/public/img/common/bn_side_03.gif" alt="" /></a>
				</div>
			</div>
			<div id="primary_content" class="primary-content">
				<div id="group_warp">
					<div>보기모드
						<select id="viewModeJisaCD">
							<option value="00">본사</option>
							<#list jisaCDs as jisaCD>
								<option value="${jisaCD.dtlCD }">${jisaCD.dtlCDNM }</option>
							</#list>
						</select>
						<select id="viewModeEmpKeyLvCD">
							<#list userTypes as userType>
								<option value="${userType.dtlCD }">${userType.dtlCDNM }</option>
							</#list>
						</select>
						<select id="viewModeDepMngCD">
							<#list userLevels as userLevel>
								<option value="${userLevel.dtlCD }">${userLevel.dtlCDNM }</option>
							</#list>
						</select>
						<input type="button" onclick="$.searchMenu();"value="검색"/>
					</div>
					<div id="menu_list"></div>
					<div id="menu_center" class='group_center'></div>
					<div id="menu_content"></div>
				</div>
			</div>
		</div>
	</div>
	<!-- //container -->

	<!-- footer -->
	<#include "/include/footer.ftl">
	<!-- //footer -->
</div>
</body>
</html>
