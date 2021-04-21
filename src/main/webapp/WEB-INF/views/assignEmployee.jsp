<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
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
			<%
			@SuppressWarnings("unchecked")
		List<Employee> employeeList = (List<Employee>) request.getAttribute("Employees");
		%>
	<p>
		<button formaction= "Project">Project</button>
		</p>
	</form>
<form action="AssignEmployee" method="post" >
<label> Enter Project id : </label>
   <input type= "number" name ="projectId" required><br>
 
 		<%
			for (Employee employee : employeeList) {
			
		%>
		<p>
			<input type="checkbox"  name="employeeIds"
				value="<%=employee.getId()%>">
			<%
				out.println(employee.getName());
			}
		%>
		</p>
 
<input type="submit" name="operation" value="AssignEmployee">
</form>
</body>
</html>