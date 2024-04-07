<#import "freemarker.log" as log>
<script src="/jqjs/jquery.js" type="text/javascript" ></script>
<script>
	
	function ajaxer(){
		$.ajax({
			type:'GET',
			url:'/AJAX/getId',
			success:function(result){
				alert(result);
			}
		});
	}
</script>
	<div>
		<a href="http://localhost:8080/logoutFM">LOGOUT</a>
	</div>
	<div>
		<a href="#" onclick="javascript:ajaxer();">AJAX</a>
	</div>
