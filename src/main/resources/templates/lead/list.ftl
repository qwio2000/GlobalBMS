<#include "/include/header.ftl">
<!-- Main Content -->
<div class="content">
	<h2 class="conTit">가맹점 희망자 List </h2>
	<div class="list02 pt20 clearfix">
		<div class="float_l">
			<input type="hidden" id="pageNum" name="pageNum" value="1"/>
			<input type="hidden" id="orderBy" name="orderBy" value=""/>
			<input type="hidden" id="ord" name="ord" value=""/>
			<label for="contactName" class="tit">Contact Name</label>
			<input type="text" id="contactName" name="contactName" value="" class="searchInput" style="width:160px" />
			<select name="statusCD" id="statusCD" style="width:250px">
				<option value="">선택</option>
				<#list leadStatus as status>
					<option value="${status.dtlCD }">${status.dtlCDNM }</option>
				</#list>
			</select>
			<span class="btnArea mt0"><a href="javascript:$.getLeadSearch();"><span style="width:70px">Search</span></a></span>
		</div>
	</div>
	<div class="tbl01 mt5">
		<table>
			<colgroup>
				<col width="150" />
				<col width="*" />
				<col width="180" />
				<col width="100" />
				<col width="100" />
				<col width="100" />
				<col width="100" />
			</colgroup>
			<thead>
				<tr>
					<th><a href="javascript:sort('status')" class="blue">Status</a></th>
					<th><a href="javascript:sort('contactName')" class="blue">Contact Name</a></th>
					<th>Phone</th>
					<th>State</th>
					<th>Source</th>
					<th><a href="javascript:sort('leadYMD')" class="blue">Lead date</a></th>
					<th>Last Contact</th>
				</tr>
			</thead>
			<tbody id="mainContent"></tbody>
		</table>
	</div>
	<div class="paging">
		<span id="pageNavi"></span>
	</div>
</div>
<!--// Main Content -->
<script id="leadsTemplate" type="text/x-handlebars-template">
	{{#each leads}}
	<tr>
		<td>{{statusName}}</td>
		<td class="left pl5"><a href="/ma/leads/{{idx}}" class="icon_search"> {{contactFstName}} {{contactLstName}}</a></td>
		<td class="left pl5">
			<a href="mailto:{{contactEmail}}"><span class="btn_mail"></span></a> {{phone}}</td>
		<td>{{stateName}}</td>
		<td>{{howHearName}}</td>
		<td>{{leadYMD}}</td>
		<td>{{lastContactYMD}}</td>
	</tr>
	{{else}}
		<tr>
			<td colspan="7">no search results</td>
		</tr>
	{{/each}}
</script>
<#include "/include/footer.ftl">