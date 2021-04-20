<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import=" com.ideas2it.employeeManagementSystem.project.model.Project"
	import=" com.ideas2it.employeeManagementSystem.employee.model.Employee"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form> 
	<p>
		<button formaction= "Project">Project</button>
		</p>
	</form>
	<form>
	<p>
		<button formaction="index">Go to main menu</button>
	</p>
	</form>
	<%
		Project project = (Project) request.getAttribute("project");
	    List<Employee> employeeList = project.getEmployeeList();
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
		<tr>
			<td>
				<%
					out.print(project.getProjectId());
				%>
			</td>
			<td>
				<%
					out.print(project.getTitle());
				%>
			</td>
			<td>
				<%
					out.print(project.getStartDate());
				%>
			</td>
			<td>
				<%
					out.print(project.getEstimatedEndDate());
				%>
			</td>
			<td>
				<%
					out.print(project.getClient());
				%>
			</td>
			<td>
				<%
					out.print(project.getStatus());
				%>
			</td>
			<td>
				<%
					out.print(project.getBudget());
				%>
			</td>
		</tr>
	</table>
	<%if(0 < employeeList.size()) { %>
		<table BORDER=1>
		<tr>
			<th>Employee Id</th>
			<th>Name</th>
			<th>Phone number</th>
			<th>Email id</th>
			<th>DOB</th>
			<th>DOJ</th>
	
	<%
		for (Employee tempEmployeeList : employeeList) {
	%>
	
		<tr>
			<td>
				<%
					out.print(tempEmployeeList.getId());
				%>
			</td>
			<td>
				<%
					out.print(tempEmployeeList.getName());
				%>
			</td>
			<td>
				<%
					out.print(tempEmployeeList.getPhoneNumber());
				%>
			</td>
			<td>
				<%
					out.print(tempEmployeeList.getEmailId());
				%>
			</td>
			<td>
				<%
					out.print(tempEmployeeList.getDateOfBirth());
				%>
			</td>
			<td>
				<%
					out.print(tempEmployeeList.getDateOfJoining());
				%>
			</td>
		</tr>
		<%
		}}%>
	</table>
</body>
</html>