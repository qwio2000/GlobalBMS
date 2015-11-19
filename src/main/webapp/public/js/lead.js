$(function(){	
	$.extend({
		getLeadSearch:function(){
			var pageNum = $("#pageNum").val();
			var searchUrl = "/ma/leads/"+pageNum;
			var contactName = $("#contactName").val();
			var statusCD = $("#statusCD").val();
			var orderBy = $("#orderBy").val();
			var ord = $("#ord").val();
			var paramData = {"contactName":contactName,"statusCD":statusCD,"orderBy":orderBy,"ord":ord};
			console.log(paramData);
			$.ajax({
				url:searchUrl,
				type:"GET",
				cache: false,
				data: paramData,
				dataType: "json",
				success: function(jsonData, textStatus, XMLHttpRequest) {
					console.log(jsonData);
					var pageInfo = jsonData.pageInfo;
					var totalRowCnt = pageInfo.totalRowCnt;
					var pageNum = pageInfo.pageNum;
					var pageSize = pageInfo.rowBlockSize;
					if(pageInfo.totalPageCnt > 1){
					$("#pageNavi").html($.pageUtil(pageInfo.pageNum,pageInfo.totalPageCnt, 
							pageInfo.pageBlockSize,pageInfo.startPageNum,pageInfo.endPageNum));
					}
					var source = $("#leadsTemplate").html();
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
		getNotes:function(){
			var idx = $("#idx").val();
			var searchUrl = "/ma/leads/notes/"+idx;
			$.ajax({
				url:searchUrl,
				type:"GET",
				cache: false,
				dataType: "json",
				success: function(jsonData, textStatus, XMLHttpRequest) {
					var source = $("#notesTemplate").html();
					var template = Handlebars.compile(source);
					$("#mainContent").empty();
					$("#mainContent").append(template(jsonData));
				},
				error:function (xhr, ajaxOptions, thrownError){	
					alert(thrownError);
				}
			});
		}
	});
	if(window.location.pathname == '/ma/leads'){
		$.getLeadSearch();
	}
	var pattern = new RegExp("\/ma\/leads\/([0-9]+)");
	if(pattern.test($.trim(window.location.pathname))){
		$.getNotes();
	}
	
	$(".paging").on("click","a.naviPage",function() {
		var pageNum = $(this).attr('pageNo');	
		$('#pageNum').val(pageNum);
		$.getLeadSearch();
	});	
});

//정렬
function sort(orderBy){
	$("#orderBy").val(orderBy);
	var ord = $("#ord").val();
	if(ord == "" || ord == "DESC"){
		ord = "ASC";
	}else{
		ord = "DESC";
	}
	$("#ord").val(ord);
	$.getLeadSearch();
}