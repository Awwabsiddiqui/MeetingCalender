<html lang="en">

<head>
	<title>Register Page</title>
</head>
<style>
	.center {
		border: 2px solid;
		margin: auto;
		width: 70%;
		padding: 15px;
		margin-top: 200px;
	}
</style>

<body>
	<div class="center">
		<form style="margin-left:25%;" action="#" th:action="@{/saveBookMeeting}" th:object="${Ent}" method="post">
			NAME : <input type="text" th:field="*{name}">
			<br>
			Password : <input type="password" th:field="*{password}">
			<br>
			Meeting With : <input type="text" th:field="*{meetingWith}">
			<br>
			StartDate : <input type="datetime-local" th:field="*{meetingDate}">
			<br>
			EndDate : <input type="datetime-local" th:field="*{meetingEndDate}">
			<!--Time : <input type="time" th:field="*{meetingTime}">	-->
			<br><br>
			<button style="margin-left: 10%;" type="submit">SAVE</button>
		</form>
	</div>

</body>

</html>