<html lang="en">
<style>
	.center {
		border: 2px solid;
		margin: auto;
		width: 50%;
		padding: 15px;
		margin-top:200px;
	}
</style>
<head>
	<title>input Page</title>
</head>

<body>
	<div class="center">
		<h4 style="color: orangered;"	th:text="${status}"></h4>
	<!--<form action="listUser" method="post">-->
			<form action="#" th:action="@{/listUser}" th:object="${Ent}" method="post" >
		<table>
			<tr>
				<td><label for="user-name">User Name</label></td>
				<td><input type="text" name="name"></input></td>
			</tr>
			<tr>
				<td><label for="user-name">Password</label></td>
				<td><input type="password" name="password"></input></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Submit"></input></td>
			</tr>
		</table>
	</form>
	</div>
</body>

</html>