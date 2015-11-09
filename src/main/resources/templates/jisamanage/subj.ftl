<#include "/include/popupheader.ftl">
<!-- Main Content -->
	<div class="popup">
		<div class="popup_top"><h1>지사 과목 관리</h1> <a href="javascript:self.close();" class="btn_popup_close">close</a></div>
		<div class="popup_content">
			<div class="pop_jisa">
			<input type="hidden" id="pageNum" name="pageNum" value="1"/>
			<input type="hidden" id="popResize" name="popResize" value="N"/>
			<div class="float_r">
				<div class="btnArea_txt p0"><a href="javascript:addNewSubj()" class="btn_doc m0">과목 추가</a></div>
			</div>
			<br/>
				<div class="tbl01">
					<table>
						<colgroup>
							<col width="50">
							<col width="*">
							<col width="80">
							<col width="100">
							<col width="150">
							<col width="150">
							<col width="80">
							<col width="80">
						</colgroup>
						<thead>
							<tr>
								<th>No.</th>
								<th>과목명</th>
								<th>과목명(Short)</th>
								<th>상태</th>
								<th>판매게시일</th>
								<th>판매중지일</th>
								<th>과목코드</th>
								<th>정렬순서</th>
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
			<div id="caption" style="display: none;"></div>
			<div id="divData" class="tbl01" style="display: none;">
			<form id="subjForm">
			<input type="hidden" id="jisaCD" name="jisaCD" value="${jisaCD?default('') }"/>
				<table>
					<colgroup>
						<col width="120">
						<col width="200">
						<col width="120">
						<col width="200">
						<col width="120">
						<col width="200">
					</colgroup>
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
<script id="jisaManageTemplate" type="text/x-handlebars-template">
	{{#each subjInfos}}
		<tr>
			<td>{{inc @index }}</td>
			<td><a href="javascript:editSubj('{{subj}}')" class="blue">{{subjName }}</a></td>
			<td>{{subjShortName }}</td>
			<td>{{#xIf useYN "==" "Y"}}판매{{else}}판매불가{{/xIf}}</td>
			<td>{{startDate }}</td>
			<td>{{stopDate }}</td>
			<td>{{subj }}</td>
			<td>{{sortVal }}</td>
		</tr>
	{{/each}}
</script>
<script id="jisaManageDivTemplate" type="text/x-handlebars-template">
<tr>
	<input type="hidden" id="chk" value="{{subjInfo.chk}}">
	<td class="col_gray">과목명</td>
	<td class="left"><input type="text" id="subjName" name="subjName" class="searchInput" style="width: 140px;" value="{{subjInfo.subjName}}" {{#xIf subjInfo.chk "!=" 0}}readonly="readonly"{{/xIf}}></td>
	<td class="col_gray">상태</td>
	<td class="left">
		<select name="useYN" id="useYN" {{#xIf subjInfo.chk "!=" 0}}onFocus='this.initialSelect = this.selectedIndex;' onChange='this.selectedIndex = this.initialSelect;'{{/xIf}}>
			<option value="Y" {{#xIf subjInfo.useYN "==" "Y" }}selected{{/xIf}}>판매</option>
			<option value="N" {{#xIf subjInfo.useYN "==" "N" }}selected{{/xIf}}>중지</option>
		</select>
	</td>
	<td class="col_gray">처방</td>
	<td class="left">
		<select id="digYN" name="digYN" {{#xIf subjInfo.chk "!=" 0}}onFocus='this.initialSelect = this.selectedIndex;' onChange='this.selectedIndex = this.initialSelect;'{{/xIf}}>
			<option value="Y" {{#xIf subjInfo.digYN "==" "Y" }}selected{{/xIf}}>가능</option>
			<option value="N" {{#xIf subjInfo.digYN "==" "N" }}selected{{/xIf}}>불가</option>
		</select>
	</td>
</tr>
<tr>
	<td class="col_gray">과목명<br/>(ShortName)</td>
	<td class="left"><input type="text" id="subjShortName" name="subjShortName" class="searchInput" style="width: 140px;" value="{{subjInfo.subjShortName}}" {{#xIf subjInfo.chk "!=" 0}}readonly="readonly"{{/xIf}}></td>
	<td class="col_gray">과목코드</td>
	<td class="left"><input type="text" id="subj" name="subj" class="searchInput" style="width: 140px;" value="{{subjInfo.subj}}" {{#xIf subjInfo.chk "!=" 0}}readonly="readonly"{{/xIf}}></td>
	<td class="col_gray">정렬순서</td>
	<td class="left"><input type="text" id="sortVal" name="sortVal" class="searchInput" style="width: 140px;" value="{{subjInfo.sortVal}}" {{#xIf subjInfo.chk "!=" 0}}readonly="readonly"{{/xIf}}></td>
</tr>
<tr>
	<td class="col_gray" style="text-align: center;">판매개시일</td>
	<td class="left"><input type="text" id="startDate" name="startDate" class="searchInput" style="width: 140px;" value="{{subjInfo.startDate}}" {{#xIf subjInfo.chk "!=" 0}}readonly="readonly"{{/xIf}}></td>
	<td class="col_gray" style="text-align: center;">판매중지일</td>
	<td colspan="3" class="left"><input type="text" id="stopDate" name="stopDate" class="searchInput" style="width: 140px;" value="{{subjInfo.stopDate}}" {{#xIf subjInfo.chk "==" 1}}readonly="readonly"{{/xIf}}"><input type="hidden" id="hiddenPicker"/></td>
</tr>
</script>
<script id="jisaManageEmptyDivTemplate" type="text/x-handlebars-template">
<tr>
	<input type="hidden" id="chk" value="3">
	<td class="col_gray">과목명</td>
	<td class="left"><input type="text" id="subjName" name="subjName" class="searchInput" style="width: 140px;" value=""></td>
	<td class="col_gray">상태</td>
	<td class="left">
		<select id="useYN" name="useYN">
			<option value="Y">판매</option>
			<option value="N">중지</option>
		</select>
	</td>
	<td class="col_gray">처방</td>
	<td class="left">
		<select id="digYN" name="digYN">
			<option value="Y">가능</option>
			<option value="N">불가</option>
		</select>
	</td>
</tr>
<tr>
	<td class="col_gray">과목명<br/>(ShortName)</td>
	<td class="left"><input type="text" id="subjShortName" name="subjShortName" class="searchInput" style="width: 140px;" value="" ></td>
	<td class="col_gray">과목코드</td>
	<td class="left"><input type="text" id="subj" name="subj" class="searchInput" style="width: 140px;" value=""></td>
	<td class="col_gray">정렬순서</td>
	<td class="left"><input type="text" id="sortVal" name="sortVal" class="searchInput" style="width: 140px;" value=""></td>
</tr>
<tr>
	<td class="col_gray" style="text-align: center;">판매개시일</td>
	<td class="left"><input type="text" id="startDate" name="startDate" class="searchInput" style="width: 140px;" value="" ></td>
	<td class="col_gray" style="text-align: center;">판매중지일</td>
	<td colspan="3" class="left"><input type="text" id="stopDate" name="stopDate" class="searchInput" style="width: 140px;" value="" ></td>
</tr>
</script>
<#include "/include/popupfooter.ftl">
