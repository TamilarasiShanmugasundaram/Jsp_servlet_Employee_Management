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

	<form>
		<p>
			<button formaction="Employee">Employee</button>
		</p>
	</form>
	<form action="AssignProject" method="post">
		<%
			@SuppressWarnings("unchecked")
		List<Project> projectList = (List<Project>) request.getAttribute("Projects");
		%>
		<label>Enter Employee id : </label> <input type="number"
			name="employeeId" required><br>
		<%
			for (Project tempProject : projectList) {
			
		%>
		<p>
			<input type="checkbox"  name="projectIds"
				value="<%=tempProject.getProjectId()%>">
			<%
				out.println(tempProject.getTitle());
			}
		%>
		</p>
			<input type="submit" name="operation" value="AssignProject">
		<br> 
	</form>
</body>
</html>