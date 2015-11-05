<#include "/include/header.ftl">
<!-- Main Content -->
<div class="content">
	<h2 class="conTit">회원 조회</h2>
	<input type="hidden" id="kind" value="${memberSearchInfo.kind?default('') }"/>
	<input type="hidden" id="type" value="${memberSearchInfo.type?default('') }"/>
	<input type="hidden" id="memKey" value="${memberSearchInfo.memKey?default('') }"/>
	<input type="hidden" id="memName" value="${memberSearchInfo.memName?default('') }"/>
	<input type="hidden" id="birthYM" value="${memberSearchInfo.birthYM?default('') }"/>
	<input type="hidden" id="pageNum" value="1"/>
	<div class="tbl01">
		<table>
			<colgroup>
				<col width="30">
				<col width="90">
				<col width="30">
				<col width="80">
				<col width="120">
				<col width="90">
				<col width="90">
				<col width="90">
				<col width="80">
				<col width="90">
				<col width="*">
			</colgroup>
			<thead>
				<tr>
					<th>No.</th>
					<th>회원번호</th>
					<th>과목</th>
					<th>상태</th>
					<th>이름</th>
					<th>생년월일</th>
					<th>최종입복회일</th>
					<th>최종퇴회일</th>
					<th>지사</th>
					<th>연락처</th>
					<th>주소</th>
				</tr>
			</thead>
			<tbody id="mainContent">
			</tbody>
		</table>
	</div>
	<div class="paging">
		<span id="pageNavi"></span>
	</div>
</div>
<!--// Main Content -->
<script id="memberReportTemplate" type="text/x-handlebars-template">
	{{#each result}}
		<tr>
			<td>{{inc @index}}</td>
			<td>{{memKey}}</td>
			<td>{{subj}}</td>
			<td>{{statusCDNM}}</td>
			<td>{{mfstName}} {{mlstName}}</td>
			<td>{{mbirthDay}}</td>
			<td>{{registFnlYMD}}</td>
			<td>{{dropFnlYMD}}</td>
			<td>{{jisaCDNM}}</td>
			<td>{{gphone}}</td>
			<td>{{addr}}</td>
		</tr>
	{{else}}
		<tr>
			<td colspan="11">no search results</td>
		</tr>
	{{/each}}
</script>
<#include "/include/footer.ftl">