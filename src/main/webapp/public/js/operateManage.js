$(function(){	
	$.extend({
		getMagamDates:function(){
			var pageNum = $("#pageNum").val();
			var searchUrl = "/ma/manage/operate/closingDate/"+pageNum;
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
					var source = $("#magamDatesTemplate").html();
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
		}
	});
	if(window.location.pathname == '/ma/manage/operate/closingDate'){
		$.getMagamDates();
	}
	$(".paging").on("click","a.naviPage",function() {
		var pageNum = $(this).attr('pageNo');	
		$('#pageNum').val(pageNum);
		$.getMagamDates();
	});	
	
	$("body").on('focus', "#mgStartDate", function(){
		$(this).datepicker({
			changeMonth: true,
			changeYear: true,
			yearRange: "-20:+20",
			dateFormat: 'yy-mm-dd'
		});
	});
	
	$("body").on('focus', "#mgEndDate", function(){
		$(this).datepicker({
			changeMonth: true,
			changeYear: true,
			yearRange: "-20:+20",
			dateFormat: 'yy-mm-dd'
		});
	});
	
	$("body").on('focus', "#mgEndYMD", function(){
		$(this).datepicker({
			changeMonth: true,
			changeYear: true,
			yearRange: "-20:+20",
			dateFormat: 'yy-mm-dd'
		});
	});
	
	$("body").on('focus', "#mgMonth", function(){
		$(this).datepicker({
			changeMonth: true,
			changeYear: true,
			dateFormat : 'yy-mm',
			yearRange: "-20:+20",
		});
	});
	
	$("#btnName").on("click", function(){
		if(!($.required("mgStartDate","마감 시작일"))){return;}
		if(!($.required("mgEndDate","마감 종료일"))){return;}
		if(!($.required("mgEndYMD","마감월말일자"))){return;}
		var param = $('#magamForm').serialize();
		if(typeof($('#mgMonth').val()) == "undefined"){//수정
			$.ajax({
				url:"/ma/manage/operate/closingDate/edit",
				type:"POST",
				cache: false,
				dataType: "text",
				data: param,
				success: function(jsonData, textStatus, XMLHttpRequest) {
					alert(jsonData);
					editCancle();
					$.getMagamDates();
				},
				error:function (xhr, ajaxOptions, thrownError){	
					alert(thrownError);
				}
			});
		}else{//등록
			if(!($.required("mgMonth","마감년월"))){return;}
			$.ajax({
				url:"/ma/manage/operate/closingDate",
				type:"POST",
				cache: false,
				dataType: "text",
				data: param,
				success: function(jsonData, textStatus, XMLHttpRequest) {
					alert(jsonData);
					editCancle();
					$.getMagamDates();
				},
				error:function (xhr, ajaxOptions, thrownError){	
					alert(thrownError);
				}
			});
		}
	});
});

function openMagamDatePop(){
	$.openPop("/ma/manage/operate/closingDate", "operate", "menubar=no,toolbar=no,status=no,resizable=yes,scrollbars=yes,width=900,height=700");
}

function editMagamDate(mgMonth){
	var mgMonthDate = new Date(mgMonth);
	var today = new Date();
	var thisYYMM = new Date(today.getFullYear().toString()+"-"+(today.getMonth()+1).toString())
	if(thisYYMM > mgMonthDate){
		alert('이미 지나간 마감년월은 수정할 수 없습니다.\n당월부터 수정 가능합니다.');
		return;
	}
	var param = {"mgMonth":mgMonth};
	$.ajax({
		url:"/ma/manage/operate/closingDate/"+mgMonth,
		type:"GET",
		cache: false,
		dataType: "json",
		data: param,
		success: function(jsonData, textStatus, XMLHttpRequest) {
			$('#btnName > span').html('수정');
			var source = $("#magamDateDivTemplate").html();
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
//Popup ReSize
function resizePop(arg) {
	var thisY = window.outerHeight;
	var thisX = window.outerWidth;
	var popResize = $('#popResize').val();
	if (arg == "L" && popResize == "N") {
		thisY = thisY + 200;
		$("#popResize").val("Y");
		window.resizeTo(thisX, thisY);
	} else if(arg == "S" && popResize == "Y"){	
		thisY = thisY - 200;
		$("#popResize").val("N");
		window.resizeTo(thisX, thisY);
	}
};

function editCancle(){
	$('#divData').css("display", "none");
	resizePop('S');
}

function addNewMagamDate(){
	$('#btnName > span').html('등록');
	var source = $("#magamDateEmptyDivTemplate").html();
	var template = Handlebars.compile(source);
	$("#divContent").empty();
	$("#divContent").append(template());
	$('#divData').css("display", "block");
	resizePop('L');
}