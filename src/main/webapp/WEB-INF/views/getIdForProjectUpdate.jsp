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
		<button formaction="Project">Project</button>
	</p>
	</form>
<form action="UpdateProject" method="post">
   <label> Enter id : </label><input type= "number" name ="id" required><br>
<input type="submit" name="operation" value="UpdateProject">
</form>
</body>
</html>