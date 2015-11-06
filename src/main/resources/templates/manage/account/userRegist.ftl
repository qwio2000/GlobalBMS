<#include "/include/popupheader.ftl">
<!-- Main Content -->
<div class="popup">
	<div class="popup_top"><h1>계정 등록</h1> <a href="javascript:self.close();" class="btn_popup_close">close</a></div>
	<div class="popup_content">
		<div class="pop_jisa">
			<form action="" name="userForm" id="userForm">
			<ul class="list02">
				<li>
					<label for="userId" class="tit">User ID</label>
					<input type="text" name="userId" id="userId" class="searchInput" style="width:175px"  maxlength='10' > <a href="javascript:;" onClick="$.userIdChk();" >ID중복체크</a>
					<input type="hidden" name="userIdConf" id="userIdConf" value=""/> 
					<input type="hidden" name="isIdUseOk" id="isIdUseOk" value="F"/> 
				</li>				
				<li>
					<label for="userPasswd" class="tit">Password <span class="must">*</span></label>
					<input type="password" name="userPasswd" id="userPasswd" class="searchInput" style="width:384px"  maxlength='20' >
				</li>
				<li>
					<label for="retypeUserPasswd" class="tit">Retype Password <span class="must">*</span></label>
					<input type="password" name="retypeUserPasswd" id="retypeUserPasswd" class="searchInput" style="width:384px"  maxlength='20' >
				</li>				
				<li>
					<label for="userLevel" class="tit">User Privilege <span class="must">*</span></label>
					<select name="userLevel" id="userLevel" style="width:187px;margin-right:3px">
						<option value="">-선택-</option>
						<option value="U0">관리자</option>
						<option value="U1">스태프</option>		
					</select>
				</li>
				<li>
					<label for="userFstName" class="tit">User's First Name <span class="must">*</span></label>
					<input type="text" name="userFstName" id="userFstName" class="searchInput" style="width:384px"  maxlength='20' >
				</li>
				<li>
					<label for="userLstName" class="tit">User's Last Name <span class="must">*</span></label>
					<input type="text" name="userLstName" id="userLstName"class="searchInput" style="width:384px"  maxlength='30' >
				</li>
				<li>
					<label for="email" class="tit">Email Address</label>
					<input type="text" name="email" id="email" class="searchInput" style="width:384px"  maxlength='100' >
				</li>
				<li>
					<label for="phone" class="tit">Phone <span class="must">*</span></label>
					<input type="text" name="phone" id="phone" class="searchInput" style="width:384px"  maxlength='15' >
				</li>
				<li>
					<label for="title" class="tit">Title</label>
					<input type="text" name="title" id="title" class="searchInput" style="width:384px"  maxlength='50' >
				</li>
				<li>
					<label for="department" class="tit">Department</label>
					<input type="text" name="departMent" id="departMent" class="searchInput" style="width:384px"  maxlength='50' >
				</li>
			</ul>
			<div class="btnArea">
				<a id="saveUserInfo" style="cursor: pointer;" ><span>Save User Information</span></a>
			</div>
			</form>
		</div>
	</div>
</div>
<!--// Main Content -->
<#include "/include/popupfooter.ftl">