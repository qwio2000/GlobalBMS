<#include "/include/header.ftl">

<!-- Main Content -->		
		
<div class="content">
	<h2 class="conTit">일일실적</h2>
	<form id="searchForm" name="searchForm" action="" method="">	
	<ul class="memSearch">
		<li>
			<label for="">검색일</label>
			<input type="text" name="selYMD" id="selYMD"  value="${selYMD?default('') }" class="searchInput" style="width:190px" readOnly >
			<a class="btn_calendar" id="selYMDDatePicker" style="cursor: pointer;">view calendar</a>			
		</li>
		<li>
			<label for="">과목</label>
			<select name="selSubj" id="selSubj" style="width:240px">
				<option value="">전체</option>
				<#list subjList as list>
				<option value="${list.dtlCD }" <#if list.dtlCD == selSubj> selected</#if> >${list.dtlCDNM }</option>				
				</#list>								
			</select>
		</li>
	</ul>
	<div class="btnArea">
		<a id="dailySalesSearchSubmit" href="javascript:;"><span>검색</span></a>
		<a id="dailySalesSearchInit" href="javascript:;"><span style="width:110px">Reset</span></a>
	</div>
	</form>	
	<div class="tbl01 tbl_w100">
		<table>
			<thead>
				<tr class="line">
					<th rowspan="2" class="no_line">구분</th>
					<th rowspan="2">기초</th>
					<th colspan="2">입회</th>
					<th rowspan="2">퇴회</th>
					<th rowspan="2">순증</th>
					<th rowspan="2">현재</th>
				</tr>
				<tr class="line">
					<th>금월</th>
					<th>전월</th>
				</tr>
			</thead>
			<tbody>
				<#list dailySales as list>
				<tr class="line2">
					<td class="no_line"><a href="javascript:;" onClick="$.openDailySales('${list.jisaCD}','${list.salesYMD}','${list.subj}','${list.deptName}');">${list.deptName }</a></td>
					<td>${list.subjBegin }</td>
					<td><a href="javascript:;" onClick="$.openSalesMemSubjRegist('${list.jisaCD}','','${list.salesYMD}','${list.subj}','${list.deptName}','daily');">${list.subjNew }</a></td>
					<td>${list.subjNewPrev }</td>
					<td><a href="javascript:;" onClick="$.openSalesMemSubjDrop('${list.jisaCD}','','${list.salesYMD}','${list.subj}','${list.deptName}','daily');">${list.subjDrop }</a></td>
					<td>${list.subjNet }</td>
					<td>${list.subjEnd }</td>
				</tr>
				</#list>			
			</tbody>
		</table>
	</div>
</div>
<!--// Main Content -->
<#include "/include/footer.ftl">		