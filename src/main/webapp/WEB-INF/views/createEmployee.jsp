<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page
	import=" com.ideas2it.employeeManagementSystem.employee.model.Address"
	import=" com.ideas2it.employeeManagementSystem.employee.model.Employee"%>
<%@ page import="java.util.HashSet"%>
<%@ page import="java.time.LocalDate"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Employee</title>
</head>
<body>
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
		Employee employee = (Employee) request.getAttribute("employee");
	%>
	<form:form action="CreateEmployee" method="post"
		modelAttribute="employee">
		<form:input type="hidden" id="id" path="id" />
		<table>
			<tr>
				<td><form:label path="name"> Name </form:label></td>
				<td><form:input path="name" required="required" /></td>
			</tr>
			<tr>
				<td><form:label path="phoneNumber">Phone number</form:label></td>
				<td><form:input type="tel" path="phoneNumber"
						pattern="(0/91)?[7-9][0-9]{9}" required="required" /></td>
			</tr>
			<tr>
				<td><form:label path="emailId">Email ID</form:label></td>
				<td><form:input type="email" path="emailId" required="required" /></td>
			</tr>
			<tr>
				<td><form:label path="dateOfBirth">DOB</form:label></td>
				<td><form:input type="date" path="dateOfBirth"
						max="<%=LocalDate.now()%>" required="required" /></td>
			</tr>
			<tr>
				<td><form:label path="dateOfJoining">DOJ</form:label></td>
				<td><form:input type="date" path="dateOfJoining"
						required="required" /></td>
			</tr>
		</table>
		<table>
			<tr>
				<td><form:input type="hidden" path="addressList[0].id" /></td>
				<td><form:input type="hidden" path="addressList[0].isPermanent" />
				</td>
			</tr>
			<tr>
				<td><form:label path="addressList[0].doorNumber"> Door number</form:label></td>
				<td><form:input path="addressList[0].doorNumber"
						required="required" /></td>
			</tr>
			<tr>
				<td><form:label path="addressList[0].streetNumber"> street number</form:label></td>
				<td><form:input path="addressList[0].streetNumber"
						required="required" /></td>
			</tr>
			<tr>
				<td><form:label path="addressList[0].city"> city</form:label></td>
				<td><form:input path="addressList[0].city" required="required" /></td>
			</tr>
			<tr>
				<td><form:label path="addressList[0].district"> district</form:label></td>
				<td><form:input path="addressList[0].district"
						required="required" /></td>
			</tr>
			<tr>
				<td><form:label path="addressList[0].state"> state</form:label></td>
				<td><form:input path="addressList[0].state" required="required" /></td>
			</tr>
			<tr>
				<td><form:label path="addressList[0].country"> country</form:label></td>
				<td><form:input path="addressList[0].country"
						required="required" /></td>
			</tr>
			<tr>
				<td><form:label path="addressList[0].pincode"> pincode</form:label></td>
				<td><form:input type="number" path="addressList[0].pincode"
						required="required" /></td>
			</tr>
		</table>

		<table>
			<tr>
				<td><form:input type="hidden" path="addressList[1].id" /></td>
				<td><form:input type="hidden" path="addressList[1].isPermanent" />
				</td>
			</tr>
			<tr>
				<td><form:label path="addressList[1].doorNumber"> Door number</form:label></td>
				<td><form:input path="addressList[1].doorNumber"
						required="required" /></td>
			</tr>
			<tr>
				<td><form:label path="addressList[1].streetNumber"> street number</form:label></td>
				<td><form:input path="addressList[1].streetNumber"
						required="required" /></td>
			</tr>
			<tr>
				<td><form:label path="addressList[1].city"> city</form:label></td>
				<td><form:input path="addressList[1].city" required="required" /></td>
			</tr>
			<tr>
				<td><form:label path="addressList[1].district"> district</form:label></td>
				<td><form:input path="addressList[1].district"
						required="required" /></td>
			</tr>
			<tr>
				<td><form:label path="addressList[1].state"> state</form:label></td>
				<td><form:input path="addressList[1].state" required="required" /></td>
			</tr>
			<tr>
				<td><form:label path="addressList[1].country"> country</form:label></td>
				<td><form:input path="addressList[1].country"
						required="required" /></td>
			</tr>
			<tr>
				<td><form:label path="addressList[1].pincode"> pincode</form:label></td>
				<td><form:input type="number" path="addressList[1].pincode"
						required="required" /></td>
			</tr>
		</table>
		<%
			if (employee.getId() == 0) {
		%>
		<input type="submit" value="Create" />
		<%
			}
		%>
		<%
			if (employee.getId() != 0) {
			for (int i = 0; i < employee.getAddressList().size(); i++) {
				employee.setAddressList(employee.getAddressList());
			}
		%>
		<button type="submit" formaction="UpdateEmployee">Update</button>
		<%
			}
		%>
	</form:form>
</body>
</html>