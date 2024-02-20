<!DOCTYPE html>
<html>
<style>
	.center {
		border: 2px solid;
		margin: auto;
		width: 50%;
		padding: 15px;
		margin-top: 200px;
	}
</style>
<html xmlns:th="https://thymeleaf.org">

<head>
	<meta charset="ISO-8859-1" />
	<title>Registeration</title>
</head>

<body>
	<div class="center">
		<h2>Registeration Status</h2>

		<span th:if="${Status == 'Created Successfully'}">
			<div class="center">
				<table>
					<tr>
						<td>
							<h4>Name: </h4>
						</td>
						<td>
							<h4 th:text="${Ent.name}"></h4>
						</td>
					</tr>
				</table>
			</div>
		</span>
		<span th:unless="${Status == 'Created Successfully'}">FAILED</span>

</html>

</body>

</html>