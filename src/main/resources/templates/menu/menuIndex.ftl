<#include "/include/header.ftl">
<body>
	<div class="wrap">
		<#include "/include/headerMenu.ftl">
		<div class="content">
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
		<#include "/include/footer.ftl">
	</div>
</body>
</html>