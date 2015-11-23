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
							<th rowspan="2">현재</th>
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
							<td>${list.subjEnd }</td>
							<#if selSubj=="">
							<td>${list.subjKM }</td>
							<td>${list.subjKK }</td>
							<td>${list.subjKG }</td>
							<td>${list.subjEM }</td>
							<td>${list.subjEE }</td>
							<td>${list.subjKP }</td>
							<td>${list.subjKS }</td>
							<td>${list.subjPS }</td>
							<td>${list.subjER }</td>
							<td>${list.subjCP }</td>
							<td>${list.subjCL }</td>
							<td>${list.subjEP }</td>
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

					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<!--// Main Content -->
<#include "/include/popupfooter.ftl">
