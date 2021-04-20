<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page  import =" com.ideas2it.employeeManagementSystem.project.model.Project" %>
    <%@ page  import ="java.util.List" %>
     
        
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
	<form> 
	<p>
		<button formaction= "Project">Project</button>
		</p>
	</form>
		<form>
	<p>
		<button formaction="Employee">Employee</button>
	</p>
	</form>
<form>
	<p>
		<button formaction="index">Go to main menu</button>
	</p>
	</form>
	<h3><%out.println(request.getAttribute("Message"));%></h3>
</body>
</html>