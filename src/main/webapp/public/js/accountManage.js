$(function(){	
	$.extend({
		getMemberSearch:function(){
			var pageNum		= $("#pageNum").val();
			var searchUrl 	= "/ma/manage/users/"+pageNum;
			$.ajax({
				url:searchUrl,
				type:"GET",
				cache: false,
				dataType: "json",
				success: function(jsonData, textStatus, XMLHttpRequest) {
					var pageInfo = jsonData.pageInfo;
					var totalRowCnt = pageInfo.totalRowCnt;
					var pageNum = pageInfo.pageNum;
					var pageSize = pageInfo.rowBlockSize;
					if(pageInfo.totalPageCnt > 1){
					$("#pageNavi").html($.pageUtil(pageInfo.pageNum,pageInfo.totalPageCnt, 
							pageInfo.pageBlockSize,pageInfo.startPageNum,pageInfo.endPageNum));
					}
					var source = $("#AccountManageTemplate").html();
					var template = Handlebars.compile(source);
					Handlebars.registerHelper("inc", function(value, options){
						return (pageNum - 1) * pageSize + parseInt(value) + 1;
					});
					Handlebars.registerHelper('xIf', function (lvalue, operator, rvalue, options) {
					    var operators, result;
					    if (arguments.length < 3) {
					        throw new Error("Handlerbars Helper 'compare' needs 2 parameters");
					    }
					    if (options === undefined) {
					        options = rvalue;
					        rvalue = operator;
					        operator = "===";
					    }
					    operators = {
					        '==': function (l, r) { return l == r; },
					        '===': function (l, r) { return l === r; },
					        '!=': function (l, r) { return l != r; },
					        '!==': function (l, r) { return l !== r; },
					        '<': function (l, r) { return l < r; },
					        '>': function (l, r) { return l > r; },
					        '<=': function (l, r) { return l <= r; },
					        '>=': function (l, r) { return l >= r; },
					        'typeof': function (l, r) { return typeof l == r; }
					    };
					    if (!operators[operator]) {
					        throw new Error("'xIf' doesn't know the operator " + operator);
					    }
					    result = operators[operator](lvalue, rvalue);
					    if (result) {
					        return options.fn(this);
					    } else {
					        return options.inverse(this);
					    }
					});					
					$("#mainContent").empty();
					$("#mainContent").append(template(jsonData));
				},
				error:function (xhr, ajaxOptions, thrownError){	
					alert(thrownError);
				}
			});
		},
		// User ID 중복체크
		userIdChk:function(){
			if(!($.required("userId","User ID"))){
				$("#userId").focus();
				return;
			}
			if($("#userId").val().length < 5){
				alert("User ID를 5자리 이상으로 입력해 주세요.");
				$("#userId").focus();
				return;
			}			
			var pattern = /(^([a-z0-9]+)([a-z0-9_]+$))/;
			if(!pattern.test($.trim($("#userId").val()))){
			    alert("User ID 형식이 아닙니다.\n\n영소문자, 숫자, _ 만 가능.\n\n첫글자는 영소문자, 숫자만 가능\n");
			    return;
			}
			var userId = $("#userId").val();
			var param = {"userId":userId};
			$.ajax({
				url:"/ma/manage/idcheck",
				type:"GET",
				cache: false,
				async: true,
				data: param,
				dataType: "text",
				success: function(jsonData, textStatus, XMLHttpRequest) {
					if(jsonData == "Y"){
						alert("사용 가능한 ID입니다.");
						$("#isIdUseOk").val('Y');
						$("#userIdConf").val(userId);
					} else{
						alert("사용할 수 없는 ID입니다.");
						$("#userId").val('');
						$("#isIdUseOk").val('N');	
						$("#userId").focus();
						$("#userIdConf").val('');
					}	
				},
				error:function (xhr, ajaxOptions, thrownError){	
					alert(_THROWNERROR);
				}
			});		

		},
		// User 비밀번호 초기화
		userPwdClear:function(userId){
			if(confirm("비밀번호를 초기화 하시겠습니까?\n\n초기화 하시면 비밀번호는 User ID 로 초기화 됩니다.")) {
				$.ajax({
					url:"/ma/manage/users/clearPwdJson",
					type:"POST",
					cache: false,
					async: true,
					data: {"userId": $("#beforeId").val()},
					dataType: "text",
					success: function(jsonData, textStatus, XMLHttpRequest) {
						alert(jsonData);
						self.location.reload();
					},
					error:function (xhr, ajaxOptions, thrownError){	
						alert(_THROWNERROR);
					}
				});			
			}
		}
	});
	if(window.location.pathname == '/ma/manage/users')
		$.getMemberSearch();
	
	$(".paging").on("click","a.naviPage",function() {
		var pageNum = $(this).attr('pageNo');	
		$('#pageNum').val(pageNum);
		$.getMemberSearch();
	});	
	
	//User 등록
	$("#saveUserInfo").on("click", function() {
		if(!($.required("userLevel","User Level"))){return;}		
		if(!($.required("userFstName","User's First Name"))){return;}
		if(!($.required("userLstName","User's Last Name"))){return;}
		if(!($.required("phone","Phone"))){return;}
		
		if($.trim($("#email").val()) != ''){
			if(!$.emailCheck("email")){
				$("#email").focus();
				return;
			}
		}
		if(!($.required("userId", "User ID"))){return;}
		if(!($("#userIdConf").val() == $("#userId").val() && $("#isIdUseOk").val()=='Y')){
			alert("ID 중복체크를 해 주십시요.");
			return;
		}	
		// 비밀번호 체크
		if(!($.required("userPasswd","Password"))){return;}
		if(!($.required("retypeUserPasswd","Retype Password"))){return;}
		if(!$.passwordCheck("userPasswd","Password")){
			$("#userPasswd").focus();
			return;
		}
		if($.trim($("#userPasswd").val()) != $.trim($("#retypeUserPasswd").val())){
			alert("Confirm Retype Password!!");
			$("#retypeUserPasswd").focus();
			return;
		}				
		var param = $("#userForm").serialize();
		console.log(param);
		$.ajax({
			url:"/ma/manage/users",
			type:"POST",
			cache: false,
			async: true,
			data: param,
			dataType: "text",
			success: function(jsonData, textStatus, XMLHttpRequest) {
				alert(jsonData);
				self.close();
				opener.location.reload();
			},
			error:function (xhr, ajaxOptions, thrownError){	
				alert(_THROWNERROR);
			}
		});
		
	});	
	//User 수정
	$("#updateUserInfo").on("click", function() {
		if(!($.required("userLevel","User Level"))){return;}		
		if(!($.required("userFstName","User's First Name"))){return;}
		if(!($.required("userLstName","User's Last Name"))){return;}
		if(!($.required("phone","Phone"))){return;}
		
		if($.trim($("#email").val()) != ''){
			if(!$.emailCheck("email")){
				$("#email").focus();
				return;
			}
		}
		var beforePasswd = $("#beforePasswd").val();
		var isChangePwd = "N";
		if(beforePasswd != $("#userPasswd").val()){
			if(!($.required("userPasswd","Password"))){return;}
			if(!($.required("retypeUserPasswd","Retype Password"))){return;}
			if(!$.passwordCheck("userPasswd","Password")){
				$("#userPasswd").focus();
				return;
			}
			if($.trim($("#userPasswd").val()) != $.trim($("#retypeUserPasswd").val())){
				alert("Confirm Retype Password!!");
				$("#retypeUserPasswd").focus();
				return;
			}				
			isChangePwd = "Y";
		}
		$("#isChangePwd").val(isChangePwd);
		var param = $("#userForm").serialize();
		console.log(param);
		$.ajax({
			url:"/ma/manage/users/edit",
			type:"POST",
			cache: false,
			async: true,
			data: param,
			dataType: "text",
			success: function(jsonData, textStatus, XMLHttpRequest) {
				alert(jsonData);
				self.close();
				opener.location.reload();
			},
			error:function (xhr, ajaxOptions, thrownError){	
				alert(_THROWNERROR);
			}
		});
		
	});	
});

function addUserPop(){
	$.openPop("/ma/manage/users/new", "userRegist", "menubar=no,toolbar=no,status=no,resizable=yes,scrollbars=yes,width=650,height=700");
}
function editUserPop(userId){
	$.openPop("/ma/manage/users/edit?userId="+userId, "userRegist", "menubar=no,toolbar=no,status=no,resizable=yes,scrollbars=yes,width=650,height=700");
}

function deleteUser(userId){
	if(confirm(userId + ' 계정을 정말 삭제 하시겠습니까?')){
		$.ajax({
			url:"/ma/manage/users/delete",
			type:"POST",
			cache: true,
			data: {"userId":userId},
			dataType: "text",
			success: function(result, textStatus, XMLHttpRequest) {
				alert(result);
				$.getMemberSearch();
			},
			error:function (xhr, ajaxOptions, thrownError){	
				alert(_THROWNERROR);
			}
		});
	}
}
