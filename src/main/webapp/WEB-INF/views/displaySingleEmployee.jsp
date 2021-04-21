<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import=" com.ideas2it.employeeManagementSystem.project.model.Project"
	import=" com.ideas2it.employeeManagementSystem.employee.model.Employee"%>
<%@ page
	import=" com.ideas2it.employeeManagementSystem.employee.model.Address"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Set"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form>
	<p>
		<button formaction="index">Go to main menu</button>
	</p>
	</form>
	<body>
<form>
	<p>
		<button formaction="Employee">Employee</button>
	</p>
	</form>
	<%
		//List<Employee> employeeList = 
	Employee employee =(Employee) request.getAttribute("employee");
	
	%>
	<table BORDER=1>
		<tr>
			<th>Employee Id</th>
			<th>Name</th>
			<th>Phone number</th>
			<th>Email Id</th>
			<th>DOB</th>
			<th>DOJ</th>
		</tr>
		<tr>
			<td>
				<%
					out.print(employee.getId());
				%>
			</td>
			<td>
				<%
					out.print(employee.getName());
				%>
			</td>
			<td>
				<%
					out.print(employee.getPhoneNumber());
				%>
			</td>
			<td>
				<%
					out.print(employee.getEmailId());
				%>
			</td>
			<td>
				<%
					out.print(employee.getDateOfBirth());
				%>
			</td>
			<td>
				<%
					out.print(employee.getDateOfJoining());
				%>
			</td>
		</tr>
	</table>


	<%
	Set<Project> projectList = employee.getProjectList();
	if (0 < projectList.size()) {
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
			for (Project tempProjectList : projectList) {
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

	<table BORDER=1>
		<tr>
			<th>Door number</th>
			<th>Street number</th>
			<th>City</th>
			<th>District</th>
			<th>State</th>
			<th>Country</th>
			<th>Pincode</th>
			<th>Address Type</th>
		</tr>
		<%
			List<Address> addressList = employee.getAddressList();
		for (Address tempAddressList : addressList) {
		%>

		<tr>
			<td>
				<%
					out.print(tempAddressList.getDoorNumber());
				%>
			</td>
			<td>
				<%
					out.print(tempAddressList.getStreetNumber());
				%>
			</td>
			<td>
				<%
					out.print(tempAddressList.getCity());
				%>
			</td>
			<td>
				<%
					out.print(tempAddressList.getDistrict());
				%>
			</td>
			<td>
				<%
					out.print(tempAddressList.getState());
				%>
			</td>
			<td>
				<%
					out.print(tempAddressList.getCountry());
				%>
			</td>
			<td>
				<%
					out.print(tempAddressList.getPincode());
				%>
			</td>
			<td>
				<%
					if(tempAddressList.getIsPermanent()) {
						out.print("Permanent");
					} else {
						out.print("Temporary");
					}
				%>
			</td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>