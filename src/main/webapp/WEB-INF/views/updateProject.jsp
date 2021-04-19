<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import=" com.ideas2it.employeeManagementSystem.project.model.Project"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>

	<form action="update" method="post">
	<%
		Project project = (Project) request.getAttribute("project");
	if(null != project) {
	%>
	 <input type="hidden" id="id" name="id" value ="<%=project.getProjectId()%>">
	 <%} %>
		<label>Title : </label><input type="text" value ="<%=project.getTitle()%>" name="title" required><br>
		<label>Start date : </label><input type="date" value ="<%=project.getStartDate()%>" name="startDate" required><br>
		<label>Estimate end date :</label> <input type="date" value ="<%=project.getEstimatedEndDate()%>" name="estimatedEndDate" required><br> 
		<label>Status : </label><input type="text" value ="<%=project.getStatus()%>" name="status" required><br> 
		<label>Client : </label><input type="text" value ="<%=project.getClient()%>"  name="client" required><br>
		<label>Budget : </label><input type="number" value ="<%=project.getBudget()%>"  name="budget" required><br> 
		<input type="submit" name="operation" value="update">
	</form>
</body>
</html>