<!DOCTYPE html>
<html lang="en">
<head>
<title>HOME Page FM</title>
</head>
<style>
.center {
	border: 2px solid;
	margin: auto;
	width: 50%;
	padding: 15px;
	margin-top: 200px;
}
</style>
<script src="/jqjs/jquery.js" type="text/javascript"></script>

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
<body>

	<div>
		<a href="http://localhost:8080/logoutFM">LOGOUT</a>
	</div>
	<div>
		<a href="#" onclick="javascript:ajaxer();">AJAX</a>
	</div>
	<div class="center">
		<a href="http://localhost:8080/searchFM">Search User</a> <br> 
		<a href="http://localhost:8080/listAllUserFM">List All Users</a> <br>
		<a href="http://localhost:8080/inputFM">Add User</a> <br> 
		<a href="http://localhost:8080/bookMeetingUIFM">Book Meeting</a>

	</div>
</body>

</html>