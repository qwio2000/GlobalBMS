/**
 * 공통으로 사용하는 자바스크립트 펑션을 정의 합니다.
 */

//전역 변수 선언
var getDomain = function(){
	var url = String(document.location);
	if (! url)
		return "";

	urls = url.split("/");
	if ((! urls) || (urls.length < 3))
		return "";

	return urls[2];
};

var _DOMAIN = getDomain();
var _HTTP_HOST = "http://" + _DOMAIN;
var _HTTPS_HOST = "http://" + _DOMAIN;
// 개발기간동안 임시로 막음, ssl 사이트 member.jei.com 라이센스만 있다고 함 var _HTTPS_HOST = "https://" + _DOMAIN;
var _SEARCHWDRULE = /[~!@\#$%^&*\()\=+|\\/:;?"<>']/gi; // 검색어 규칙
var _THROWNERROR = "현재 페이지에서 오류가 발생하였습니다.\n한번 더 시도해 보시고 그래도  오류가  발생시에는  고객센터로 접수 해 주십시요.";

$(document).ready(function() {
	$(function(){
	
		$.fn.extend({
			getSetSSValue: function(value){
				if (value){
					//set value and trigger change event
					$(this).val(value).change();
					return this;
				} else {
					return $(this).find(':selected').val();
				}
			},
			//added by Justin Beasley
			resetSS: function(){
				var oldOpts = $(this).data('ssOpts');
				$this = $(this);
				$this.next().remove();
				//unbind all events and redraw
				$this.unbind('.sSelect').sSelect(oldOpts);
			}
		});
		
		$.extend({
			pageUtil : function(pageNum, totalPageCnt, pageBlockSize, startPageNum,endPageNum) {
				var thisBlock = Math.ceil(pageNum / pageBlockSize); // 현재 페이징블럭
	 			var preBlock, nextBlock; // 이전, 다음 페이징 블럭
	 			var html = "";
	 
	 			if (totalPageCnt > 0) {
	 				// 현재 페이지블럭의 시작페이지번호, 전페이지번호
	 				if (thisBlock > 1) {
						startPageNum = (thisBlock - 1) * pageBlockSize + 1;
						preBlock = startPageNum - 1;
					} else {
						startPageNum = preBlock = 1;
					}
					
					// 현재 페이지블럭의 끝페이지번호, 다음페이지번호
					if ((thisBlock * pageBlockSize) >= totalPageCnt) {					
						endPageNum = totalPageCnt;
						nextBlock = endPageNum;
					} else {
						endPageNum = thisBlock * pageBlockSize;
	
						nextBlock = endPageNum + 1
					}
					
					if(pageNum > 1){
						html += "<a class='first naviPage' pageNo='1' href='javascript:;'><img alt='처음' src='/public/img/community/announcement/btn_paging_first.gif'></a> "; // 맨처음으로 가기
						
						if (preBlock > 1) {
							html += "<a class='prev naviPage' pageNo='"+preBlock+"' href='javascript:;'><img alt='이전' src='/public/img/community/announcement/btn_paging_prev.gif'></a> "; // 현재블럭의 전페이지
						}
					}
	
					
					for (i = startPageNum; i <= endPageNum; i++) {
						
						if(i != pageNum){
							html += " <a class='naviPage' href='javascript:;' pageNo='"+i+"'>"+i+"</a> ";
						} else {
							html += "<a class='on' href='javascript:;' pageNo='"+i+"'><strong>"+i+"</strong></a>";
						}
					}
	
					if(pageNum != totalPageCnt){
						if(nextBlock < totalPageCnt ){
							html += "<a class='next naviPage' pageNo='"+nextBlock+"' href='javascript:;'><img alt='다음' src='/public/img/community/announcement/btn_paging_next.gif'></a> "; // 현재 블럭의 다음페이지
						}
						html += "<a class='last naviPage' pageNo='"+totalPageCnt+"' href='javascript:;'><img alt='끝' src='/public/img/community/announcement/btn_paging_last.gif'></a> "; // 맨끝으로 가기
					}
				}
				return html;
			},
			// 검색어 체크
			searchValueCheck: function(element) {
	
				var schValue = jQuery.trim($("#"+element).val().replace(_SEARCHWDRULE,''));
				$("#"+element).val(schValue);
	
				if(!$("#"+element).val()) { alert("검색어를 2글자이상 입력하세요 \n\n특수문자는 검색 불가능합니다");$("#"+element).val('');$("#"+element).focus(); return false; }
				if($("#"+element).val().length < 2) { alert("검색어는 2글자이상 입력하셔야 합니다\n\n특수문자는 검색 불가능합니다");$("#"+element).focus(); return false; }
			},
	
			//전체선택,해제
			check_all:function(element1,element2) {
	
				if ($("#"+element1+":checked").length > 0) {
					$("input[name="+element2+"]").prop("checked", true);
				}else {
					$("input[name="+element2+"]").prop("checked", false);
				}
			},
			//리스트에서 선택한 데이타 처리전 체크
			btn_check:function(act,element) {
				var str = act;
				if (act == "update") {
					str = "수정";
				}else if (act == "delete"){
					str = "삭제";
				}
	
				if ($("input[name="+element+"]:checked").length == 0){
					alert(str + "할 자료를 하나 이상 선택하세요.");
					return false;
				}
				if (act == "delete")
				{
					if (!confirm("삭제후 복원할 수 없습니다.\n\n선택한 자료를 정말 삭제 하시겠습니까?"))
						return false;
				}
	
				return true;
			},
			// 숫자키 만 입력받게 함
			numericCheck:function() {
				if((event.keyCode < 48) || (event.keyCode > 57)){
			 		event.returnValue = false;
				}
			},
	
		    // 숫자인지검사
			numeric:function(element,str){
				var pattern = /(^[0-9]+$)/;
				if(!pattern.test($.trim($("#"+element).val()))){
				    alert(str + ":숫자가 아닙니다.\n");
				    return false;
				}
				return true;
			},		
			
			// 양쪽 공백 없애기
			trimvalue:function(element){
		        var pattern = /(^\s*)|(\s*$)/g; // \s 공백 문자
				var trimValue = jQuery.trim($("#"+element).val().replace(pattern,''));
				$("#"+element).val(trimValue);
				return trimValue;
		    },
		    // 필수 입력 검사
		    required:function (element,str) {
				var chk = false;
				if($("#"+element).attr("type") == 'radio' || $("#"+element).attr("type") == 'checkbox'){
					var chk_fld = $("input[name="+element+"]");
					for(var i=0; i<chk_fld.length; i++)
					{
						if(chk_fld[i].checked){
							chk = true;
							break;
						}
					}
					if(!chk)
					{
						str = str + " : 필수 선택 입니다.\n";
					}
				}else {
		            if ($.trimvalue(element) != ""){
		            	chk = true;
		            }else{
		            	str = str + " : 필수 "+($("#"+element).attr("type")=="select-one"?"선택":"입력")+"입니다.\n";
		            }
		        }
	
				if (!chk){alert(str);}
				return chk;
		    },
		    emailCheck:function(element) {
				var pattern = /([0-9a-zA-Z_-]+)@([0-9a-zA-Z_-]+)\.([0-9a-zA-Z_-]+)/; //pattern = /(\S+)@(\S+)\.(\S+)/; 이메일주소에 한글 사용시
				if(!pattern.test($.trim($("#"+element).val()))){
				    alert("이메일 형식을 맞춰주세요. 예)hong@korea.com");
				    return false;
				}
				return true;
			},
			//비밀번호 형식 체크
			passwordCheck:function(element,str) {
				var pattern = /^.*(?=^.{8,20}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
				if(!pattern.test($.trim($("#"+element).val()))){
				    alert(str + " 형식이 아닙니다.\n\n영대/소문자, 숫자 및 특수문자 조합 비밀번호 8자리 이상 20자리 이하로 가능\n");
				    return false;
				}
				return true;
			},			
		    //파일 종류체크
		    fileNameChk:function (element,flag){
		    	var type = "";
		    	var str = "";
	
		    	switch (flag){
			    	case "img":	type = "jpg, jpeg, gif";	str = "이미지 파일("+ type +")";	break;
			    	case "jpg":	type = "jpg";				str = "이미지 파일("+ type +")";	break;
			    	case "swf":	type = "swf";				str = "플래시 파일("+ type +")";	break;
			    	case "vod":	type = "mov, avi, wmv, asf";str = "동영상 파일("+ type +")";	break;
			    	case "audio":type = "wma, mp3, ogg";	str = "오디오 파일("+ type +")";	break;
			    	case "multi":type = "mov, avi, wmv, asf, wma, mp3, ogg";	str = "멀티미디어 파일("+ type +")";
			    	case "pdf": type = "pdf"; str = "PDF 파일("+ type +")";
			    	default:break;
		    	}
	
			  	var file_ext=($("#"+element).val().substring($("#"+element).val().lastIndexOf(".")+1,$("#"+element).val().length));
			  	file_ext=file_ext.toLowerCase();
	
			  	var strfile = $("#"+element).val();
			  	var sp_v = strfile.split("\\");
			  	var file_name =(sp_v[sp_v.length-1]);
	
	
			  	//업로드 금지 확장자 체크
			  	var xBadFile = 'html,htm,php,asp,jsp,exe,script,js,dll,asa,hta,aspx,ini,db';
			  	if (xBadFile.indexOf(file_ext) != -1){
			  		alert('업로드가 금지된 확장자 파일입니다.');
			  		$("#"+element).val("");
			  		return false;
			  	}
	
			  	var cnt  = 0; var file_chk = "";
	
			  	for (var i=0;i<file_name.length;i++)
			  	{
			  		file_chk=file_name.substring(i,i+1);
			  		if(file_chk=='!'||file_chk=='@'||file_chk=='#'||file_chk=='$'||file_chk=='%'||file_chk=='^'||file_chk=='&'||file_chk=='*') {
			  			cnt = cnt + 1;
			  			break;
			  		}
			  	}
	
			  	if(cnt > 0) {
			  		alert('첨부파일명에 특수문자가 있으면 정상적으로 다운로드 되지 않습니다...(ex:&,%,$,# 등)');
			  		$("#"+element).val();
			  		return false;
			  	}
			  	else {
			  		if(type == ''){
			  			return true;
			  		} else {
			  			if (type.indexOf(file_ext) == -1) {
			  				alert(str + '만 첨부 가능합니다.');
			  				return false;
			  			} else {
			  				return true;
			  			}
			  		}
			  	}
		    },		
			//이미지 사이즈 조정
			resizeImages:function(id,imgWidth){
				var resizeWidth = parseInt(imgWidth);
				$('#'+id+' img').each(function(){
					var tmpWidth = $(this).width();
					if(tmpWidth > resizeWidth) $(this).width(resizeWidth);
					$(this).load(function() {
						tmpWidth = $(this).width();
						if(tmpWidth > resizeWidth) $(this).width(resizeWidth);
					});
				});
			},
	
			//오늘날짜 구하기(YYYY-MM-DD)
			toDay:function (){
				var now = new Date();
				var year = parseInt(now.getFullYear());
				var month = parseInt(now.getMonth()) + 1;
				var day =  parseInt(now.getDate());
	
				if(month<10){
					month = "0" + month;
				}
				if(day<10){
					day = "0" + day;
				}
	
				var toDay = year + "-" + month + "-" +  day;
				return toDay;
			},
			//두날짜 사이의 간격:일
			calculateDateDiff:function(sDate,eDate) {
				var arrDate;
				var dtStart, dtEnd, dtDiff;
				var iDays;
				try{
				    arrDate = sDate.split("-");
				    dtStart = new Date( parseInt(arrDate[0]), parseInt(arrDate[1]) - 1, parseInt(arrDate[2]) );
				    arrDate = eDate.split("-");
				    dtEnd = new Date( parseInt(arrDate[0]), parseInt(arrDate[1]) - 1, parseInt(arrDate[2]) );
				    dtDiff = dtEnd.getTime() - dtStart.getTime();
				    iDays = Math.floor(dtDiff/1000/60/60/24);
				    return iDays;
				}catch (exception) {
				    return null;
				} 
			},			
			
	
			// 세자리 마다 콤마(,) 찍기 php의 number_format 따라하기
			number_format:function(data) {
				data = String(data);
	
		        var number = '';
		        var cutlen = 3;
		        var comma = ',';
		        var i;
	
		        len = data.length;
		        mod = (len % cutlen);
		        k = cutlen - mod;
		        for (i=0; i<data.length; i++) {
		            number = number + data.charAt(i);
	
		            if (i < data.length - 1) {
		                k++;
		                if ((k % cutlen) == 0) {
		                    number = number + comma;
		                    k = 0;
		                }
		            }
		        }
		        return number;
		    },
	
		    //popup GET
			openPop:function(url, PopName, opt) {
				var win = window.open(url, PopName, opt);
				win.focus();
			},
			//popup POST
			openPopPost:function(url, PopName, opt, frm) {
				var win = window.open("", PopName, opt);
				
				frm.target = PopName;
				frm.action = url;
				frm.submit();
				frm.target = "";
				win.focus();
			},
			// 처방기록부 POST POPUP
			openIpprPost:function(jisa,omrdate, memKey, mujin, subj, lang, avg, div){	
				var html;
					html = "<form name='IpprPagePopupfrom' method='post' >";
					html += "<input type='text' name='jisa' id='jisa' value='"+jisa+"'>";
					html += "<input type='text' name='omrdate' id='omrdate' value='"+omrdate+"'>";
					html += "<input type='text' name='memKey' id='memKey' value='"+memKey+"'>";
					html += "<input type='text' name='mujin' id='mujin' value='"+mujin+"'>";
					html += "<input type='text' name='subj' id='subj' value='"+subj+"'>";
					html += "<input type='text' name='lang' id='lang' value='"+lang+"'>";
					html += "<input type='text' name='avg' id='avg' value='"+avg+"'>";
					html += "</form>";
			
					$("body").append("<div id='"+div+"'></div>");
					$("#"+div).html(html);
		
					if(subj == "KM" || subj == "EM"){
						$.openPopPost("/fa/diagnosis/OmrPrintJD", "IpprPage", "width=1024,height=800,left=300,scrollbars=yes,resizable=yes", document.IpprPagePopupfrom);
					}else{
						$.openPopPost("/fa/diagnosis/OmrPrint", "IpprPage", "width=1024,height=800,left=300,scrollbars=yes,resizable=yes", document.IpprPagePopupfrom);						
					}
				
					$("body #"+div+"").remove();
				
			},
			
			//IE여부
			isIE:function() {
				var agt = navigator.userAgent.toLowerCase();
				var trident = navigator.userAgent.match(/Trident\/(\d)/i);
				if (agt.indexOf("msie") != -1 || trident != null){
					return true;
				}else{
					return false;
				}
			}
			
				    
		});
		
		// 메뉴 컨트롤
		$(".active").css('borderBottom','2px solid #fff'); 
		
		$(".gnb>li>a").hover(function(){
			$(".gnb>li>a").removeClass('on');
			$(this).addClass('on').children().addClass('on');
			$(".active").css('borderBottom','2px solid #fc9d0b'); 
		});
	
		$(".gnb").mouseleave(function(){
			var menuFirstCode = $('#menuFirstCode').val();
			$('.gnb>li>a').removeClass('on').children().removeClass('on');
			if(menuFirstCode != ''){
				$('.gnb>li>a').each(function(index){
					if(index+1 == menuFirstCode){
						$(this).addClass('on').children().addClass('on');
						$(".active").css('borderBottom','2px solid #fff'); 
					}
				});
			}
		});
		
		$(".datePicker").datepicker(
				{
					changeYear : true,
					changeMonth : true,
					dateFormat : 'yy-mm-dd',
					prevText : '이전 달',
					nextText : '다음 달',
					monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월',
							'9월', '10월', '11월', '12월' ],
					monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월',
							'8월', '9월', '10월', '11월', '12월' ],
					dayNames : [ '일', '월', '화', '수', '목', '금', '토' ],
					dayNamesShort : [ '일', '월', '화', '수', '목', '금', '토' ],
					dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
					showMonthAfterYear : true,
					yearSuffix : '년'
		});
		$(".datePicker_yymm").datepicker(
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
				onSelect: function()
				{ 
					var dateObject = $(this).datepicker('getDate'); 
				},
				onClose: function(dateText, inst){
					var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
					var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
					$(this).datepicker('setDate', new Date(year, month, 1));
				},
		});
		 
	});

});

