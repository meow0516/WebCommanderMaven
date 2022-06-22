<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
    <%@page import="java.util.*"%>
<%@page import="idv.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Web Commander(Maven)</title>
</head>
<body>
	<div>Current Folder:</div>
	<div>
		Drive:<select name="History Path">
		</select>
		<div>
			<form method="post">
				<input type="hidden" name="nav" value="rootPath" />
				<button>/</button>
			</form>
			<form method="post">
				<input type="hidden" name="nav" value="prevFolder" />
				<button>...</button>
			</form>
		</div>
		<div>
			<span> Free Space: GB / </span> <span> Total Space: GB </span>
		</div>
		<div>
			History Path: <select name="History Path">
			</select>
		</div>

		<div>
			<table>
				<thead>
					<tr>
						<th></th>
						<th>Name</th>
						<th>Last Modified Time</th>
						<th>Size(bytes)</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
	<div>
		<h3>Folder State</h3>

		<span class="totalSize"></span>/ bytes | <span class="totalFileCount"></span>
		/ files | <span class="totalFolderCount"></span> / dirs

	</div>
	<div>
		<div>
			<form method="post">
				<input type="hidden" name="webpath" /> <input type="text"
					name="createItem">
				<button>Create</button>
			</form>
		</div>
		<div>
			<form method="post">
				<input type="hidden" name="webpath" /> <input type="text"
					class="renameItem" name="renameItem" /> to:<input type="text"
					name="newFileName" />
				<button>Rename</button>
			</form>
		</div>
		<div>
			<form method="post">
				<input type="hidden" name="webpath" /> <input type="text"
					name="deleteItem" class="deleteItem">
				<button>Delete</button>
			</form>
		</div>
	</div>

	<form method="post" action="Logout">
		<Button>Logout</Button>
	</form>
</body>
</html>

<style>
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

td {
	padding: 5px 10px;
}

div {
	padding-top: 10px;
}
</style>
