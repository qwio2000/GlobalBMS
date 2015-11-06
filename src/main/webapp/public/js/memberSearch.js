$(function(){	
	$.extend({
		getMemberSearch:function(){
			var pageNum		= $("#pageNum").val();
			var searchUrl 	= "/ma/member/search/"+pageNum;
			var kind        = $("#kind").val();
			var type        = $("#type").val();
			var memKey      = $("#memKey").val();
			var memName     = $("#memName").val();
			var birthYM     = $("#birthYM").val();
			var paramData = {"kind":kind,"type":type,"memKey":memKey,"memName":memName,"birthYM":birthYM};
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
					var source = $("#memberReportTemplate").html();
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
	
//	$("#hiddenPicker").datepicker({
//		changeMonth: true,
//		changeYear: true,
//		yearRange: '1950:2015',
//		dateFormat: 'yy-mm-dd',
//		onSelect: function(dataText, inst){
//			var today = new Date();
//			var yy = dataText.split('-')[0];
//			var mm = dataText.split('-')[1];
//			var dd = dataText.split('-')[2];
//			var dob = new Date(yy, mm-1, dd);
//			if(today < dob){
//				alert('생년월일이 오늘 날짜보다 클 수는 없습니다');
//				return;
//			}
//			$("#birthYM").val(yy+'-'+mm);
//		}
//	});
	$("#hiddenPicker").datepicker(
		{
			changeMonth: true,
			changeYear: true,
			dateFormat : 'yy-mm',
			showButtonPanel: true,
			yearRange: "-100:+0",
			monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월',
							'9월', '10월', '11월', '12월' ],
			monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월',
							'8월', '9월', '10월', '11월', '12월' ],
			yearSuffix : '년',
			showMonthAfterYear : true,
			closeText : "확인",
			currentText : "오늘",
			onSelect: function(){ 
				var dateObject = $(this).datepicker('getDate'); 
				alert(dateObject);
			},
			onClose: function(dateText, inst){
				var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
				var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
				if(parseInt(month)+1 < 10){
					month = '0'+(parseInt(month)+1);
				}else{
					month = (parseInt(month)+1);
				}
				$('#birthYM').val(year+'-'+month);
				$(this).datepicker('setDate', new Date(year, month, 1));
			}
	});
	$("#dobDatePicker, #birthYM").click(function(){
		var birthYM = $("#birthYM").val();
		var yy = birthYM.split('-')[0];
		var mm = birthYM.split('-')[1];
		$("#hiddenPicker").datepicker("setDate", new Date(yy, mm-1, 1));
		$("#hiddenPicker").datepicker("show");
	});
	
	$("#memberSearchBtn").click(function(){
		var memKey = $('#memKey').val();
		var memName = $('#memName').val();
		var birthYM = $('#birthYM').val();
		if(memKey != ''){
			if(memKey.length != '8'){
				alert('회원번호 8자리를 정확히 입력해 주십시오');
				$('#memKey').focus();
				return;
			}
		}else{
			if((memName != '' ^ birthYM != '') || (memName != '' && birthYM != '')){
				if(birthYM == ''){
					alert('생년월일을 입력해 주십시오.');
					$('#birthYM').focus();
					return;
				}else if(memName == '' || memName.length < 2){
					alert('회원 이름을 두 자리 이상 입력해 주십시오.');
					$('#memName').focus();
					return;
				}
			}else{
				alert('회원 검색 정보를 입력해 주십시오.');
				$('#memKey').focus();
				return;
			}
		}
		$("#memberSearchForm").submit();
	});
	
	if(window.location.pathname == '/ma/member/search'){
		$.getMemberSearch();
	}
});
