<#include "/include/header.ftl">
<!-- Main Content -->
<script>
	var head = $("head");
	var headlinkLast = head.find("link[rel='stylesheet']:last");
	var linkElement = "<style>.ui-datepicker-calendar {display: none;}</style>";
	if (headlinkLast.length){
		headlinkLast.after(linkElement);
	}
	else {
	   head.append(linkElement);
	}
</script>
<div class="content">
	<h2 class="conTit">회원 조회</h2>
	<form id="memberSearchForm" action="/ma/member/search" method="get">
	<ul class="memSearch">
		<li>
			<label for="kind">회원 종류</label>
			<span class="radio_wrap"><input type="radio" value="hkForeign" name="kind" id="hkForeign" checked="checked"><label class="radio_label" for="hkForeign"> 해외(홍콩)회원</label></span>
			<span class="radio_wrap"><input type="radio" value="otherForeign" name="kind" id="otherForeign"><label class="radio_label" for="otherForeign"> 그외 해외회원</label></span>
			<span class="radio_wrap"><input type="radio" value="korean" name="kind" id="korean"><label class="radio_label" for="korean"> 한국회원</label></span>
		</li>
		<li>
			<label for="memKey">회원번호</label>
			<input id="memKey" type="text" name="memKey" class="searchInput" style="width: 334px;" maxlength="8">
		</li>
		<li>
			<label for="memName">이름</label>
			<input id="memName" type="text" name="memName" class="searchInput" style="width: 334px;" maxlength="50">
		</li>
		<li>
			<label for="birthYM">생년월</label>
			<input type="text"  class="searchInput" style="width:300px" name="birthYM" id="birthYM" value="" maxlength='7' readonly="readonly"/>
			<input type="hidden" id="hiddenPicker"/>
			<a class="btn_calendar" id="dobDatePicker" style="cursor: pointer;">view calendar</a>
		</li>
	</ul>
	<div class="btnArea">
		<a href="javascript:location.reload();"><span>초기화</span></a>
		<a id="memberSearchBtn" href="#"><span>검색</span></a>
	</div>
	</form>
</div>
<!--// Main Content -->
<#include "/include/footer.ftl">