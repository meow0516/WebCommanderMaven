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
			<span> Free Space: <%=request.getAttribute("usableSpace")%> GB
				/
			</span> <span> Total Space:<%=request.getAttribute("totalSpace")%> GB
			</span>
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
					<%
					List<FileInfo> folderList = (List<FileInfo>) request.getAttribute("folderList");
					for (FileInfo folderItem : folderList) {
					%>
					<tr>
						<td><input type="checkbox" class="selectFile folderCount"
							value="<%=folderItem.getFileName()%>" /></td>
						<td>
							<form method="post" action="CommanderController">
								<input type="hidden" name="nav"
									value="<%=folderItem.getFileName()%>">
								<button class="btn-link"><%=folderItem.getFileName()%></button>
							</form>
						</td>
						<td><%=folderItem.getLastModified()%></td>
						<td>DIR</td>
					</tr>
					<%
					}
					%>
					<%
					List<FileInfo> fileList = (List<FileInfo>) request.getAttribute("fileList");
					for (FileInfo fileItem : fileList) {
					%>
					<tr>
						<td><input type="checkbox" class="selectFile fileCount"
							value="<%=fileItem.getFileName()%>" /></td>
						<td><%=fileItem.getFileName()%></td>
						<td><%=fileItem.getLastModified()%></td>
						<td class="fileSize"><%=fileItem.getSize()%></td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>
	</div>
	<div>
		<h3>Folder State</h3>

		<span class="totalSize"></span>/<%=request.getAttribute("totalSize")%>
		bytes | <span class="totalFileCount"></span> /<%=request.getAttribute("fileCount")%>
		files | <span class="totalFolderCount"></span> /<%=request.getAttribute("folderCount")%>
		dirs

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

.btn-link {
	background: none;
	border: none;
	color: blue;
	text-decoration: underline;
	cursor: pointer;
	font-size: 1em;
}
</style>
