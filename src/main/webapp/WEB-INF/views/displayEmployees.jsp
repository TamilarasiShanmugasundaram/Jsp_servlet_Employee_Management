<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import=" com.ideas2it.employeeManagementSystem.employee.model.Employee"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display Employees</title>
</head>
<body>
	<h1>Display Employees</h1>
	<form>
	<p>
		<button formaction="index">Go to main menu</button>
	</p>
	</form>
			<form>
	<p>
		<button formaction="Employee">Employee</button>
	</p>
	</form>
	<%
		@SuppressWarnings("unchecked")
	List<Employee> list = (List<Employee>) request.getAttribute("Employees");
	if (0 == list.size()) {
	%>
	<h3>No employees to display</h3>
	<a href="index.jsp">Go to main menu</a>
	<%
		} else {
	%>
	<table BORDER =1>
		<tr>
			<th>Employee Id</th>
			<th>Name</th>
			<th>Phone number</th>
			<th>EmailId</th>
			<th>DOB</th>
			<th>DOJ</th>
			<th>IsDelete</th>
		</tr>
	
	<%
		for (Employee tempEmployeeList : list) {
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
						<td>
				<%
					out.print(tempEmployeeList.getIsDelete());
				%>
			</td>
						<td>
				<%
					if (false == tempEmployeeList.getIsDelete()) {
				%>
				<form action="DeleteEmployee" method="post">
					<button type="submit" name="id"
						value="<%=tempEmployeeList.getId()%>">Delete</button>
				</form> <%
 	} else {
 %>
				<form action="retrieveEmployee" method="post">
					<button type="submit" name="id"
						value="<%=tempEmployeeList.getId()%>">Retrieve</button>
				</form> <%
 	}
 %>
			</td>
					<td>
				<form action="getEmployeeForUpdate" method="post">
					<button type="submit" name="id"
						value="<%=tempEmployeeList.getId()%>">Update</button>
				</form>
			</td>
			<td>
							<form action="assignProject" method="post">
					<button type="submit" name="id"
						value="<%=tempEmployeeList.getId()%>">Assign/Unassign</button>
				</form>
			</td>
		</tr>
		
	<% }%>	
	<% }
	%>
	</table>


</body>
</html>