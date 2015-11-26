<#include "/include/popupheader.ftl">
<!-- Main Content -->
<div class="popup" style="min-width:300px">
	<div class="popup_top"><h1>월별실적조회 &gt; ${deptName?default('') }</h1> <a href="javascript:self.close();" class="btn_popup_close">close</a></div>
	<div class="popup_content">
		<div class="pop_gm">
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
							<#if selSubj=="">
							<th colspan="12">상품별실적</th>
							</#if>
						</tr>
						<tr class="line">
							<th>금월</th>
							<th>전월</th>
							<#if selSubj=="">
							<th>KM</th>
							<th>KK</th>							
							<th>KG</th>
							<th>EM</th>
							<th>EE</th>
							<th>KP</th>							
							<th>KS</th>							
							<th>PS</th>
							<th>ER</th>
							<th>CP</th>
							<th>CL</th>
							<th>EP</th>
							</#if>
						</tr>
					</thead>
					<tbody>
						<#list monthlySalesPop as list>
						<tr class="line2">
							<td class="no_line left">${list.deptName }</td>
							<td>${list.subjBegin }</td>
							<td>${list.subjNew }</td>
							<td>${list.subjNewPrev }</td>
							<td>${list.subjDrop }</td>
							<td>${list.subjNet }</td>
							<td><a href="javascript:;" onClick="$.openSalesMemSubj('${list.jisaCD}','${list.deptCD}','${list.mgYY}','${list.mgMM}','${list.subj}','${list.deptName}');">${list.subjEnd }</a></td>
							<#if selSubj=="">
							<td><a href="javascript:;" onClick="$.openSalesMemSubj('${list.jisaCD}','${list.deptCD}','${list.mgYY}','${list.mgMM}','KM','${list.deptName}');">${list.subjKM }</a></td>
							<td><a href="javascript:;" onClick="$.openSalesMemSubj('${list.jisaCD}','${list.deptCD}','${list.mgYY}','${list.mgMM}','KK','${list.deptName}');">${list.subjKK }</a></td>
							<td><a href="javascript:;" onClick="$.openSalesMemSubj('${list.jisaCD}','${list.deptCD}','${list.mgYY}','${list.mgMM}','KG','${list.deptName}');">${list.subjKG }</a></td>
							<td><a href="javascript:;" onClick="$.openSalesMemSubj('${list.jisaCD}','${list.deptCD}','${list.mgYY}','${list.mgMM}','EM','${list.deptName}');">${list.subjEM }</a></td>
							<td><a href="javascript:;" onClick="$.openSalesMemSubj('${list.jisaCD}','${list.deptCD}','${list.mgYY}','${list.mgMM}','EE','${list.deptName}');">${list.subjEE }</a></td>
							<td><a href="javascript:;" onClick="$.openSalesMemSubj('${list.jisaCD}','${list.deptCD}','${list.mgYY}','${list.mgMM}','KP','${list.deptName}');">${list.subjKP }</a></td>
							<td><a href="javascript:;" onClick="$.openSalesMemSubj('${list.jisaCD}','${list.deptCD}','${list.mgYY}','${list.mgMM}','KS','${list.deptName}');">${list.subjKS }</a></td>
							<td><a href="javascript:;" onClick="$.openSalesMemSubj('${list.jisaCD}','${list.deptCD}','${list.mgYY}','${list.mgMM}','PS','${list.deptName}');">${list.subjPS }</a></td>
							<td><a href="javascript:;" onClick="$.openSalesMemSubj('${list.jisaCD}','${list.deptCD}','${list.mgYY}','${list.mgMM}','ER','${list.deptName}');">${list.subjER }</a></td>
							<td><a href="javascript:;" onClick="$.openSalesMemSubj('${list.jisaCD}','${list.deptCD}','${list.mgYY}','${list.mgMM}','CP','${list.deptName}');">${list.subjCP }</a></td>
							<td><a href="javascript:;" onClick="$.openSalesMemSubj('${list.jisaCD}','${list.deptCD}','${list.mgYY}','${list.mgMM}','CL','${list.deptName}');">${list.subjCL }</a></td>
							<td><a href="javascript:;" onClick="$.openSalesMemSubj('${list.jisaCD}','${list.deptCD}','${list.mgYY}','${list.mgMM}','EP','${list.deptName}');">${list.subjEP }</a></td>
							</#if>							
						</tr>
						<#else>
						<tr class="line2">
							<#if selSubj=="">
							<td class="no_line" colspan="19">내용이 없습니다.</td>
							<#else>
							<td class="no_line" colspan="7">내용이 없습니다.</td>
							</#if>							
						</tr>						
						</#list>
						<tr class="line2 total">
							<td class="no_line">합계</td>
							<td>${monthlySalesPopTot.subjBegin }</td>
							<td>${monthlySalesPopTot.subjNew }</td>
							<td>${monthlySalesPopTot.subjNewPrev }</td>
							<td>${monthlySalesPopTot.subjDrop }</td>
							<td>${monthlySalesPopTot.subjNet }</td>
							<td>${monthlySalesPopTot.subjEnd }</td>
							<#if selSubj=="">
							<td>${monthlySalesPopTot.subjKM }</td>
							<td>${monthlySalesPopTot.subjKK }</td>
							<td>${monthlySalesPopTot.subjKG }</td>
							<td>${monthlySalesPopTot.subjEM }</td>
							<td>${monthlySalesPopTot.subjEE }</td>
							<td>${monthlySalesPopTot.subjKP }</td>
							<td>${monthlySalesPopTot.subjKS }</td>
							<td>${monthlySalesPopTot.subjPS }</td>
							<td>${monthlySalesPopTot.subjER }</td>
							<td>${monthlySalesPopTot.subjCP }</td>
							<td>${monthlySalesPopTot.subjCL }</td>
							<td>${monthlySalesPopTot.subjEP }</td>
							</#if>							
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<!--// Main Content -->
<#include "/include/popupfooter.ftl">
