<script type="text/javascript">
		$(function() {
			$("#tree").treeview({
				collapsed: true,
				animated: "medium",
				control:"#sidetreecontrol",
				persist: "location"
			});
		})
</script>

<div id="sidetree">
	<div class="treeheader">&nbsp;</div>
	<div id="sidetreecontrol">
		<a href="?#">Collapse All</a> | <a href="?#">Expand All</a>
	</div>
		<#assign cnt = menuList?size>
		<#assign depth = 1>
		<#assign deptemp = 0>
		<ul id='tree'>ROOT&nbsp;
			<a onclick='$.menu_add(1);' style='cursor:pointer'><img src='/public/img/menu/icons_insert.gif'></a>
			&nbsp;
			<#if cnt gt 1 >
			<a onclick='$.menu_change(1);' style='cursor:pointer'><img src='/public/img/menu/icons_sort.gif'></a>
			</#if>
			<ul>
			<#if cnt gt 1>
			<#list 1..cnt-1 as i>
					<li>
					<span>
						<#if 0 == menuList[i].MUseState?number>
							<del>${menuList[i].MMenuName }</del>			
						<#else>
							${menuList[i].MMenuName}
						</#if>
					</span>
					&nbsp;<a onclick='$.menu_modify("${menuList[i].MIdx}");' style='cursor:pointer'><img src='/public/img/menu/icons_modify.gif'></a>
					&nbsp;<a onclick='$.menu_add("${menuList[i].MIdx}");' style='cursor:pointer'><img src='/public/img/menu/icons_insert.gif'></a>
					&nbsp;<a onclick='$.menu_delete("${menuList[i].MIdx}");' style='cursor:pointer'><img src='/public/img/menu/icons_delete.gif'></a>
					<#if 1 == menuList[i].MHasChildren?number>
						&nbsp;<a onclick='$.menu_change("${menuList[i].MIdx}");' style='cursor:pointer'><img src='/public/img/menu/icons_sort.gif'></a>
						<ul>
						<#assign depth = depth + 1>
					<#else>
						<#if depth gt 1>
							<#if cnt gt i+1>
								<#assign deptemp = menuList[i].MDepth - menuList[i+1].MDepth>
								<#if deptemp == 0>
									</li>
								<#else>
									<#list 0..deptemp-1 as j>
										</li></ul>
										<#assign depth = depth - 1>
									</#list>
								</#if>
							<#else>
								<#list 0..depth-2 as k>
									</li></ul>
								</#list>
								</li>
							</#if>
						<#else>
							</li>
						</#if>
					</#if>
				</#list>
				</#if>
				</ul>
</div>
