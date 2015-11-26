<#include "/include/popupheader.ftl">
<!-- Main Content -->
<div class="popup" style="min-width:300px">
	<div class="popup_top"><h1>마감과목 회원 현황 &gt; ${deptName?default('') }</h1> <a href="javascript:self.close();" class="btn_popup_close">close</a></div>
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
							<th>최초<br />입회일</th>
							<th>복회일</th>
							<th>퇴회일</th>
							<th>유지<br />개월수</th>
							<th>관리<br />요일</th>
							<th>진행<br />등급</th>																																			
							<th>도시/주</th>
							<th>교육원</th>														
						</tr>
					</thead>
					<tbody>
						<#list salesMemSubjPop as list>
						<tr class="line2">
							<td class="no_line">${(list_index + 1)?c}</td>
							<td>${list.memKey }</td>
							<td>${list.memName }</td>							
							<td>${list.subj }</td>
							<td>${list.registFstYMD }</td>
							<td>${list.registFnlYMD }</td>
							<td>${list.dropFnlYMD }</td>
							<td>${list.studyMonth }</td>
							<td>${list.yoilName }</td>		
							<td>${list.wbGrade }</td>
							<td>${list.stateName }</td>
							<td>${list.deptName }</td>
						</tr>
						<#else>
						<tr class="line2">
							<td class="no_line" colspan="12">내용이 없습니다.</td>
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
