	<style type="text/css">
		.demo ul { list-style-type: none; margin: 0; padding: 0; margin-bottom: 10px; }
		.demo li { margin: 5px; padding: 5px; width: 150px; }
	</style>

	<script type="text/javascript">
	$(function() {
		$( "#sortable" ).sortable({
			revert: true
		});
		$( "ul, li" ).disableSelection();


		$("#sortsave").on('click',function() {
			var lan = $("#sortable").sortable('toArray');
			var url = "/adminManager/menuChange";
			$.ajax({
				url:url,
				type:"POST",
				data: "lan="+lan,
				cache: false,
				async: true,
				dataType: "text",
				success: function(msg, textStatus, XMLHttpRequest) {
					alert(msg);
					$.menu_list_load();			
				},
				error:function (xhr, ajaxOptions, thrownError){	
					alert(thrownError);
				}
			});
			
		});
	});
	</script>


<div class="demo">

<ul id="sortable">
	<#list menuList as menu>
		<li class="ui-state-default" id="${menu.MIdx}">${menu.MMenuName}</li>
	</#list>
</ul>
</div>
<input id="sortsave" type="button" value="순서변경저장하기">
