<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
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
<form action="AssignEmployee" method="post" >
<label> Enter Project id : </label>
   <input type= "text" name ="projectId" required><br>
   
  <input type="checkbox" id="vehicle1" name="vehicle1" value="Bike">
  <label for="vehicle1"> I have a bike</label><br>
  <input type="checkbox" id="vehicle2" name="vehicle2" value="Car">
  <label for="vehicle2"> I have a car</label><br>
  <input type="checkbox" id="vehicle3" name="vehicle3" value="Boat">
  <label for="vehicle3"> I have a boat</label><br><br>
  <input type="submit" value="Submit">
    <input type= "text" name ="employeeId" required><br>
<input type="submit" name="operation" value="AssignEmployee">
</form>
</body>
</html>