<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import=" com.ideas2it.employeeManagementSystem.employee.model.Address"
	import=" com.ideas2it.employeeManagementSystem.employee.model.Employee"%>
<%@ page import="java.util.List"%>
<%@ page import="java.time.LocalDate"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="UpdateEmployee" method="post">
		<%
			Employee employee = (Employee) request.getAttribute("employee");
		List<Address> addressList = employee.getAddressList();
		Address permanentAddress = addressList.get(0);
		Address temporaryAddress = addressList.get(1);
		System.out.println(addressList.size());
		%>
		<input type="hidden" id="id" name="id" value="<%=employee.getId()%>">
		<label>Name </label><input type="text" value="<%=employee.getName()%>"
			name="name" required><br> <label>Enter phone
			number </label><input type="tel" value="<%=employee.getPhoneNumber()%>"
			name="phonenumber" pattern="(0/91)?[7-9][0-9]{9}" required><br>
		<label>Enter email id : </label> <input type="email"
			value="<%=employee.getEmailId()%>" name="emailId" required><br>
		<label>Enter DOB </label> <input type="date"
			value="<%=employee.getDateOfBirth()%>" name="dob"
			max="<%=LocalDate.now()%>" required><br> <label>Enter
			DOJ </label><input type="date" value="<%=employee.getDateOfJoining()%>"
			name="doj" required><br> <label>**Enter
			Permanent Address** </label><br> <label>Door number : </label><input
			type="text" value="<%=permanentAddress.getDoorNumber()%>"
			name="permanentDoorNumber" required><br> <label>Street
			number/Name </label> <input type="text"
			value="<%=permanentAddress.getStreetNumber()%>"
			name="permanentStreetNumber" required><br> <label>City
			: </label><input type="text" value="<%=permanentAddress.getCity()%>"
			name="permanentCity" required><br> <label>District
			:</label> <input type="text" value="<%=permanentAddress.getDistrict()%>"
			name="permanentDistrict" required><br> <label>State
			: </label><input type="text" value="<%=permanentAddress.getState()%>"
			name="permanentState" required><br> <label>Country
			:</label> <input type="text" value="<%=permanentAddress.getCountry()%>"
			name="permanentCountry" required><br> <label>pincode
			:</label> <input type="number" value="<%=permanentAddress.getPincode()%>"
			name="permanentPincode" required><br> <label>**Enter
			temporary address** </label><br> <label>Door number : </label><input
			type="text" value="<%=temporaryAddress.getDoorNumber()%>"
			name="temporaryDoorNumber" required><br> <label>
			Street number/name :</label> <input type="text"
			value="<%=temporaryAddress.getStreetNumber()%>"
			name="temporaryStreetNumber" required><br> <label>City
			:</label> <input type="text" value="<%=temporaryAddress.getCity()%>"
			name="temporaryCity" required><br> <label>
			District :</label> <input type="text"
			value="<%=temporaryAddress.getDistrict()%>" name="temporaryDistrict"
			required><br> <label> State :</label> <input type="text"
			value="<%=temporaryAddress.getState()%>" name="temporaryState"
			required><br> <label>Country :</label> <input
			type="text" value="<%=temporaryAddress.getCountry()%>"
			name="temporaryCountry" required><br> <label>pincode
			:</label> <input type="number" value="<%=temporaryAddress.getPincode()%>"
			name="temporaryPincode" required><br> <input
			type="submit" name="operation" value="update">
	</form>
</body>
</html>