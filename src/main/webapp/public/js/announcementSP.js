$(function(){
	$.extend({
		getBoardList:function(){
			var pageNum = $("#pageNum").val();
			var searchUrl = "/community/announcementsSP/page/"+pageNum;
			var searchField = $("#searchField").val();
			var searchValue = $.trim($("#searchValue").val());
			var paramData = {"searchField":searchField, "searchValue":searchValue};
			$.ajax({
				url:searchUrl,
				type:"GET",
				cache: false,
				data: paramData,
				dataType: "json",
				success: function(jsonData, textStatus, XMLHttpRequest) {
					var pageInfo = jsonData.pageInfo;
					var totalRowCnt = pageInfo.totalRowCnt;
					$("#totalCnt").html(totalRowCnt);
					$("#pageNavi").html($.pageUtil(pageInfo.pageNum,pageInfo.totalPageCnt, 
							pageInfo.rowBlockSize,pageInfo.startPageNum,pageInfo.endPageNum));	
					var source = $("#announcementsTemplate").html();
					var template = Handlebars.compile(source);
					Handlebars.registerHelper("boardNo", function(index, options){
						return totalRowCnt - pageInfo.startRow - index;
					});
					$("#mainContent").empty();
					$("#mainContent").append(template(jsonData));
				},
				error:function (xhr, ajaxOptions, thrownError){	
					alert(thrownError);
				}
			});
		},
		fileDelete:function(boardIdx, fileIdx, index){
			if(!confirm('정말 첨부파일을 삭제 하시겠습니까?')){
				return false;
			}
			$.ajax({
				url: "/community/announcementsSP/"+boardIdx+"/"+fileIdx,
				type:"delete",
				cache: false,
				async: true,
				success: function(jsonData, textStatus, XMLHttpRequest) {
					$("#attachFile"+index).empty();
					$("#attachFile"+index).append("<input type='file' name='attachFile'/>");
					alert(jsonData.msg);
				},
				error:function (xhr, ajaxOptions, thrownError){	
					alert(thrownError);
				}
			});
		},
		commentDelete:function(commentIdx){
			if(!confirm('정말 댓글을 삭제 하시겠습니까?')){
				return false;
			}
			var boardIdx = $("#boardIdx").val();
			$.ajax({
				url: "/community/announcementsSP/"+boardIdx+"/comment/"+commentIdx,
				type:"delete",
				cache: false,
				async: true,
				success: function(jsonData, textStatus, XMLHttpRequest) {
					alert(jsonData.msg);
					var source = $("#commentsTemplate").html();
					var template = Handlebars.compile(source);
					Handlebars.registerHelper("boardNo", function(index, options){
						return totalRowCnt - pageInfo.startRow - index;
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
					$("#commentContent").val('');
					$("#commentsContent").empty();
					$("#commentsContent").append(template(jsonData));
				},
				error:function (xhr, ajaxOptions, thrownError){	
					alert(thrownError);
				}
			});
		}
	});
	
	//리스트 페이지인 경우 getBoardList 호출
	if(window.location.pathname == '/community/announcementsSP')
		$.getBoardList();
	// paging 클릭
	$(".paging").on("click","a.naviPage",function() {
		var pageNum = $(this).attr('pageNo');	
		$('#pageNum').val(pageNum);
		$.getBoardList();
	});	
	
	$("#searchBtn").on("click",function() {
		var searchValue = $('#searchValue');
		if(searchValue.val().trim() == ""){
			alert("검색어를 입력해 주세요");
			searchValue.focus();
			return false;
		}
		$("#pageNum").val("1");
		$.getBoardList();
	});
	
	$("#submitBtn").on("click",function() {
		var boardSubject = $("#boardSubject");
		var boardContent = $("#boardContent");
		
		if(boardSubject.val().trim() == ""){
			alert("제목을 입력해 주세요");
			boardSubject.focus();
			return false;
		}else if(boardContent.val().trim() == ""){
			alert("내용을 입력해 주세요");
			boardContent.focus();
			return false;
		}
		$("#announcementFrm").submit();
		
	});
	$(".attachFile").on("change",function() {
		var f = this.files[0];
		if(f.size > 5 * 1024 * 1024){
			alert('파일 첨부는 5MB까지 가능합니다.');
			this.value = "";
			this.focus();
		}
	});
	
	$("#deleteBtn").on("click",function() {
		if(!confirm('정말 삭제 하시겠습니까?')){
			return false;
		}
		var boardIdx = $('#boardIdx').val();
		$.ajax({
			url: "/community/announcementsSP/"+boardIdx,
			type:"delete",
			cache: false,
			async: true,
			success: function(jsonData, textStatus, XMLHttpRequest) {
				alert(jsonData.msg);
				location.href="/community/announcementsSP";
			},
			error:function (xhr, ajaxOptions, thrownError){	
				alert(thrownError);
			}
		});
	});
	
	$("#commentSubmit").on("click",function() {
		if($("#commentContent").val().trim() == ""){
			alert("댓글을 입력하여 주십시오");
			$("#commentContent").focus();
			return false;
		}
		var boardIdx = $('#boardIdx').val();
		var url = "/community/announcementsSP/"+boardIdx+"/comment";
		var commentContent = $("#commentContent").val();
		$.ajax({
			url: url,
			type:"post",
			data: {"commentContent":commentContent},
			cache: false,
			async: true,
			success: function(jsonData, textStatus, XMLHttpRequest) {
				alert(jsonData.msg);
				var source = $("#commentsTemplate").html();
				var template = Handlebars.compile(source);
				Handlebars.registerHelper("boardNo", function(index, options){
					return totalRowCnt - pageInfo.startRow - index;
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
				$("#commentContent").val('');
				$("#commentsContent").empty();
				$("#commentsContent").append(template(jsonData));
			},
			error:function (xhr, ajaxOptions, thrownError){	
				alert(thrownError);
			}
		});
	});
	$("input:text").keydown(function(evt) {
        if (evt.keyCode == 13)
            return false;
    });
});
