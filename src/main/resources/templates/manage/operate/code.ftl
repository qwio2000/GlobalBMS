<#include "/include/popupheader.ftl">
<!-- Main Content -->
	<div class="popup">
		<div class="popup_top"><h1>코드 관리</h1> <a href="javascript:self.close();" class="btn_popup_close">close</a></div>
		<div class="popup_content">
			<div class="pop_jisa">
				<div class="list02 pt20 clearfix">
					<div class="float_l">
					<input type="hidden" id="pageNum" name="pageNum" value="1"/>
						<select name="jisaCD" id="jisaCD" style="width:162px">
							<option value="">지사선택</option>
							<#list jisaCDs as jisaCD>
								<option value="${jisaCD.dtlCD }">${jisaCD.dtlCDNMK }</option>
							</#list>
						</select>
						<select name="mstCD" id="mstCD" style="width:162px">
							<option value="">코드선택</option>
							<#list codeMsts as codeMst>
								<option value="${codeMst.mstCD }">${codeMst.mstCDName }</option>
							</#list>
						</select>
						<span class="btnArea" style="margin-top: 0px;"><a id="goBtn" style="cursor: pointer;"><span style="width:70px">Go</span></a></span>
					</div>
				</div>
				<div id="listContent">
				</div>
			</div>
		</div>
	</div>
<!--// Main Content -->
<script id="codeListTemplate" type="text/x-handlebars-template">
<div class="float_r">
	<div class="btnArea_txt p0"><a href="javascript:addNewCodeDtl()" class="btn_doc m0">추가</a></div>
</div>
<br/>
<div class="tbl01">
	<table>
		<colgroup>
			<col width="70">
			<col width="150">
			<col width="150">
			<col width="150">
			<col width="150">
			<col width="50">
			<col width="80">
		</colgroup>
		<thead>
			<tr>
				<th>코드번호</th>
				<th>내 용 1</th>
				<th>내 용 2</th>
				<th>내 용 3</th>
				<th>내 용 4</th>
				<th>정렬</th>
				<th>사용여부</th>
			</tr>
		</thead>
		<tbody>
		{{#each codeDtls}}
			<tr>
				<td><a href="/ma/manage/operate/code/edit?mstCD={{mstCD}}&dtlCD={{dtlCD}}&jisaCD={{jisaCD}}" class="blue">{{dtlCD }}</a></td>
				<td class="left">{{dtlCDNM }}</td>
				<td class="left">{{dtlCDNMK}}</td>
				<td class="left">{{dtlCDNME }}</td>
				<td class="left">{{dtlCDNMC }}</td>
				<td>{{sortVal1 }}</td>
				<td>{{#xIf useYN "==" "Y"}}사용가능
					{{else}}사용안함
					{{/xIf}}
				</td>
			</tr>
		{{/each}}
		</tbody>
	</table>
</div>
<div class="paging">
	<span id="pageNavi"></span>
</div>
</script>
<#include "/include/popupfooter.ftl">
