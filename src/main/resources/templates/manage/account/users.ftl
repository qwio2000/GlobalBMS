<#include "/include/header.ftl">
<!-- Main Content -->
<div class="content">
	<h2 class="conTit">사용자 계정 관리</h2>
	<input type="hidden" id="pageNum" value="1"/>
	<div class="clearfix">
		<div class="float_r">
			<div class="btnArea_txt p0"><a href="javascript:addUserPop()" class="btn_doc m0">계정 추가</a></div>
		</div>
	</div>
	<div class="tbl01">
		<table>
			<colgroup>
				<col width="50">
				<col width="100">
				<col width="150">
				<col width="*">
				<col width="80">
				<col width="170">
				<col width="170">
				<col width="100">
			</colgroup>
			<thead>
				<tr>
					<th>No.</th>
					<th>ID</th>
					<th>사용자 이름</th>
					<th>소속</th>
					<th>상태</th>
					<th>등록일</th>
					<th>최종로그인일자</th>
					<th></th>
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
<script id="AccountManageTemplate" type="text/x-handlebars-template">
	{{#each users}}
		<tr>
			<td>{{inc @index}}</td>
			<td>{{userId}}</td>
			<td>{{userFstName}} {{userLstName}}</td>
			<td>{{departMent}}</td>
			<td>{{statusCDNM}}</td>
			<td>{{regDate}}</td>
			<td>{{latestLoginDate}}</td>
			<td>
				<div class="btnArea_icon2" style="width:80px;">
					<a href="" class="btn_info " title="수정">수정</a>
					<a href="" class="btn_del " title="삭제">삭제</a>				
				</div>
			</td>
		</tr>
	{{else}}
		<tr>
			<td colspan="8">결과가 없습니다.</td>
		</tr>
	{{/each}}
</script>
<#include "/include/footer.ftl">