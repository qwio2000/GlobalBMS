<form name="menuContentFrm" id="menuContentFrm">
<table cellspacing="0" cellpadding="0" border="1" width="100%">
	<tr>
		<th colspan="2">메뉴등록</th>
	</tr>
	<tr>
		<td>상위메뉴</td>
		<td><select id="mParentIdx" name="mParentIdx">
		<#list menuList as menu>
			<option value="${menu.MIdx}">
				<#list 1..menu.MDepth as i>
					&nbsp;
				</#list>
				${menu.MMenuName}
			</option>
		</#list>
		
		</select></td>
	</tr>
	<tr>
		<td>메뉴번호</td>
		<td>
			<input id="mIdx" name="mIdx" type="text" readonly="readonly"> 
		</td>
	</tr>
	<tr>
		<td>메뉴명</td>
		<td>
			<input id="mMenuName" name="mMenuName" type="text"> 
		</td>
	</tr>
	<tr>
		<td>메뉴코드</td>
		<td>
			<input id="mMenuCode" name="mMenuCode" type="text" maxlength="20"> 
		</td>
	</tr>
	<tr>
		<td>지사코드</td>
		<td>
			<select  id="mJisaCD" name="mJisaCD">
				<option value="00">본사</option>
				<option value="03">북경</option>
				<option value="06">호주</option>
				<option value="07">뉴질랜드</option>
				<option value="08">홍콩</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>계층</td>
		<td>
			<select id="mEmpKeyLvCD" name="mEmpKeyLvCD">
				<option value="MA">본사</option>
				<option value="JA">지사</option>
				<option value="FA">교육원</option>
				<option value="MD">영파</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>권한등급</td>
		<td>
			<select id="mDepMngCD" name="mDepMngCD">
				<option value="A">전체관리자</option>
				<option value="F">원장</option>
				<option value="T">교사</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>사용여부</td>
		<td>
			<select id="mUseState" name="mUseState">
				<option value="1">사용</option>
				<option value="0">미사용</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>링크</td>
		<td>
			<input id="mMenuLink" name="mMenuLink" type="text" >
		</td>
	</tr>
	<tr>
		<td>AntPattern</td>
		<td>
			<input id="mAntPattern" name="mAntPattern" type="text" >
		</td>
	</tr>
	<tr>
		<td>메뉴 설명</td>
		<td>
			<textarea id="mCon" name="mCon"></textarea>
		</td>
	</tr>
</table>

<input id="mHasChildren" name="mHasChildren" type="hidden" value="0">
<input id="mDepth" name="mDepth" type="hidden" value="1">
<input id="mSort" name="mSort" type="hidden" value="1">
<input id="_method" name="_method" type="hidden">
</form>       

<div>
	<div align="right">
			<input id="insertBut" type="button" value="등록하기">
			<input id="updateBut" type="button" value="수정하기" style="display: none;">
			<input id="deleteBut" type="button" value="삭제하기" style="display: none;">
	</div>
</div>
