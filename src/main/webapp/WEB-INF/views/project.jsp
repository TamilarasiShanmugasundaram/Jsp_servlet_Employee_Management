<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
	<form>
	<p>
		<button formaction="index">Go to main menu</button>
	</p>
	</form>
	<form>
		<p>
			<button formaction="getProject">Create</button>
		</p>
	</form>
	<form>
		<p>
			<button formaction="getIdfordeleteProject">Delete</button>
		</p>
	</form>

	<form action="DisplayAllProject">
		<p>
			<input type="submit" name="operation" value="DisplayAllProject">
		</p>
	</form>
	<form>
		<p>
			<button formaction="getProjectIdForDisplay">Display
				single project</button>
		</p>
	</form>
	<form>
	<p>
		<button formaction="getIdForProjectUpdate">Update</button>
	</p>
	</form>
	<form>
	<p>
		<button formaction="assignEmployee">Assign</button>
	</p>
	</form>
	<form>
	<p>
		<button formaction="unassignEmployee">Unassign</button>
	</p>
	</form>
	<p>
	<form action="Retrieve">
		<input type="submit" name="operation" value="Retrieve">
	</form>
	</p>
</body>
</html>