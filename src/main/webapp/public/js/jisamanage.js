$(function(){	
	$.extend({

		// 지사 상세 정보
		goJisaView:function(jisaCD ){
			location.href = "/ma/jisamanage/jisaView?jisaCD="+jisaCD;
		},
		// 상품 셋팅/변경
		openSetSubjPreference:function(deptCD){
			alert('상품 셋팅/변경');
		},
		// 회비 셋팅/변경
		openSetTuitionMatrix:function(deptCD){
			alert('회비 셋팅/변경');
		}		
	
			
	});
	
	


});
