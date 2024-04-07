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
		<h4 style="color: orangered;">${status!'NA'}</h4>
		<h4 style="color: orangered;">${title!'NA'}</h4>
		
		  <#assign urlAction>/notes/add</#assign>
            <#assign submitTitle>SEARCH</#assign>

		  <form action="listUserFM" name="listUserFM" method="GET" >
            <table border="0">
                <tr>
                    <td>Title</td>
                    <td>:</td>
                    <td><input type="text" name="name" value="${(Ent.name)!''}" /></td>              
                </tr>
                <tr>
                    <td>Content</td>
                    <td>:</td>
                   <td><input type="text" name="password" value="${(Ent.password)!''}" /></td>    
                </tr>
            </table>
            <input type="submit" value="${submitTitle}" />
        </form>
	</div>
</body>

</html>