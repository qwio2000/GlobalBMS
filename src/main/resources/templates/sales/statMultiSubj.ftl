<#include "/include/header.ftl">

<!-- Main Content -->		

<div class="content">
	<h2 class="conTit">복수과목 회원 현황</h2>
	<form id="searchForm" name="searchForm" action="" method="">	
	<ul class="memSearch">
		<li>
			<label for=""><a href="javascript:;" onClick="$.openDeptSearch();">조직찾기</a></label>
			<input type="text" name="deptName" id="deptName" value="${deptName }" class="searchInput" style="width:230px" onClick="$.openDeptSearch();" readOnly />
			<input type="hidden" name="jisaCD" id="jisaCD" value="${jisaCD }"> 
			<input type="hidden" name="deptCD" id="deptCD" value="${deptCD }">
		</li>
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
				<option value="" >전체</option>
				<#list subjList as list>
				<option value="${list.dtlCD }" <#if list.dtlCD == selSubj> selected</#if> >${list.dtlCDNM }</option>				
				</#list>								
			</select>	
		</li>
	</ul>
	<div class="btnArea">
		<a id="statMultiSubjSearchSubmit" href="javascript:;"><span>검색</span></a>
		<a id="statMultiSubjSearchInit" href="javascript:;"><span style="width:110px">Reset</span></a>
	</div>	
	</form>
	<div class="tbl01 tbl_w70">
		<table>

			<thead>
				<tr class="line">
					<th rowspan="2" class="no_line" style="width:90px">교육원</th>
					<th rowspan="2" style="width:90px">마감회원수</th>
					<th colspan="2">1과목</th>
					<th colspan="2">2과목</th>
					<th colspan="2">3과목</th>
					<th colspan="2">4과목</th>
					<th colspan="2">5과목</th>
					<th colspan="2">6과목이상</th>
				</tr>
				<tr class="line">
					<th>회원수</th>
					<th>%</th>
					<th>회원수</th>
					<th>%</th>
					<th>회원수</th>
					<th>%</th>
					<th>회원수</th>
					<th>%</th>
					<th>회원수</th>
					<th>%</th>
					<th>회원수</th>
					<th>%</th>
				</tr>
			</thead>
			<tbody>
				<#list statMultiSubj as list >
				<tr class="line2">
					<td class="no_line">${list.deptName }</td>
					<td>${list.memEnd }</td>
					<td><a href="javascript:$.openStatMultiSubj('${list.jisaCD }','${list.deptCD }','${list.mgYY }','${list.mgMM }','${list.subj}','1','${list.deptName }');">${list.multi1 }</a></td>
					<td>${list.multi1Rate?string("##0.00") }</td>
					<td><a href="javascript:$.openStatMultiSubj('${list.jisaCD }','${list.deptCD }','${list.mgYY }','${list.mgMM }','${list.subj}','2','${list.deptName }');">${list.multi2 }</a></td>
					<td>${list.multi2Rate?string("##0.00") }</td>
					<td><a href="javascript:$.openStatMultiSubj('${list.jisaCD }','${list.deptCD }','${list.mgYY }','${list.mgMM }','${list.subj}','3','${list.deptName }');">${list.multi3 }</a></td>
					<td>${list.multi3Rate?string("##0.00") }</td>
					<td><a href="javascript:$.openStatMultiSubj('${list.jisaCD }','${list.deptCD }','${list.mgYY }','${list.mgMM }','${list.subj}','4','${list.deptName }');">${list.multi4 }</a></td>
					<td>${list.multi4Rate?string("##0.00") }</td>
					<td><a href="javascript:$.openStatMultiSubj('${list.jisaCD }','${list.deptCD }','${list.mgYY }','${list.mgMM }','${list.subj}','5','${list.deptName }');">${list.multi5 }</a></td>
					<td>${list.multi5Rate?string("##0.00") }</td>
					<td><a href="javascript:$.openStatMultiSubj('${list.jisaCD }','${list.deptCD }','${list.mgYY }','${list.mgMM }','${list.subj}','6','${list.deptName }');">${list.multi6 }</a></td>
					<td>${list.multi6Rate?string("##0.00") }</td>
				</tr>
				<#else >
				<tr class="line2">
					<td class="no_line" colspan="14">내용이 없습니다.</td>
				</tr>				
				</#list >
			</tbody>
		</table>
	</div>
</div>		

<!--// Main Content -->
<#include "/include/footer.ftl">		