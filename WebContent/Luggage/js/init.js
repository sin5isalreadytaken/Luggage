$(function(){
	$('#board').click(function(){
		$(this).addClass('active');
		$('#position').removeClass();
	});
	
	$('#position').click(function(){
		this.className = "active";
		$('#board').removeClass('active');
	});
});