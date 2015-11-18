$(function(){	
	$.extend({
		
	});
	
	$("#saveBtn").click(function(){
		var jisaCD = $("#jisaCD").val();
		var statusCD = $("#statusCD").val();
		var closeReason = $("#closeReason").val();
		if(!($.required("jisaCD","지사"))){return;}
		if(!($.required("statusCD","입회 가능 여부"))){return;}
		if(statusCD != "2"){
			if(!($.required("closeReason","입회 불가 사유"))){return;}
		}
		var param = $("#closeForm").serialize();
		console.log(param);
		$.ajax({
			url:"/ma/manage/operate/closeRegist",
			type:"POST",
			cache: false,
			dataType: "text",
			data: param,
			success: function(jsonData, textStatus, XMLHttpRequest) {
				alert(jsonData);
				location.reload();
			},
			error:function (xhr, ajaxOptions, thrownError){	
				alert(thrownError);
			}
		});
	});
	
	$("#statusCD").change(function(){
		var statusCD = $(this).val();
		if(statusCD == "2"){
			$("#closeReason").attr("disabled", true);
		}else{
			$("#closeReason").attr("disabled", false);
		}
	});
});
