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
<#attempt>
<#macro hello inputValue colorValue>
	<@fontColorer colorVal=colorValue>
 		Hello ${inputValue}
 	</@fontColorer>
</#macro>

<#macro fontColorer colorVal>
	<font color="${colorVal}">
		<#nested>
	</font>
</#macro>

	<u>
	<#if Ent.name??>
		<h2><@hello  inputValue=Ent.name colorValue="orange" />  
		<br>
		${Ent.name?upper_case}
	</#if>
		<h2>${Ent???c}</h2>
		
		<@fontColorer colorVal="blue">
			${lastChar(Ent.name)}
		</@>fontColorer>
		<#if Ent.name='awwab'>
			admin
			<#else>
			non admin
		</#if>
	</u>
	<table border="1px" style="border-color: bisque;">
		<tr>
			<th>User Name</th>
			<th>Meeting with</th>
			<th>Meeting Start Date</th>
			<th>Meeting End Date</th>
		</tr>
		<#if list??>
		  <#list list as subEnt>
                    <tr>
                   		<td>${Ent.name}</td>
                        <td>${subEnt.meetingWith}</td>
                        <td>${(subEnt.meetingDate)?string["dd-MMM-yyyy hh:mm"]}</td>
                        <td>${(subEnt.meetingEndDate)?string["dd-MMM-yyyy hh:mm"]}</td>
                    </tr>
                </#list>
		</#if>
	</table>
	
	<#list hmTest?keys as key>
    ${key} = ${hmTest[key]}
</#list>

<#recover>
	someError

	</#attempt>
</div>

</html>

</body>

</html>