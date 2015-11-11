<#include "/include/popupheader.ftl">
<!-- Main Content -->
<div class="popup">
	<div class="popup_top"><h1>계정 수정</h1> <a href="javascript:self.close();" class="btn_popup_close">close</a></div>
	<div class="popup_content">
		<div class="pop_jisa">
			<form action="" name="userForm" id="userForm">
			<input type="hidden" name="userId" id="userId" value="${user.userId?default('') }"/>
			<ul class="list02">
				<li>
					<label for="userId" class="tit">User ID</label>
					<input type="text" class="searchInput" style="width:384px" readonly="readonly" maxlength='10' value="${user.userId?default('') }"> 
				</li>				
				<li>
					<label for="userPasswd" class="tit">Password <span class="must">*</span></label>
					<strong><a href="javascript:$.userPwdClear('');" class="">[Clear] </a></strong>
				</li>
				<li>
					<label for="userLevel" class="tit">User Duty <span class="must">*</span></label>
					<select name="userLevel" id="userLevel" style="width:187px;margin-right:3px">
						<option value="">-선택-</option>
						<option value="U0" <#if user.userLevel == 'U0'>selected</#if>>관리자</option>
						<option value="U1" <#if user.userLevel == 'U1'>selected</#if>>스태프</option>		
					</select>
				</li>
				<li>
					<label for="userFstName" class="tit">User's First Name <span class="must">*</span></label>
					<input type="text" name="userFstName" id="userFstName" class="searchInput" style="width:384px"  maxlength='20' value="${user.userFstName?default('') }">
				</li>
				<li>
					<label for="userLstName" class="tit">User's Last Name <span class="must">*</span></label>
					<input type="text" name="userLstName" id="userLstName"class="searchInput" style="width:384px"  maxlength='30' value="${user.userLstName?default('') }">
				</li>
				<li>
					<label for="email" class="tit">Email Address</label>
					<input type="text" name="email" id="email" class="searchInput" style="width:384px"  maxlength='100' value="${user.email?default('') }">
				</li>
				<li>
					<label for="phone" class="tit">Phone <span class="must">*</span></label>
					<input type="text" name="phone" id="phone" class="searchInput" style="width:384px"  maxlength='15' value="${user.phone?default('') }">
				</li>
				<li>
					<label for="title" class="tit">Title</label>
					<input type="text" name="title" id="title" class="searchInput" style="width:384px"  maxlength='50' value="${user.title?default('') }">
				</li>
				<li>
					<label for="department" class="tit">Department</label>
					<input type="text" name="departMent" id="departMent" class="searchInput" style="width:384px"  maxlength='50' value="${user.departMent?default('') }">
				</li>
				<li>
					<label for="statusCD" class="tit">Status</label>
					<span class="radio_wrap"><input type="radio" value="1" name="statusCD" id="Active" <#if user.statusCD?default('') == "1">checked</#if> ><label class="radio_label" for="Active"> 사용가능</label></span>
					<span class="radio_wrap"><input type="radio" value="0" name="statusCD" id="Inactive" <#if user.statusCD?default('') == "0">checked</#if> ><label class="radio_label" for="Inactive"> 사용불가</label></span>
				</li>
			</ul>
			<div class="btnArea">
				<a id="updateUserInfo" style="cursor: pointer;" ><span>Update User Information</span></a>
			</div>
			</form>
		</div>
	</div>
</div>
<!--// Main Content -->
<#include "/include/popupfooter.ftl">