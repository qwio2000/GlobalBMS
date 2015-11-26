<#include "/include/header.ftl">

<!-- Main Content -->		
<div class="content">
	<h2 class="conTit">상품별 추이</h2>
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
			<select name="selYY" id="selYY" style="width:240px">
				<#list currentYear?number..currentYear?number-2 as i>
					<option value="${i?c }" <#if i == selYY?number>selected</#if>>${i?c }</option>
				</#list>				
			</select>	
			년
		</li>		
	</ul>
	<div class="btnArea">
		<a id="statProgBySubjSearchSubmit" href="javascript:;"><span>검색</span></a>
		<a id="statProgBySubjSearchInit" href="javascript:;"><span style="width:110px">Reset</span></a>
	</div>	
	</form>
	<div class="tbl01">
		<table>
			<thead>
				<tr class="line">
					<th rowspan="2"  class="no_line" style="width:72px">Program</th>
					<th colspan="12">${prevSelYY?c }</th>
					<th colspan="12">${selYY }</th>
				</tr>
				<tr class="line bg_gray">
					<th style="width:38px">Jan</th>
					<th style="width:38px">Feb</th>
					<th style="width:38px">Mar</th>
					<th style="width:38px">Apr</th>
					<th style="width:38px">May</th>
					<th style="width:38px">Jun</th>
					<th style="width:38px">Jul</th>
					<th style="width:38px">Aug</th>
					<th style="width:38px">Sep</th>
					<th style="width:38px">Oct</th>
					<th style="width:38px">Nov</th>
					<th style="width:38px">Dec</th>
					<th style="width:38px">Jan</th>
					<th style="width:38px">Feb</th>
					<th style="width:38px">Mar</th>
					<th style="width:38px">Apr</th>
					<th style="width:38px">May</th>
					<th style="width:38px">Jun</th>
					<th style="width:38px">Jul</th>
					<th style="width:38px">Aug</th>
					<th style="width:38px">Sep</th>
					<th style="width:38px">Oct</th>
					<th style="width:38px">Nov</th>
					<th style="width:38px">Dec</th>
				</tr>
			</thead>
			<tbody>
				<#list statProgBySubj as list>
				<tr class="line2 <#if list.subj=='TT'>total</#if>">
					<#if list.subj=='TT'>
					<td class="no_line">${list.subjShortName }</td>
					<#else>
					<th class="no_line no_left">${list.subjShortName }</th>
					</#if>
					<td>${list.prevMgMM01 }</td>
					<td>${list.prevMgMM02 }</td>
					<td>${list.prevMgMM03 }</td>
					<td>${list.prevMgMM04 }</td>
					<td>${list.prevMgMM05 }</td>
					<td>${list.prevMgMM06 }</td>
					<td>${list.prevMgMM07 }</td>
					<td>${list.prevMgMM08 }</td>
					<td>${list.prevMgMM09 }</td>
					<td>${list.prevMgMM10 }</td>
					<td>${list.prevMgMM11 }</td>
					<td>${list.prevMgMM12 }</td>
					<td><#if currentYear?number &gt; selYY?number || (currentYear?number == selYY?number && currentMonth?number &gt; 1) >${list.mgMM01 }</#if></td>
					<td><#if currentYear?number &gt; selYY?number || (currentYear?number == selYY?number && currentMonth?number &gt; 2) >${list.mgMM02 }</#if></td>
					<td><#if currentYear?number &gt; selYY?number || (currentYear?number == selYY?number && currentMonth?number &gt; 3) >${list.mgMM03 }</#if></td>
					<td><#if currentYear?number &gt; selYY?number || (currentYear?number == selYY?number && currentMonth?number &gt; 4) >${list.mgMM04 }</#if></td>
					<td><#if currentYear?number &gt; selYY?number || (currentYear?number == selYY?number && currentMonth?number &gt; 5) >${list.mgMM05 }</#if></td>
					<td><#if currentYear?number &gt; selYY?number || (currentYear?number == selYY?number && currentMonth?number &gt; 6) >${list.mgMM06 }</#if></td>
					<td><#if currentYear?number &gt; selYY?number || (currentYear?number == selYY?number && currentMonth?number &gt; 7) >${list.mgMM07 }</#if></td>
					<td><#if currentYear?number &gt; selYY?number || (currentYear?number == selYY?number && currentMonth?number &gt; 8) >${list.mgMM08 }</#if></td>
					<td><#if currentYear?number &gt; selYY?number || (currentYear?number == selYY?number && currentMonth?number &gt; 9) >${list.mgMM09 }</#if></td>
					<td><#if currentYear?number &gt; selYY?number || (currentYear?number == selYY?number && currentMonth?number &gt; 10) >${list.mgMM10 }</#if></td>
					<td><#if currentYear?number &gt; selYY?number || (currentYear?number == selYY?number && currentMonth?number &gt; 11) >${list.mgMM11 }</#if></td>
					<td><#if currentYear?number &gt; selYY?number || (currentYear?number == selYY?number && currentMonth?number &gt; 12) >${list.mgMM12 }</#if></td>
					<td class="no_line"></td>
				</tr>
				</#list>			
			</tbody>
		</table>
	</div>
</div>		

<!--// Main Content -->
<#include "/include/footer.ftl">		