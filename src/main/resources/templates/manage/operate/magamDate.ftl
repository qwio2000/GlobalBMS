<#include "/include/popupheader.ftl">
<!-- Main Content -->
	<div class="popup">
		<div class="popup_top"><h1>전산 마감기간 관리</h1> <a href="javascript:self.close();" class="btn_popup_close">close</a></div>
		<div class="popup_content">
			<div class="pop_jisa">
			<input type="hidden" id="pageNum" name="pageNum" value="1"/>
			<input type="hidden" id="popResize" name="popResize" value="N"/>
			<div class="float_r">
				<div class="btnArea_txt p0"><a href="javascript:addNewMagamDate()" class="btn_doc m0">추가</a></div>
			</div>
			<br/>
				<div class="tbl01">
					<table>
						<colgroup>
							<col width="50">
							<col width="120">
							<col width="120">
							<col width="120">
							<col width="120">
							<col width="120">
							<col width="120">
						</colgroup>
						<thead>
							<tr>
								<th>No.</th>
								<th>마감년월</th>
								<th>마감시작일</th>
								<th>마감종료일</th>
								<th>마감월말일자</th>
								<th>마감작업일</th>
								<th>마감확정일</th>
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
			<div id="divData" class="tbl01" style="display: none;">
			<form id="magamForm">
				<table>
					<colgroup>
						<col width="120">
						<col width="150">
						<col width="150">
						<col width="150">
					</colgroup>
					<thead>
						<tr>
							<th>마감년월</th>
							<th>마감시작일</th>
							<th>마감종료일</th>
							<th>마감월말일자</th>
						</tr>
					</thead>
					<tbody id="divContent">
					</tbody>
				</table>
				<div class="btnArea" style="text-align: center;">
					<a id="btnName" style="cursor: pointer;"><span>수정</span></a>
					<a href="javascript:editCancle();"><span>취소</span></a>
				</div>
			</form>
			</div>
		</div>
	</div>
<!--// Main Content -->
<script id="magamDatesTemplate" type="text/x-handlebars-template">
	{{#each magamDates}}
		<tr>
			<td>{{inc @index }}</td>
			<td><a href="javascript:editMagamDate('{{mgMonth}}')" class="blue">{{mgMonth }}</a></td>
			<td>{{mgStartDate }}</td>
			<td>{{mgEndDate}}</td>
			<td>{{mgEndYMD }}</td>
			<td>{{mgJobDate }}</td>
			<td>{{mgJobEndDate }}</td>
		</tr>
	{{/each}}
</script>
<script id="magamDateDivTemplate" type="text/x-handlebars-template">
<tr>
	<td>
		{{magamDate.mgMonth}}
		<input type="hidden" name="mgMonth" value="{{magamDate.mgMonth}}">
	</td>
	<td><input type="text" name="mgStartDate" id="mgStartDate" class="searchInput" style="width: 120px;" value="{{magamDate.mgStartDate}}"/></td>
	<td><input type="text" name="mgEndDate" id="mgEndDate" class="searchInput" style="width: 120px;" value="{{magamDate.mgEndDate}}"/></td>
	<td><input type="text" name="mgEndYMD" id="mgEndYMD" class="searchInput" style="width: 120px;" value="{{magamDate.mgEndYMD}}"/></td>
</tr>
</script>
<script id="magamDateEmptyDivTemplate" type="text/x-handlebars-template">
<tr>
	<td><input type="text" name="mgMonth" id="mgMonth" class="searchInput" style="width: 120px;" value=""/></td>
	<td><input type="text" name="mgStartDate" id="mgStartDate" class="searchInput" style="width: 120px;" value=""/></td>
	<td><input type="text" name="mgEndDate" id="mgEndDate" class="searchInput" style="width: 120px;" value=""/></td>
	<td><input type="text" name="mgEndYMD" id="mgEndYMD" class="searchInput" style="width: 120px;" value=""/></td>
</tr>
</script>
<#include "/include/popupfooter.ftl">
