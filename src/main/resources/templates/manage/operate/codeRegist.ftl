<#include "/include/popupheader.ftl">
<!-- Main Content -->
<div class="popup">
	<div class="popup_top"><h1>코드 등록</h1> <a href="javascript:self.close();" class="btn_popup_close">close</a></div>
	<div class="popup_content">
		<div class="pop_jisa">
			<form action="" name="codeForm" id="codeForm">
			<ul class="list02">
				<li>
					<label for="mstCD" class="tit">업무분류</label>
					<select name="mstCD" id="mstCD" style="width:162px;margin-right:3px">
						<option value="">코드선택</option>
						<#list codeMsts as codeMst>
							<option value="${codeMst.mstCD }" <#if codeMst.mstCD == mstCD>selected</#if>>${codeMst.mstCDName }</option>
						</#list>
					</select>
				</li>				
				<li>
					<label for="jisaCD" class="tit">지사</label>
					<select name="jisaCD" id="jisaCD" style="width:162px">
						<option value="">지사선택</option>
						<#list jisaCDs as jisaCDElement>
							<option value="${jisaCDElement.dtlCD }" <#if jisaCDElement.dtlCD == jisaCD>selected</#if>>${jisaCDElement.dtlCDNMK }</option>
						</#list>
					</select>
				</li>				
				<li>
					<label for="dtlCD" class="tit">상세 코드 <span class="must">*</span></label>
					<input type="text" name="dtlCD" id="dtlCD" class="searchInput" style="width:384px"  maxlength='4' >
				</li>
				<li>
					<label for="dtlCDNM" class="tit">내용 1<span class="must">*</span></label>
					<input type="text" name="dtlCDNM" id="dtlCDNM" class="searchInput" style="width:384px"  maxlength='100' >
				</li>				
				<li>
					<label for="dtlCDNMK" class="tit">내용 2</label>
					<input type="text" name="dtlCDNMK" id="dtlCDNMK" class="searchInput" style="width:384px"  maxlength='100' >
				</li>				
				<li>
					<label for="dtlCDNME" class="tit">내용 3</label>
					<input type="text" name="dtlCDNME" id="dtlCDNME" class="searchInput" style="width:384px"  maxlength='100' >
				</li>				
				<li>
					<label for="dtlCDNMC" class="tit">내용 4</label>
					<input type="text" name="dtlCDNMC" id="dtlCDNMC" class="searchInput" style="width:384px"  maxlength='100' >
				</li>				
				<li>
					<label for="sortVal1" class="tit">정렬순서 <span class="must">*</span></label>
					<input type="text" name="sortVal1" id="sortVal1" class="searchInput" style="width:384px">
				</li>
				<li>
					<label for="useYN" class="tit">사용여부</label>
					<span class="radio_wrap"><input type="radio" value="1" name="useYN" id="Active" checked><label class="radio_label" for="Active"> 사용가능</label></span>
					<span class="radio_wrap"><input type="radio" value="0" name="useYN" id="Inactive"><label class="radio_label" for="Inactive"> 사용불가</label></span>
				</li>
			</ul>
			<div class="btnArea">
				<a id="saveUserInfo" style="cursor: pointer;" ><span>Save Code Information</span></a>
			</div>
			</form>
		</div>
	</div>
</div>
<!--// Main Content -->
<#include "/include/popupfooter.ftl">