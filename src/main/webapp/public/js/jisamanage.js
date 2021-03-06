$(function(){	
	$.extend({
		getSubjInfos:function(){
			var pageNum = $("#pageNum").val();
			var jisaCD = $("#jisaCD").val();
			var searchUrl = "/ma/jisamanage/subj/"+jisaCD+"/"+pageNum;
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
					var source = $("#jisaManageTemplate").html();
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
		// 지사 상세 정보
		goJisaView:function(jisaCD ){
			location.href = "/ma/jisamanage/jisaView?jisaCD="+jisaCD;
		},
		// 상품 셋팅/변경
		openSetSubjPreference:function(jisaCD){
			$.openPop("/ma/jisamanage/subj/"+jisaCD, "jisaManage", "menubar=no,toolbar=no,status=no,resizable=yes,scrollbars=yes,width=900,height=500");
		},
		// 회비 셋팅/변경
		openSetTuitionMatrix:function(jisaCD){
			$.openPop("/ma/jisamanage/tuition/"+jisaCD, "jisaManage", "menubar=no,toolbar=no,status=no,resizable=yes,scrollbars=yes,width=900,height=570");
		}		
	
			
	});
	if(window.location.pathname.indexOf('ma/jisamanage/subj') > 0){
		$.getSubjInfos();
	}
	
	$(".paging").on("click","a.naviPage",function() {
		var pageNum = $(this).attr('pageNo');	
		$('#pageNum').val(pageNum);
		$.getSubjInfos();
	});	
	$("#hiddenPicker").datepicker({
		changeMonth: true,
		changeYear: true,
		yearRange: '1950:2015',
		dateFormat: 'yy-mm-dd',
		onSelect: function(dataText, inst){
			$("#stopDate").val(dataText);
		}
	});
	$("body").on('focus', "#stopDate", function(){
		if($('#chk').val() != '1'){
			$(this).datepicker({
				changeMonth: true,
				changeYear: true,
				yearRange: '1950:2015',
				dateFormat: 'yy-mm-dd',
				onSelect: function(dataText, inst){
					var today = new Date();
					var dataSplit = dataText.split("-");
					var yy = dataSplit[0];
					var mm = dataSplit[1];
					var dd = dataSplit[2];
					var selectDay = new Date(yy, mm-1, dd);
					if(today > selectDay){
						alert('판매 중지일이 오늘 날짜보다 작을 수는 없습니다');
						$('#stopDate').val('');
						return;
					}
					$("#stopDate").val(dataText);
				}
			});
		}
	});
	$("body").on('focus', "#startDate", function(){
		if($('#chk').val() == '3'){
			$(this).datepicker({
				changeMonth: true,
				changeYear: true,
				yearRange: '1950:2015',
				dateFormat: 'yy-mm-dd'
			});
		}
	});
	
	$("#delBtn").on("click", function(){
		var deptCnt = $("#deptCnt").val();
		if(deptCnt > 0 && !confirm('현재 가맹점에서 운영중인 과목이 삭제됩니다. 계속하시겠습니까?')){
			return;
		}
		var param = {"jisaCD": $("#jisaCD").val(), "subj":$('#beforeSubj').val(), "deptCnt":deptCnt};
		console.log(param);
		$.ajax({
			url:"/ma/jisamanage/subj/delete",
			type:"POST",
			cache: false,
			dataType: "text",
			data: param,
			success: function(jsonData, textStatus, XMLHttpRequest) {
				alert(jsonData);
				editCancle();
				$.getSubjInfos();
			},
			error:function (xhr, ajaxOptions, thrownError){	
				alert(thrownError);
			}
		});
	});
	$("#btnName").on("click", function(){
		var chk = $('#chk').val();
		var param = $('#subjForm').serialize();
		if(chk == '3'){//최초 등록
			if(!($.required("subjName","과목 이름"))){return;}
			if(!($.required("subjShortName","과목 이름(Short Name)"))){return;}
			if(!($.required("subj","과목 코드"))){return;}
			if(!($.numeric("sortVal","정렬 순서"))){return;}
			$.ajax({
				url:"/ma/jisamanage/subj",
				type:"POST",
				cache: false,
				dataType: "text",
				data: param,
				success: function(jsonData, textStatus, XMLHttpRequest) {
					alert(jsonData);
					editCancle();
					$.getSubjInfos();
				},
				error:function (xhr, ajaxOptions, thrownError){	
					alert(thrownError);
				}
			});
		}else if(chk == '2'){//유지회원 0, 기존 회원 0 이상
			var stopDate = $('#stopDate').val();
			var deptCnt = $("#deptCnt").val();
			if(stopDate != ''){
				if(deptCnt > 0 && !confirm('현재 가맹점에서 운영중인 과목이 삭제됩니다. 계속하시겠습니까?')){
					return;
				}
			}
			param = {"subj":$('#subj').val(), "jisaCD":$('#jisaCD').val(), "stopDate":stopDate, "deptCnt":deptCnt};
			$.ajax({
				url:"/ma/jisamanage/subj/updatestopdate",
				type:"POST",
				cache: false,
				dataType: "text",
				data: param,
				success: function(jsonData, textStatus, XMLHttpRequest) {
					alert(jsonData);
					editCancle();
					$.getSubjInfos();
				},
				error:function (xhr, ajaxOptions, thrownError){	
					alert(thrownError);
				}
			});
		}else if(chk == '0'){//유지 회원 0, 기존 회원 0
			var stopDate = $('#stopDate').val();
			if(stopDate != ''){
				if($("#deptCnt").val() > 0 && !confirm('현재 가맹점에서 운영중인 과목이 삭제됩니다. 계속하시겠습니까?')){
					return;
				}
			}
			$.ajax({
				url:"/ma/jisamanage/subj/update",
				type:"POST",
				cache: false,
				dataType: "text",
				data: param,
				success: function(jsonData, textStatus, XMLHttpRequest) {
					alert(jsonData);
					editCancle();
					$.getSubjInfos();
				},
				error:function (xhr, ajaxOptions, thrownError){	
					alert(thrownError);
				}
			});
			
		}
	});
});

function editSubj(subj){
	var param = {"jisaCD":$('#jisaCD').val()};
	$.ajax({
		url:"/ma/jisamanage/subj/"+subj,
		type:"GET",
		cache: false,
		dataType: "json",
		data: param,
		success: function(jsonData, textStatus, XMLHttpRequest) {
			var message = "";
			$("#beforeSubj").val(subj);
			if(jsonData.subjInfo.chk == "1"){
				message = "해당 과목이 유지인 회원이 존재 : <strong>수정 불가</strong>";
				if(jsonData.subjInfo.deptCnt > 0){
					message += "<br/>현재 이 과목을 운영중인 가맹점이 존재합니다.";
				}
				$('#caption').html(message);
				$('#caption').show();
				$('#btnName').hide();
				$('#delBtn').hide();
			}else if(jsonData.subjInfo.chk == "2"){
				message = "유지는 없지만 판매한 이력이 있는 상품 : <strong>판매중지일만 수정 가능</strong>";
				if(jsonData.subjInfo.deptCnt > 0){
					message += "<br/>현재 이 과목을 운영중인 가맹점이 존재합니다. <br/>판매 중지일 변경 시 이 과목을 운영중인 가맹점에서 해당 과목이 <strong>삭제</strong> 처리됩니다.";
				}
				$('#caption').html(message);
				$('#caption').show();
				$('#btnName > span').html('수정');
				$('#btnName').show();
				$('#delBtn').hide();
			}else{
				if(jsonData.subjInfo.deptCnt > 0){
					message = "현재 이 과목을 운영중인 가맹점이 존재합니다. <br/>판매 중지일 변경 시 이 과목을 운영중인 가맹점에서 해당 과목이 <strong>삭제</strong> 처리됩니다.";
					$('#caption').html(message);
					$('#caption').show();
				}else{
					$('#caption').hide();
				}
				$('#btnName > span').html('수정');
				$('#btnName').show();
				$('#delBtn').show();
			}
			var source = $("#jisaManageDivTemplate").html();
			var template = Handlebars.compile(source);
			$("#divContent").empty();
			$("#divContent").append(template(jsonData));
		},
		error:function (xhr, ajaxOptions, thrownError){	
			alert(thrownError);
		}
	});
	$('#divData').css("display", "block");
	resizePop('L');
	
}

function editCancle(){
	$('#divData').css("display", "none");
	resizePop('S');
	$("#caption").hide();
}
//Popup ReSize
function resizePop(arg) {
	var thisY = window.outerHeight;
	var thisX = window.outerWidth;
	var popResize = $('#popResize').val();
	if (arg == "L" && popResize == "N") {
		thisY = thisY + 300;
		$("#popResize").val("Y");
		window.resizeTo(thisX, thisY);
	} else if(arg == "S" && popResize == "Y"){	
		thisY = thisY - 300;
		$("#popResize").val("N");
		window.resizeTo(thisX, thisY);
	}
};
function addNewSubj(){
	$('#btnName > span').html('등록');
	$('#btnName').show();
	$('#caption').hide();
	$('#delBtn').hide();
	var source = $("#jisaManageEmptyDivTemplate").html();
	var template = Handlebars.compile(source);
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
	$("#divContent").empty();
	$("#divContent").append(template());
	$('#divData').css("display", "block");
	resizePop('L');
}
