<#import "/spring.ftl" as spring/>
<html lang="en">
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
<title>input Page</title>
</head>
<#include '/menuFM.ftlh'>
<body>
	<div class="center">
	Error => 
	<br>
	
	${ERROR_STATUS_CODE!'ERROR_STATUS_CODE'}
	<br>
	${ERROR_EXCEPTION!'ERROR_EXCEPTION'}
	<br>
	${ERROR_EXCEPTION_TYPE!'ERROR_EXCEPTION_TYPE'}
	<br>
	${ERROR_MESSAGE!'ERROR_MESSAGE'}
	<br>
	</div>
</body>

</html>