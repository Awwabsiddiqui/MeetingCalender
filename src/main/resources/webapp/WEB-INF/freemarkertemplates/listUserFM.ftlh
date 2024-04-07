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
	<table border="1px" style="border-color: bisque;">
		<tr>
			<th>User Name</th>
			<th>Meeting with</th>
			<th>Meeting Start Date</th>
			<th>Meeting End Date</th>
		</tr>
		
		  <#list list as subEnt>
                    <tr>
                   		<td>${Ent.name}</td>
                        <td>${subEnt.meetingWith}</td>
                        <td>${(subEnt.meetingDate)?string["dd-MMM-yyyy hh:mm"]}</td>
                        <td>${(subEnt.meetingEndDate)?string["dd-MMM-yyyy hh:mm"]}</td>
                    </tr>
                </#list>
		
	</table>
</div>

</html>

</body>

</html>