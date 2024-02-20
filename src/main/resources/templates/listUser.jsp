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
	<title>List</title>
</head>

<body>
	<html xmlns:th="https://thymeleaf.org">
	<div class="center">
		<u>
			<h2 th:text="${Ent.name}"></h2>
		</u>
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
			<tr th:each="ent : ${list}">
				<div th:if="${ent.meetingWith!=null}">
					<td th:text="${ent.name}"> </td>
					<td th:text="${ent.meetingWith}"> </td>
					<td th:text="${#dates.format(ent.meetingDate	, 'dd-MMM-yyyy hh:mm')}"></td>
					<td th:text="${#dates.format(ent.meetingEndDate	, 'dd-MMM-yyyy hh:mm')}"></td>
				</div>
			</tr>
		</table>
	</div>

	</html>

</body>

</html>