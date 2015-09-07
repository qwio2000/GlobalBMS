			<#if leftMenuList?has_content>
					<#assign depth = 1 deptemp = 0>
					<#assign menuCodeCnt = menuCode?length>
				<div class="snb">
					<h3>${leftMenuList[0].MMenuName}</h3>
					<#if ((leftMenuList?size)-1 > 0)>
						<ul>
						<#list 1..(leftMenuList?size-1) as i>
								<#if (menuCodeCnt == 7 && (leftMenuList[i].MMenuCode == menuTwoCode)) >
									<li class="active">
								<#elseif (menuCodeCnt == 5 && (leftMenuList[i].MMenuCode == menuTwoCode)) >	
									<li class="active">
								<#elseif (menuCodeCnt == 3 && (leftMenuList[i].MMenuCode == menuCode)) >	
									<li class="active">
								<#else>
									<li>
								</#if>
									<#if ((menuCodeCnt == 7) && (leftMenuList[i].MMenuCode == menuThreeCode)) || leftMenuList[i].MMenuCode == menuCode>
										<a class="on" href="${leftMenuList[i].MMenuLink}">
									<#else>
										<a href="${leftMenuList[i].MMenuLink}">
									</#if>
								<#if (leftMenuList[i].MDepth == 4)>
								-
								<#elseif (leftMenuList[i].MDepth >= 5)>
								*
								</#if>
								${leftMenuList[i].MMenuName}</a>
								<#if leftMenuList[i].MHasChildren == "1">
									<ul>
									<#assign depth = depth+1>
								<#else>
									<#if (depth > 1)>
										<#if (i+1 < leftMenuList?size) >
											<#assign deptemp = leftMenuList[i].MDepth-leftMenuList[i+1].MDepth>
											<#if (deptemp == 0) >
												</li>
											<#else>
												<#list 1..deptemp as j>
													</li></ul>
													<#assign depth = depth-1>
												</#list>
											</#if>
											</li>
										<#else>
											<#list 1..(depth-1) as k >	
												</li></ul>
											</#list>
											</li>
										</#if>
									<#else>
										</li>
									</#if>
								</#if>								
						</#list>
							</ul>
					</#if>
				</div>
				</#if>