<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
			<form> 
	<p>
		<button formaction= "Project">Project</button>
		</p>
	</form>
	<form action="DeleteProject" method="post">
		<label> Enter id : </label> <input type="number" name="id" required><br>
		<input type="submit" name="operation" value="delete">
	</form>
</body>
</html>