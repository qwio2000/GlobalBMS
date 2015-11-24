<#include "/include/popupheader.ftl">
<!-- Main Content -->
<div class="popup" style="min-width:300px">
	<div class="popup_top"><h1>조직찾기 &gt; Hong Kong</h1> <a href="javascript:self.close();" class="btn_popup_close">close</a></div>
	<div class="popup_content">
		<div class="pop_gm">
			<div class="tbl01 tbl_w100">
				<table>
					<thead>
						<tr class="line">
							<th class="no_line" style="width:40px">순번</th>
							<th>State</th>
							<th>Center</th>
						</tr>
					</thead>
					<tbody>
						<#list deptSearchPop as list>
						<tr class="line2">
							<td class="no_line">${list_index + 1}</td>
							<td>${list.stateName }</td>
							<td class="left"><a href="javascript:;" onClick="$.deptSearchSelect('${list.jisaCD}','${list.deptCD }','${list.deptName }');">${list.deptName }</a></td>
						</tr>
						<#else>
						</#list>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<!--// Main Content -->
<#include "/include/popupfooter.ftl">
