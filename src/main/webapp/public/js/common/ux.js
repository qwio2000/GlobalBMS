$(function(){
	//툴팁 : 아이콘 설명
	$(".tooltip").each(function(){
		$(this).on("mouseenter",function(e){
			if($(this).attr('title') != ""){
			$('.tooltip_Area').css({
				position:'absolute',
				zIndex:50,
				top:$(this).position().top+$(this).height(),
				left:$(this).position().left
			}).text($(this).attr('title')).show(0);
			}
		});
		$(this).on("mouseleave",function(e){
			$('.tooltip_Area').hide(0);
		});
	});
});


//팝업오픈
function openPopup(el){
	var top = ( $(window).scrollTop() + ($(window).height() - $(el).height()) / 2 );

	$('.bg_layer_popup').css({
		height:$(document).height()
	}).fadeIn(100,function(){
		$(el).fadeIn(100).css({
			'left':'50%',
			'top':top, 
			position:'absolute',
			marginLeft: -$(el).width() /2
		});
	});
}

//팝업클로즈
function closePopup(el){
	$(el).hide(0,function(){
		$('.bg_layer_popup').fadeOut(100);
	});
}
