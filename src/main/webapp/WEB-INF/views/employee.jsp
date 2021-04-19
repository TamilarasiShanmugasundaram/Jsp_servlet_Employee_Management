<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<p> <button onclick="document.location='createEmployee.jsp'">Create</button></p>
<p> <button onclick="document.location='deleteEmployee.jsp'">Delete</button></p>
<p><form action="displayAllEmployee">
<input type="submit" name="operation" value="displayAllEmployee">
</form></p>
<p><button onclick="document.location='getEmployeeId.jsp'">Display single Employee</button></p>
<p> <button onclick="document.location='getIdForEmployeeUpdate.jsp'">Update</button></p>
<p> <button onclick="document.location='assignProject.jsp'">Assign</button></p>
<p> <button onclick="document.location='unassignProject.jsp'">Unassign</button></p>
<p><form action="RetrieveEmployees" >
<input type="submit" name="operation" value="Retrieve">
</form></p>

</body>
</html>