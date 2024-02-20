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

<head>
	<meta charset="ISO-8859-1" />
	<title>List All</title>
</head>

<body>
	<html xmlns:th="https://thymeleaf.org">
	<div class="center">
		<u>
<!--			<h2 th:text="${list}"></h2>
-->		</u>
		<table border="1px" style="border-color:bisque;">
			<tr>
				<th>
					User Name
				</th>
				<th>
					Meeting with
				</th>
				<th>
					Meeting Start Date
				</th>
				<th>
					Meeting End Date
				</th>
			</tr>
			<tr th:each="Ent : ${list}">
				<div th:if="${Ent.meetingWith!=null}">
					<td th:text="${Ent.name}"> </td>
					<td th:text="${Ent.meetingWith}"> </td>
					<td th:text="${#dates.format(Ent.meetingDate	, 'dd-MMM-yyyy hh:mm')}"></td>
					<td th:text="${#dates.format(Ent.meetingEndDate	, 'dd-MMM-yyyy hh:mm')}"></td>
				</div>
			</tr>
		</table>
	</div>

	</html>

</body>

</html>