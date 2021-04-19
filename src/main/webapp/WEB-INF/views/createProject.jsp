<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="org.springframework.web.bind.annotation.ModelAttribute"%>
<%@ page import="org.springframework.web.servlet.tags.form.FormTag"%>
<%@ page import="org.springframework.web.servlet.tags.form.LabelTag"%>
<%@ page import="org.springframework.web.servlet.tags.form.InputTag"%>
<%@ page import="java.sql.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
	<h1>Create Project</h1>
	<form:form action="createProject" method="post"
		modelAttribute="project">
		<table>
			<tr>
				<td><form:label path="title"> Title </form:label></td>
				<td><form:input path="title" required="required" /></td>
			</tr>
			<tr>
				<td><form:label path="startDate"> Start date</form:label></td>
				<td><form:input type="date"  path="startDate"
						required="required" /></td>
			</tr>
			<tr>
				<td><form:label path="estimatedEndDate">End date</form:label></td>
				<td><form:input type="date" path="estimatedEndDate"
						required="required" /></td>
			</tr>
			<tr>
				<td><form:label path="status">Status</form:label></td>
				<td><form:input path="status" required="required" /></td>
			</tr>
			<tr>
				<td><form:label path="client">Client</form:label></td>
				<td><form:input path="client" required="required" /></td>
			</tr>
			<tr>
				<td><form:label path="budget">Budget</form:label></td>
				<td><form:input type="number" path="budget" required="required" /></td>
			</tr>
		</table>
			<input type="submit" value="Create">
	</form:form>
</body>
</html>