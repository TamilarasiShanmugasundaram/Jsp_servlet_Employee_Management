<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import=" com.ideas2it.employeeManagementSystem.project.model.Project"%>
<%@ page
	import=" com.ideas2it.employeeManagementSystem.employee.model.Employee"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Set"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Assign Project</title>
</head>
<body>

	<form>
		<p>
			<button formaction="Employee">Employee</button>
		</p>
	</form>
	<form action="AssignProject" method="post" >
		<%
		Employee employee = (Employee) request.getAttribute("employee");
		Set<Project> projects = employee.getProjectList();
		List<Project> projectList = (List<Project>) request.getAttribute("Projects");
			for (Project tempProject : projectList) {
		%>
		<p>
			<input type="checkbox" name="projectIds"
				value="<%=tempProject.getProjectId()%>"
				<%if (projects.contains(tempProject)) {%> checked="checked"
				<%} else {%> <%}%> />
			
			Title :	<%out.println(tempProject.getTitle());%>
			Id : <%out.println(tempProject.getProjectId());
			}
			%>
			<input type="hidden" name ="projectIds" value="0"/>
		</p>
		<button type="submit" name="employeeId"  value="<%=employee.getId()%>">Assign/Unassign</button>
	</form>

</body>

</html>