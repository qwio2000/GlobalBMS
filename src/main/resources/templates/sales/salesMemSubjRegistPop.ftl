<#include "/include/popupheader.ftl">
<!-- Main Content -->
<div class="popup" style="min-width:300px">
	<div class="popup_top"><h1>입회 현황 &gt; ${deptName?default('') }</h1> <a href="javascript:self.close();" class="btn_popup_close">close</a></div>
	<div class="popup_content">
		<div class="pop_gm">
			<div class="tbl01 tbl_w100">
				<table>
					<thead>
						<tr class="line">
							<th class="no_line" style="width:60px">순번</th>
							<th>회원번호</th>
							<th>회원명</th>
							<th>과목</th>							
							<th>입복회일</th>
							<th>입회구분</th>
							<th>관리<br />요일</th>
							<th>첫관리방문일</th>																																			
							<th>도시/주</th>
							<th>교육원</th>														
						</tr>
					</thead>
					<tbody>
						<#list salesMemSubjRegistPop as list>
						<tr class="line2">
							<td class="no_line">${(list_index + 1)?c}</td>
							<td>${list.memKey }</td>
							<td>${list.memName }</td>							
							<td>${list.subj }</td>
							<td>${list.registYMD }</td>
							<td>${list.registGubunName }</td>
							<td>${list.yoilName }</td>		
							<td>${list.fstVisitYMD }</td>
							<td>${list.stateName }</td>
							<td>${list.deptName }</td>
						</tr>
						<#else>
						<tr class="line2">
							<td class="no_line" colspan="10">내용이 없습니다.</td>
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
