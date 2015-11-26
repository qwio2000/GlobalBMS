<#include "/include/header.ftl">

<!-- Main Content -->		
		
<div class="content">
	<h2 class="conTit">월별실적</h2>
	<form id="searchForm" name="searchForm" action="" method="">
	<ul class="memSearch">
		<li>
			<label for="">검색기간</label>
			<select name="selYY" id="selYY" style="width:105px">
				<#list currentYear?number..currentYear?number-2 as i>
					<option value="${i?c }" <#if i == selYY?number>selected</#if>>${i?c }</option>
				</#list>				
			</select>			
			년 &nbsp;
			<select name="selMM" id="selMM" style="width:105px">
				<#list monthList as list>
				<option value="${list.dtlCD }" <#if list.dtlCD == selMM> selected</#if> >${list.dtlCD }</option>				
				</#list>					
			</select>						
			월
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
		<a id="monthlySalesSearchSubmit" href="javascript:;"><span>검색</span></a>
		<a id="monthlySalesSearchInit" href="javascript:;"><span style="width:110px">Reset</span></a>
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
					<th rowspan="2">마감</th>
				</tr>
				<tr class="line">
					<th>금월</th>
					<th>전월</th>
				</tr>
			</thead>
			<tbody>
				<#list monthlySales as list>			
				<tr class="line2">
					<td class="no_line"><a href="javascript:;" onClick="$.openMonthlySales('${list.jisaCD}','${list.mgYY}','${list.mgMM}','${list.subj}','${list.deptName}');">${list.deptName }</a></td>
					<td>${list.subjBegin }</td>
					<td><a href="javascript:;" onClick="$.openSalesMemSubjRegist('${list.jisaCD}','','${list.mgYYMM}','${list.subj}','${list.deptName}','monthly');">${list.subjNew }</a></td>
					<td>${list.subjNewPrev }</td>
					<td><a href="javascript:;" onClick="$.openSalesMemSubjDrop('${list.jisaCD}','','${list.mgYYMM}','${list.subj}','${list.deptName}','monthly');">${list.subjDrop }</a></td>
					<td>${list.subjNet }</td>
					<td><a href="javascript:;" onClick="$.openSalesMemSubj('${list.jisaCD}','','${list.mgYY}','${list.mgMM}','${list.subj}','${list.deptName}');">${list.subjEnd }</a></td>					
				</tr>
				<#else>
				<tr class="line2">
					<td class="no_line" colspan="7">내용이 없습니다.</td>					
				</tr>								
				</#list>				
			</tbody>
		</table>
	</div>
</div>
<!--// Main Content -->
<#include "/include/footer.ftl">		