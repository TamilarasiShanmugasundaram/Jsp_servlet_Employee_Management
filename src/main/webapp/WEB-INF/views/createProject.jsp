<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import=" com.ideas2it.employeeManagementSystem.project.model.Project"%>
<%@ page import="java.sql.Date"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Create project</title>
</head>

<body>

		<form> 
	<p>
		<button formaction= "Project">Project</button>
		</p>
	</form>
	
	<%Project project = (Project) request.getAttribute("project"); %>
	<form:form action="createProject" method="post"
		modelAttribute="project">
		<form:input type="hidden" id="id" path="projectId"/>
		<table>
			<tr>
				<td><form:label path="title"> Title </form:label></td>
				<td><form:input path="title" required="required" /></td>
			</tr>
			<tr>
				<td><form:label path="startDate"> Start date</form:label></td>
				<td><form:input type="date"  path="startDate"
						required="required" /></td>
			</tr>
			<tr>
				<td><form:label path="estimatedEndDate">End date</form:label></td>
				<td><form:input type="date" path="estimatedEndDate"
						required="required" /></td>
			</tr>
			<tr>
				<td><form:label path="status">Status</form:label></td>
				<td><form:input path="status" required="required" /></td>
			</tr>
			<tr>
				<td><form:label path="client">Client</form:label></td>
				<td><form:input path="client" required="required" /></td>
			</tr>
			<tr>
				<td><form:label path="budget">Budget</form:label></td>
				<td><form:input type="number" path="budget" required="required" /></td>
			</tr>
		</table>
		<%if(project.getProjectId() == 0)  {%>
			<input type="submit" value="Create"/>
	<%	}%>	
	
				<%if(project.getProjectId() != 0)  {%>
			<button type="submit" formaction="update"> Update </button>
	<%	}%>	
	</form:form>
</body>
</html>