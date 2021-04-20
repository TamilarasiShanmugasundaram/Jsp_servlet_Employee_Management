<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form>
	<p>
		<button formaction="index">Go to main menu</button>
	</p>
	</form>
<form>
<p> <button formaction="getEmployee">Create</button></p>
</form>
<form>
<p> <button formaction="deleteEmployee">Delete</button></p>
</form>
<form action="displayAllEmployee">
<p>
<input type="submit" name="operation" value="displayAllEmployee">
</p>
</form>
<form>
<p><button formaction="getEmployeeId">Display single Employee</button></p></form>
<form><p> <button formaction="getIdForEmployeeUpdate">Update</button></p></form>
<form><p> <button formaction="assignProject">Assign</button></p></form>
<form><p> <button formaction="unassignProject">Unassign</button></p></form>
<p><form action="RetrieveEmployees" >
<input type="submit" name="operation" value="Retrieve"></form>
</p>
</body>
</html>