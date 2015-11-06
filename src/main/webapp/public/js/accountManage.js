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
					} else{
						alert("사용할 수 없는 ID입니다.");
						$("#userId").val('');
						$("#isIdUseOk").val('N');	
						$("#userId").focus();
					}	
				},
				error:function (xhr, ajaxOptions, thrownError){	
					alert(_THROWNERROR);
				}
			});		

		}
	});
	if(window.location.pathname == '/ma/manage/users')
		$.getMemberSearch();
	
	$(".paging").on("click","a.naviPage",function() {
		var pageNum = $(this).attr('pageNo');	
		$('#pageNum').val(pageNum);
		$.getMemberSearch();
	});	
});

function addUserPop(){
	$.openPop("/ma/manage/users/new", "userRegist", "menubar=no,toolbar=no,status=no,resizable=yes,scrollbars=yes,width=650,height=650");
}
