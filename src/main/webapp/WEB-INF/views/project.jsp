<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
	<h1>Create project</h1>
	<form> 
	<p>
		<button formaction= "getProject">Create</button>
	</p>
	</form>
	<p>
		<button onclick="document.location='deleteProject.jsp'">Delete</button>
	</p>
	<p>
	<form action="DisplayAllProject">
		<input type="submit" name="operation" value="DisplayAllProject">
	</form>
	</p>
	<p>
		<button onclick="document.location='getProjectId.jsp'">Display
			single project</button>
	</p>
	<p>
		<button onclick="document.location='getIdForProjectUpdate.jsp'">Update</button>
	</p>
	<p>
		<button onclick="document.location='assignEmployee.jsp'">Assign</button>
	</p>
	<p>
		<button onclick="document.location='unassignEmployee.jsp'">Unassign</button>
	</p>
	<p>
	<form action="Retrieve">
		<input type="submit" name="operation" value="Retrieve">
	</form>
	</p>
</body>
</html>