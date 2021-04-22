<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import=" com.ideas2it.employeeManagementSystem.project.model.Project"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display projects</title>
</head>
<body>
	<h1>Display projects</h1>
	<form>
		<p>
			<button formaction="Project">Project</button>
		</p>
	</form>
	<form>
		<p>
			<button formaction="index">Go to main menu</button>
		</p>
	</form>
	<%
		@SuppressWarnings("unchecked")
	List<Project> list = (List<Project>) request.getAttribute("Projects");
	if (0 == list.size()) {
	%>
	<h3>No projects to display</h3>
	<a href="index.jsp">Go to main menu</a>
	<%
		} else {
	%>



	<table BORDER=1>
		<tr>
			<th>ProjectId</th>
			<th>Title</th>
			<th>Start Date</th>
			<th>Estimated end date</th>
			<th>Client</th>
			<th>Status</th>
			<th>Budget</th>
			<th>isDelete</th>
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
			<td>
				<%
					out.print(tempProjectList.getIsDelete());
				%>
			</td>
			<td>
				<%
					if (false == tempProjectList.getIsDelete()) {
				%>
				<form action="DeleteProject" method="post">
					<button type="submit" name="id"
						value="<%=tempProjectList.getProjectId()%>">Delete</button>
				</form> <%
 	} else {
 %>
				<form action="retrieve" method="post">
					<button type="submit" name="id"
						value="<%=tempProjectList.getProjectId()%>">Retrieve</button>
				</form> <%
 	}
 %>
			</td>
			<td>
				<form action="UpdateProject" method="post">
					<button type="submit" name="id"
						value="<%=tempProjectList.getProjectId()%>">Update</button>
				</form>
			</td>
			<td>
					<form action="assignEmployee" method="post">
					<button type="submit" name="id"
						value="<%=tempProjectList.getProjectId()%>">Assign/Unassign</button>
				</form>
			</td>
		</tr>
		<%
			}

		}
		%>
	</table>



</body>
</html>