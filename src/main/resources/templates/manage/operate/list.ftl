<#include "/include/header.ftl">
<!-- Main Content -->
<div class="content">
	<h2 class="conTit">전산 운영</h2>
	<div class="tbl01">
		<table>
			<colgroup>
				<col width="50">
				<col width="150">
				<col width="*">
				<col width="150">
				<col width="100">
			</colgroup>
			<thead>
				<tr>
					<th>No.</th>
					<th>업무명</th>
					<th>설명</th>
					<th>작업주기</th>
					<th>작업</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td>전산 마감기간</td>
					<td class="left">전산 마감 시작일 종료일 입력</td>
					<td>월 1회</td>
					<td>
						<div class="btnArea_icon2" style="width:40px;">
							<a href="javascript:openMagamDatePop();" class="btn_info " title="수정">수정</a>
						</div>
					</td>
				</tr>
				<tr>
					<td>2</td>
					<td>입회 가능여부 관리</td>
					<td class="left">월 회비 인상 등의 사유로 입회를 닫아야 할 경우 등록</td>
					<td>변동사항 발생 시</td>
					<td>
						<div class="btnArea_icon2" style="width:40px;">
							<a href="javascript:" class="btn_info " title="수정">수정</a>
						</div>
					</td>
				</tr>
				<tr>
					<td>3</td>
					<td>업무코드 관리</td>
					<td class="left">입회사유, 입회경로, 퇴회사유, 학년 등 업무 분류 코드 생성 및 사용 중지</td>
					<td>변동사항 발생 시</td>
					<td>
						<div class="btnArea_icon2" style="width:40px;">
							<a href="javascript:" class="btn_info " title="수정">수정</a>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
<!--// Main Content -->
<#include "/include/footer.ftl">