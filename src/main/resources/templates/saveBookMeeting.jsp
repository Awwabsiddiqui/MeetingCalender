<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1" />
	<title>Registeration</title>
</head>

<body>
	<h4>Registeration Status</h4>

	<html xmlns:th="https://thymeleaf.org">
	
	 <span th:if="${Status == 'Meeting Booked'}">

	<table>
		<tr>
			<td>
				<h4 th:text="${Ent.name}"></h4> with <h4 th:text="${Ent.meetingWith}"></h4>
			</td>
		</tr>
		
		<tr>
			<td>
				<h4>Meeting Start time</h4>
			</td>
			<td>
				<h4 th:text="${#dates.format(Ent.meetingDate	, 'dd-MMM-yyyy hh:mm')}"></h4>
			</td>
		</tr>
		
		<tr>
			<td>
				<h4>Meeting End time</h4>
			</td>
			<td>
				<h4 th:text="${#dates.format(Ent.meetingEndDate	, 'dd-MMM-yyyy hh:mm')}"></h4>
			</td>
		</tr>
		
	</table>

	</span>
    <span th:unless="${Status == 'Created Successfully'}">
		<h4 th:text="${Status}"></h4>
	</span>
	
	</html>

</body>

</html>