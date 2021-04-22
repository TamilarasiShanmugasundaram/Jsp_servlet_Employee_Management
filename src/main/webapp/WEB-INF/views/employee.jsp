<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee</title>
</head>
<body>
	<form>
		<p>
			<button formaction="index">Go to main menu</button>
		</p>
	</form>
	<form>
		<p>
			<button formaction="getEmployee">Create</button>
		</p>
	</form>
	<form action="displayAllEmployee">
		<p>
			<input type="submit" name="operation" value="displayAllEmployee">
		</p>
	</form>
	<form>
		<p>
			<button formaction="getEmployeeId">Display single Employee</button>
		</p>
	</form>
</body>
</html>