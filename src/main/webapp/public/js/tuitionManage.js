$(function(){	
	$.extend({
		getTuitionInfos:function(){
			var jisaCD = $("#jisaCD").val();
			var searchUrl = "/ma/jisamanage/tuition/"+jisaCD+"/list";
			$.ajax({
				url:searchUrl,
				type:"GET",
				cache: false,
				dataType: "json",
				success: function(jsonData, textStatus, XMLHttpRequest) {
					var source = $("#jisaManageTemplate").html();
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
					$("#mainContent").empty();
					$("#mainContent").append(template(jsonData));
				},
				error:function (xhr, ajaxOptions, thrownError){	
					alert(thrownError);
				}
			});
		}		
	});
	if(window.location.pathname.indexOf('ma/jisamanage/tuition') > 0){
		$.getTuitionInfos();
	}
	
	$("#editBtn").on("click", function(){
		if(confirm('정말 회비를 수정하시겠습니까?')){
			var param = $('#tuitionForm').serialize();
			$.ajax({
				url:"/ma/jisamanage/tuition",
				type:"POST",
				cache: false,
				dataType: "text",
				data: param,
				success: function(jsonData, textStatus, XMLHttpRequest) {
					alert(jsonData);
					cancel();
					$.getTuitionInfos();
				},
				error:function (xhr, ajaxOptions, thrownError){	
					alert(thrownError);
				}
			});
		}
	});
});

function editTuition(deptType, feeType){
	var param = {"jisaCD":$('#jisaCD').val(), "deptType":deptType, "feeType":feeType};
	$.ajax({
		url:"/ma/jisamanage/tuition/edit",
		type:"GET",
		cache: false,
		dataType: "json",
		data: param,
		success: function(jsonData, textStatus, XMLHttpRequest) {
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

function cancel(){
	$('#divData').css("display", "none");
	resizePop('S');
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
