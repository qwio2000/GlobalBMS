$(function(){	
	$.extend({
		// 일일실적 팝업
		openDailySales:function(jisaCD,selYMD,subj,deptName){
			var url = "/ma/sales/dailySalesPop?jisaCD="+jisaCD+"&selYMD="+selYMD+"&selSubj="+subj+"&deptName="+deptName;
			$.openPop(url, "dailySalesPop","menubar=no,toolbar=no,status=no,resizable=yes,scrollbars=yes,width=1000,height=700");			
		},
		// 월별실적 팝업
		openMonthlySales:function(jisaCD,selYY,selMM,subj,deptName){
			var url = "/ma/sales/monthlySalesPop?jisaCD="+jisaCD+"&selYY="+selYY+"&selMM="+selMM+"&selSubj="+subj+"&deptName="+deptName;
			$.openPop(url, "monthlySalesPop","menubar=no,toolbar=no,status=no,resizable=yes,scrollbars=yes,width=1000,height=700");			
		},
		//복수과목 현황 팝업
		openStatMultiSubj:function(jisaCD,deptCD,selYY,selMM,subj,multiSubjCnt,deptName){
			var url = "/ma/sales/statMultiSubjPop?jisaCD="+jisaCD+"&deptCD="+deptCD+"&selYY="+selYY+"&selMM="+selMM+"&selSubj="+subj+"&multiSubjCnt="+multiSubjCnt+"&deptName="+deptName;
			$.openPop(url, "statMultiSubjPop","menubar=no,toolbar=no,status=no,resizable=yes,scrollbars=yes,width=1000,height=700");			
		},		
		//연령별 과목수 팝업
		openStatSubjByAge:function(jisaCD,deptCD,subj,gradeCD,selYY,selMM,deptName){
			var url = "/ma/sales/statSubjByAgePop?jisaCD="+jisaCD+"&deptCD="+deptCD+"&selYY="+selYY+"&selMM="+selMM+"&selSubj="+subj+"&ageCD="+gradeCD+"&deptName="+deptName;
			$.openPop(url, "statSubjByAgePop","menubar=no,toolbar=no,status=no,resizable=yes,scrollbars=yes,width=1000,height=700");			
		},				
		//등급별 과목수 팝업
		openStatSubjByGrade:function(jisaCD,deptCD,subj,wbGrade,selYY,selMM,deptName){
			var url = "/ma/sales/statSubjByGradePop?jisaCD="+jisaCD+"&deptCD="+deptCD+"&selYY="+selYY+"&selMM="+selMM+"&selSubj="+subj+"&wbGrade="+wbGrade+"&deptName="+deptName;
			$.openPop(url, "statSubjByGradePop","menubar=no,toolbar=no,status=no,resizable=yes,scrollbars=yes,width=1000,height=700");			
		},
		//조직찾기
		openDeptSearch:function(){
			var url = "/ma/sales/deptSearchPop";
			$.openPop(url, "deptSearchPop","menubar=no,toolbar=no,status=no,resizable=yes,scrollbars=yes,width=600,height=600");		
		},
		deptSearchSelect:function(jisaCD,deptCD,deptName){
			opener.$("#jisaCD").val(jisaCD);		
			opener.$("#deptCD").val(deptCD);			
			opener.$("#deptName").val(deptName);
			self.close();
			
		}		
	});
 
	// 일일실적 검색	
	$("#dailySalesSearchInit").on("click", function() {
		$('#selYMD').getSetSSValue($.toDay());		
		$('#selSubj').getSetSSValue("");
		location.href = "/ma/sales/dailySales";
	});	
	$("#dailySalesSearchSubmit").on("click", function() {
		location.href = "/ma/sales/dailySales?selYMD="+$("#selYMD").val()+"&selSubj="+$("#selSubj > option:selected").val();
	});
	// 일일실적검색일 캘린더
	$("#selYMD").datepicker({
		changeMonth: true,
		changeYear: true,
		showButtonPanel: true, 
		yearRange: 'c-2:c+0',
		dateFormat: 'yy-mm-dd'
    });		
	$("#selYMDDatePicker, #salesYMD").click(function(){
		$("#selYMD").val(''); 		
		$("#selYMD").datepicker("show");
	});	
	// 월별실적 검색
	$("#monthlySalesSearchInit").on("click", function() {
		$('#selYY').getSetSSValue($.toDay().split("-")[0]);
		$('#selMM').getSetSSValue($.toDay().split("-")[1]);
		$('#selSubj').getSetSSValue("");
		location.href = "/ma/sales/monthlySales";
	});	
	$("#monthlySalesSearchSubmit").on("click", function() {
		location.href = "/ma/sales/monthlySales?selYY="+$("#selYY > option:selected").val()+"&selMM="+$("#selMM > option:selected").val()+"&selSubj="+$("#selSubj > option:selected").val();
	});	
	// 등급별 과목수 검색
	$("#statSubjByGradeSearchInit").on("click", function() {
		$('#selYY').getSetSSValue($.toDay().split("-")[0]);
		$('#selMM').getSetSSValue($.toDay().split("-")[1]);
		$('#selSubj').getSetSSValue("");
		location.href = "/ma/sales/statSubjByGrade";
	});	
	$("#statSubjByGradeSearchSubmit").on("click", function() {
		location.href = "/ma/sales/statSubjByGrade?deptName="+$("#deptName").val()+"&jisaCD="+$("#jisaCD").val()+"&deptCD="+$("#deptCD").val()+"&selYY="+$("#selYY > option:selected").val()+"&selMM="+$("#selMM > option:selected").val()+"&selSubj="+$("#selSubj > option:selected").val();
	});	
	// 연령별 과목수 검색
	$("#statSubjByAgeSearchInit").on("click", function() {
		$('#selYY').getSetSSValue($.toDay().split("-")[0]);
		$('#selMM').getSetSSValue($.toDay().split("-")[1]);
		$('#selSubj').getSetSSValue("");
		location.href = "/ma/sales/statSubjByAge";
	});	
	$("#statSubjByAgeSearchSubmit").on("click", function() {
		location.href = "/ma/sales/statSubjByAge?deptName="+$("#deptName").val()+"&jisaCD="+$("#jisaCD").val()+"&deptCD="+$("#deptCD").val()+"&selYY="+$("#selYY > option:selected").val()+"&selMM="+$("#selMM > option:selected").val()+"&selSubj="+$("#selSubj > option:selected").val();
	});		
	// 복수과목 회원 현황 검색
	$("#statMultiSubjSearchInit").on("click", function() {
		$('#selYY').getSetSSValue($.toDay().split("-")[0]);
		$('#selMM').getSetSSValue($.toDay().split("-")[1]);
		$('#selSubj').getSetSSValue("");
		location.href = "/ma/sales/statMultiSubj";
	});	
	$("#statMultiSubjSearchSubmit").on("click", function() {
		location.href = "/ma/sales/statMultiSubj?deptName="+$("#deptName").val()+"&jisaCD="+$("#jisaCD").val()+"&deptCD="+$("#deptCD").val()+"&selYY="+$("#selYY > option:selected").val()+"&selMM="+$("#selMM > option:selected").val()+"&selSubj="+$("#selSubj > option:selected").val();
	});		
	
});
