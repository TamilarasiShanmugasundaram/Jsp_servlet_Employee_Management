<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import = "com.ideas2it.employeeManagementSystem.employee.model.Employee"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
	<h1>Deleted employees</h1>
	<p>
		<a href="retrieveEmployee.jsp">Retrieve Employee</a>
	</p>
	<%
		@SuppressWarnings("unchecked")
	List<Employee> list = (List<Employee>) request.getAttribute("deletedEmployees");
	if (0 == list.size()) {
	%>
	<h3>No Employees to retrieve</h3>
	<a href="index.jsp">Go to main menu</a>
	<%
		} else {
	%>

	<table BORDER=1>
		<tr>
			<th>Employee Id</th>
			<th>Name</th>
			<th>Phone number</th>
			<th>EmailId</th>
			<th>DOB</th>
			<th>DOJ</th>
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
		</tr>
		<%
			}
		}
		%>
	</table>



</body>
</html>