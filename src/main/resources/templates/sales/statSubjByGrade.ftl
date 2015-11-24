<#include "/include/header.ftl">

<!-- Main Content -->		
<div class="content">
	<h2 class="conTit">등급별 과목 수</h2>
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
					<option value="${i?c }" <#if i == currentYear?number>selected</#if>>${i?c }</option>
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
				<#list subjList as list>
				<option value="${list.dtlCD }" <#if list.dtlCD == selSubj> selected</#if> >${list.dtlCDNM }</option>				
				</#list>								
			</select>	
		</li>
	</ul>
	<div class="btnArea">
		<a id="statSubjByGradeSearchSubmit" href="javascript:;"><span>검색</span></a>
		<a id="statSubjByGradeSearchInit" href="javascript:;"><span style="width:110px">Reset</span></a>
	</div>	
	</form>
	<div class="tbl01">
		<table>
			<colgroup>
				<col style="width:100px" />
				<col />
				<col style="width:230px" />
				<col style="width:230px" />
			</colgroup>
			<thead>
				<tr class="line">
					<th rowspan="2" class="no_line">순번</th>
					<th rowspan="2">등급</th>
					<th colspan="2">과목</th>
				</tr>
				<tr class="line">
					<th>수</th>
					<th>%</th>
				</tr>
			</thead>
			<tbody>
				<#if totCnt != 0 >
				<tr class="total line2">
					<td class="no_line"></td>
					<td>합계</td>
					<td><a href="javascript:$.openStatSubjByGrade('${jisaCD }','${deptCD }','${selSubj }','','${selYY }','${selMM }','${deptName }');">${totSubjCnt }</a></td>
					<td>${totSubjRate }</td>
				</tr>
				</#if >
				<#list statSubjByGrade as list>
				<tr class="line2">
					<td class="no_line">${list_index + 1 }</td>
					<td>${list.wbGrade }</td>
					<td><a href="javascript:$.openStatSubjByGrade('${list.jisaCD }','${list.deptCD }','${list.subj }','${list.wbGrade }','${list.mgYY }','${list.mgMM }','${deptName }');">${list.subjCnt }</a></td>
					<td>${list.subjRate }</td>
				</tr>
				<#else>
				<tr class="line2">
					<td class="no_line" colspan="4">내용이 없습니다</td>
				</tr>				
				</#list>
			</tbody>
		</table>
	</div>
</div>		

<!--// Main Content -->
<#include "/include/footer.ftl">		