$(function(){	
	$.extend({
		getCodeDtls:function(){
			var pageNum = $("#pageNum").val();
			var jisaCD = $("#jisaCD").val();
			var mstCD = $("#mstCD").val();
			var searchUrl = "/ma/manage/operate/code/"+pageNum;
			$.ajax({
				url:searchUrl,
				type:"GET",
				cache: false,
				dataType: "json",
				data: {"jisaCD":jisaCD, "mstCD":mstCD},
				success: function(jsonData, textStatus, XMLHttpRequest) {
					var pageInfo = jsonData.pageInfo;
					var totalRowCnt = pageInfo.totalRowCnt;
					var pageNum = pageInfo.pageNum;
					var pageSize = pageInfo.rowBlockSize;
					var source = $("#codeListTemplate").html();
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
					$("#listContent").empty();
					$("#listContent").append(template(jsonData));
					if(pageInfo.totalPageCnt > 1){
						$("#pageNavi").html($.pageUtil(pageInfo.pageNum,pageInfo.totalPageCnt, 
								pageInfo.pageBlockSize,pageInfo.startPageNum,pageInfo.endPageNum));	
					}
				},
				error:function (xhr, ajaxOptions, thrownError){	
					alert(thrownError);
				}
			});
		}
	});
	
	$("#goBtn").click(function(){
		var mstCD = $("#mstCD").val();
		if(mstCD == ""){
			alert('관리 코드를 선택해 주세요.');
			$("#mstCD").focus();
			return;
		}
		var jisaCD = $("#jisaCD").val();
		if(jisaCD == ""){
			alert('지사를 선택해 주세요.');
			$("#jisaCD").focus();
			return;
		}
		$.getCodeDtls();
	});
	
	$("body").on('click', "a.naviPage", function(){
		var pageNum = $(this).attr('pageNo');
		$('#pageNum').val(pageNum);
		$.getCodeDtls();
	});
	
	var pageNum = $("#pageNum").val();
	var mstCD = $("#mstCD").val();
	var jisaCD = $("#jisaCD").val();
	if(pageNum != '' && mstCD != '' && jisaCD != '' && window.location.pathname == '/ma/manage/operate/code'){
		$.getCodeDtls();
	}
	
});

function addNewCodeDtl(){
	var mstCD = $("#mstCD").val();
	var jisaCD = $("#jisaCD").val();
	var pageNum = $("#pageNum").val();
	location.href = "/ma/manage/operate/code/new?mstCD="+mstCD+"&jisaCD="+jisaCD+"&pageNum="+pageNum;
}
