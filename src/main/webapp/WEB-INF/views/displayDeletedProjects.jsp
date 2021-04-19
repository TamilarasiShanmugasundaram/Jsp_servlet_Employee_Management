<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import= "com.ideas2it.employeeManagementSystem.project.model.Project"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Display deleted projects</h1>
<p>
	<a href="retrieveProject.jsp">Retrieve Project</a>
</p>
	<%
		@SuppressWarnings("unchecked")
	List<Project> list = (List<Project>) request.getAttribute("deletedProjects");
	if (0 == list.size()) {
	%>
	<h3>No projects to retrieve</h3>
	<a href="index.jsp">Go to main menu</a>
	<%
		} else {
	%>

	<table BORDER=1>
		<tr>
			<th>Project Id</th>
			<th>Title</th>
			<th>Start Date</th>
			<th>Estimated end date</th>
			<th>Client</th>
			<th>Status</th>
			<th>Budget</th>
		</tr>

	<%
		for (Project tempProjectList : list) {
	%>
	
		<tr>
			<td>
				<%
					out.print(tempProjectList.getProjectId());
				%>
			</td>
			<td>
				<%
					out.print(tempProjectList.getTitle());
				%>
			</td>
			<td>
				<%
					out.print(tempProjectList.getStartDate());
				%>
			</td>
			<td>
				<%
					out.print(tempProjectList.getEstimatedEndDate());
				%>
			</td>
			<td>
				<%
					out.print(tempProjectList.getClient());
				%>
			</td>
			<td>
				<%
					out.print(tempProjectList.getStatus());
				%>
			</td>
			<td>
				<%
					out.print(tempProjectList.getBudget());
				%>
			</td>
		</tr>
			<%
		}
	}
	%>
	</table>

</html>

</body>
</html>