<#include "/include/popupheader.ftl">
<!-- Main Content -->
<div class="popup">
	<div class="popup_top"><h1>입회 가능여부 관리</h1> <a href="javascript:self.close();" class="btn_popup_close">close</a></div>
	<div class="popup_content">
		<div class="pop_jisa">
			<form action="" name="closeForm" id="closeForm">
			<ul class="list02">
				<li>
					<label for="jisaCD" class="tit">지사 <span class="must">*</span></label>
					<select name="jisaCD" id="jisaCD" style="width:162px">
						<option value="">지사선택</option>
						<#list jisaCDs as jisaCDElement>
							<option value="${jisaCDElement.dtlCD }">${jisaCDElement.dtlCDNMK }</option>
						</#list>
					</select>
				</li>				
				<li>
					<label for="statusCD" class="tit">입회 가능 여부 <span class="must">*</span></label>
					<select name="statusCD" id="statusCD" style="width:162px">
						<option value="">상태선택</option>
						<option value="1">불가</option>
						<option value="2">가능</option>
					</select>
				</li>				
				<li>
					<label for="closeReason" class="tit">입회 불가 사유 <span class="must">*</span></label>
					<input type="text" name="closeReason" id="closeReason" class="searchInput" style="width:384px"  maxlength='300' >
				</li>
			</ul>
			<div class="btnArea">
				<a id="saveBtn" style="cursor: pointer;" ><span>Save Close Regist Information</span></a>
				<a href="javascript:self.close();"><span>Cancel</span></a>
			</div>
			</form>
		</div>
	</div>
</div>
<!--// Main Content -->
<#include "/include/popupfooter.ftl">