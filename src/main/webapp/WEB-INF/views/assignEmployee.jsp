<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import=" com.ideas2it.employeeManagementSystem.employee.model.Employee"%>
<%@ page
	import=" com.ideas2it.employeeManagementSystem.project.model.Project"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Set"%>
<%@ page import="org.springframework.web.bind.annotation.RequestParam"%>
<%@ page import="org.springframework.web.servlet.ModelAndView"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Assign Employee</title>
</head>
<body>
	<form>
		<%
		Project project = (Project) request.getAttribute("project");
		Set<Employee> list = project.getEmployeeList();
		List<Employee> employeeList = (List<Employee>) request.getAttribute("Employees");
		%>
		<p>
			<button formaction="Project">Project</button>
		</p>
	</form>
	<form action="AssignEmployee" method="post" >

		<%
			for (Employee employee : employeeList) {
		%>
		<p>
			<input type="checkbox" name="employeeIds"
				value="<%=employee.getId()%>" <%if (list.contains(employee)) {%>
				checked="checked" 	<%} else {%> <%}%> />
		
			Name :		<%out.println(employee.getName());%>
		Id :		<%out.println(employee.getId());
			}
			%>
		</p>
		<input type="hidden" name ="employeeIds" value="0"/>
		<button type="submit" name="projectId"
			value="<%=project.getProjectId()%>">Assign/Unassign</button>
			
	</form>
	
</body>

</html>