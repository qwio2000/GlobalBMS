<#include "/include/popupheader.ftl">
<!-- Main Content -->
	<div class="popup">
		<div class="popup_top"><h1>지사 회비 관리</h1> <a href="javascript:self.close();" class="btn_popup_close">close</a></div>
		<div class="popup_content">
			<div class="pop_jisa">
			<input type="hidden" id="popResize" name="popResize" value="N"/>
				<div class="tbl01">
					<table>
						<colgroup>
							<col width="*">
							<col width="85">
							<col width="85">
							<col width="85">
							<col width="85">
							<col width="85">
							<col width="85">
							<col width="60">
					</colgroup>
						<thead>
							<tr>
								<th rowspan="2">사업형태</th>
								<th rowspan="2">지역</th>
								<th rowspan="2">입회비</th>
								<th rowspan="2">월회비</th>
								<th colspan="4">차액월회비</th>
								<th rowspan="2">화폐단위</th>
							</tr>
							<tr>
								<th>4</th>
								<th>3</th>
								<th>2</th>
								<th>1</th>
							</tr>
						</thead>
						<tbody id="mainContent">
						</tbody>
					</table>
				</div>
			</div>
			<div class="pop_jisa">
			<div id="divData" class="tbl01" style="display: none;">
			<form id="tuitionForm">
			<input type="hidden" id="jisaCD" name="jisaCD" value="${jisaCD?default('') }"/>
				<table>
					<colgroup>
							<col width="*">
							<col width="85">
							<col width="85">
							<col width="85">
							<col width="85">
							<col width="85">
							<col width="85">
							<col width="60">
					</colgroup>
					<thead>
						<tr>
							<th rowspan="2">사업형태</th>
							<th rowspan="2">지역</th>
							<th rowspan="2">입회비</th>
							<th rowspan="2">월회비</th>
							<th colspan="4">차액월회비</th>
							<th rowspan="2">화폐단위</th>
						</tr>
						<tr>
							<th>4</th>
							<th>3</th>
							<th>2</th>
							<th>1</th>
						</tr>
					</thead>
					<tbody id="divContent">
					</tbody>
				</table>
				<div class="btnArea" style="text-align: center;">
					<a id="editBtn" style="cursor: pointer;"><span>수정</span></a>
					<a href="javascript:cancel();"><span>취소</span></a>
				</div>
			</form>
			</div>
			</div>
		</div>
	</div>
<!--// Main Content -->
<script id="jisaManageTemplate" type="text/x-handlebars-template">
	{{#each tuitionInfos}}
		<tr>
			<td>
				{{#xIf deptType "==" "FF"}}가맹
				{{else}}{{#xIf deptType "==" "JL"}}라이선스
				{{else}}{{#xIf deptType "==" "JT"}}방문교사
				{{else}}통신관리
				{{/xIf}}{{/xIf}}{{/xIf}}
			</td>
			<td>
				<a href="javascript:editTuition('{{deptType}}','{{feeType}}')" class="blue">
					{{#xIf feeType "==" "HK"}}홍콩
					{{else}}{{#xIf feeType "==" "SG"}}싱가포르
					{{else}}광동성
					{{/xIf}}{{/xIf}}
				</a>
			</td>
			<td>{{registFee}}</td>
			<td>{{monthFee}}</td>
			<td>{{sectionFee4}}</td>
			<td>{{sectionFee3}}</td>
			<td>{{sectionFee2}}</td>
			<td>{{sectionFee1}}</td>
			<td>{{feeUnit}}</td>
		</tr>
	{{/each}}
</script>
<script id="jisaManageDivTemplate" type="text/x-handlebars-template">
<tr>
	<td>
		{{#xIf tuitionInfo.deptType "==" "FF"}}가맹
		{{else}}{{#xIf tuitionInfo.deptType "==" "JL"}}라이선스
		{{else}}{{#xIf tuitionInfo.deptType "==" "JT"}}방문교사
		{{else}}통신관리
		{{/xIf}}{{/xIf}}{{/xIf}}
		<input type="hidden" name="deptType" value="{{tuitionInfo.deptType}}"/>
	</td>
	<td>
		{{#xIf tuitionInfo.feeType "==" "HK"}}홍콩
		{{else}}{{#xIf tuitionInfo.feeType "==" "SG"}}싱가포르
		{{else}}광동성
		{{/xIf}}{{/xIf}}
		<input type="hidden" name="feeType" value="{{tuitionInfo.feeType}}"/>
	</td>
	<td><input type="text" name="registFee" id="registFee" class="searchInput" style="width: 50px;" value="{{tuitionInfo.registFee}}"/></td>
	<td><input type="text" name="monthFee" id="monthFee" class="searchInput" style="width: 50px;" value="{{tuitionInfo.monthFee}}"/></td>
	<td><input type="text" name="sectionFee4" id="sectionFee4" class="searchInput" style="width: 50px;" value="{{tuitionInfo.sectionFee4}}"/></td>
	<td><input type="text" name="sectionFee3" id="sectionFee3" class="searchInput" style="width: 50px;" value="{{tuitionInfo.sectionFee3}}"/></td>
	<td><input type="text" name="sectionFee2" id="sectionFee2" class="searchInput" style="width: 50px;" value="{{tuitionInfo.sectionFee2}}"/></td>
	<td><input type="text" name="sectionFee1" id="sectionFee1" class="searchInput" style="width: 50px;" value="{{tuitionInfo.sectionFee1}}"/></td>
	<td>
		<select name="feeUnit" id="feeUnit">
			{{#each feeUnits}}
			<option value="{{dtlCD}}" {{#xIf dtlCD "==" ../tuitionInfo.feeUnit }}selected{{/xIf}}>{{dtlCDNM}}</option>
			{{/each}}
		</select>
	</td>
</tr>
</script>
<#include "/include/popupfooter.ftl">
