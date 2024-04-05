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

<body>
	<div class="center">
		<h4 style="color: orangered;">${status}</h4>
		
		
		  <#assign urlAction>/notes/add</#assign>
            <#assign submitTitle>Create</#assign>

		  <form action="${urlAction}" name="note" method="POST">
            <table border="0">
                <#if note.id??>
                <tr>
                    <td>ID</td>
                    <td>:</td>
                    <td>${note.id}</td>             
                </tr>
                </#if>
                <tr>
                    <td>Title</td>
                    <td>:</td>
                    <td><input type="text" name="title" value="${(note.title)!''}" /></td>              
                </tr>
                <tr>
                    <td>Content</td>
                    <td>:</td>
                    <td><textarea name="content" rows="4" cols="50">${(note.content)!""}</textarea></td>
                </tr>
                <#if note.createdOn??>
                <tr>
                    <td>Created On</td>
                    <td>:</td>
                    <td>${(note.createdOn).format('yyyy-MM-dd HH:mm:ss')}</td>              
                </tr>
                <tr>
                    <td>Updated On</td>
                    <td>:</td>
                    <td>${(note.updatedOn).format('yyyy-MM-dd HH:mm:ss')}</td>              
                </tr>
                </#if>
            </table>
            <input type="submit" value="${submitTitle}" />
        </form>
						
		<!--<form action="" method="POST">
		Name:
		<@spring.bind "Ent.name"/>
		<input type="text" name="${spring.status.expression}" value="${spring.status.value}"/><br />
		<#list spring.status.errorMessages as error> <b>${error}</b> <br /> </#list>
		<br />
		<input type="submit" value="submit"/>
	</form>
		-->
	</div>
</body>

</html>