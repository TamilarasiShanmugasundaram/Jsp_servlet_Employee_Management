<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import = "java.time.LocalDate"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
	<h1>Create Employee</h1>
	<form action="CreateEmployee" method="post">
		<label>Name </label><input type="text" name="name" required><br> <label>Enter
			phone number </label><input type="tel" name="phonenumber" pattern="(0/91)?[7-9][0-9]{9}" required><br>
		<label>Enter email id : </label> <input type="email" name="emailId" required><br>
		<label>Enter DOB </label> <input type="date" name="dob"  max="<%=LocalDate.now()%>" required><br>
		<label>Enter DOJ </label><input type="date" name="doj" required><br>
		<label>**Enter Permanent Address** </label><br> <label>Door
			number : </label><input type="text" name="permanentDoorNumber" required><br>
		<label>Street number/Name </label> <input type="text"
			name="permanentStreetNumber" required><br> <label>City :
		</label><input type="text" name="permanentCity" required><br> <label>District
			:</label> <input type="text" name="permanentDistrict" required><br> <label>State
			: </label><input type="text" name="permanentState" required><br> <label>Country
			:</label> <input type="text" name="permanentCountry" required><br> <label>pincode
			:</label> <input type="number" name="permanentPincode" required><br> <label>**Enter
			temporary address** </label><br> <label>Door number : </label><input
			type="text" name="temporaryDoorNumber" required><br>
		<label> Street number/name :</label> <input type="text"
			name="temporaryStreetNumber" required><br> <label>City :</label>
		<input type="text" name="temporaryCity" required><br>
		<label> District :</label> <input type="text" name="temporaryDistrict" required><br>
		<label> State :</label> <input type="text" name="temporaryState" required><br>
		<label>Country :</label> <input type="text" name="temporaryCountry" required><br>
		<label>pincode :</label> <input type="number" name="temporaryPincode" required><br>
		<input type="submit" name="operation" value="create">

	</form>
</body>
</html>