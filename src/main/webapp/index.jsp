<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@page import="java.util.*"%>
<%@page import="idv.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Web Commander(Maven)</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<div>
		Current Folder:<%=session.getAttribute("currentFolder")%></div>
	<div>
		<div>
			<form method="post" action="Navigation">
				<input type="hidden" name="nav" value="rootFolder" />
				<button>/</button>
			</form>
			<form method="post" action="Navigation">
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
			History Path: <select>
				<%
				Set<String> historyPath = (Set<String>) request.getAttribute("historyPath");
				for (String path : historyPath) {
				%>
				<option><%=path%></option>
				<%
				}
				%>
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
							<form method="post" action="Navigation">
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
		<div>
			<form method="post" action="CreateFile">
				<input type="text" name="createItem">
				<button>Create</button>
			</form>
		</div>
		<div>
			<form method="post" action="RenameFile">
				<input type="hidden" class="renameItem" name="renameItem" /> Rename
				as:<input type="text" name="newFileName" />
				<button>Rename</button>
			</form>
		</div>
		<div>
			<form method="post" action="DeleteFile">
				<input type="hidden" name="deleteItem" class="deleteItem">
				<button>Delete</button>
			</form>
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
		<form method="post" action="Logout">
			<Button>Logout</Button>
		</form>
	</div>
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

<script>
	$(document).ready(
			function() {
				var selectItems = new Array();
				var totalSelectedSize = 0;
				var fileCount = 0;
				var folderCount = 0;
				$('.selectFile').on(
						'change',
						function() {
							if ($(this).is(":checked")) {
								selectItems.push($(this).val());
								if ($(this).hasClass("fileCount")) {
									fileCount += 1;
									totalSelectedSize += parseInt($(this)
											.parent().siblings('.fileSize')
											.text());

								} else if ($(this).hasClass("folderCount")) {
									folderCount += 1;
								}
							}

							else {
								var x = selectItems.indexOf($(this).val());
								selectItems.splice(x, 1);
								if ($(this).hasClass("fileCount")) {
									fileCount -= 1;
									totalSelectedSize -= parseInt($(this)
											.parent().siblings('.fileSize')
											.text());
								} else if ($(this).hasClass("folderCount")) {
									folderCount -= 1;
								}

							}
							$('.renameItem').val(selectItems);
							$('.deleteItem').val(selectItems);
							$('.totalFileCount').text(fileCount);
							$('.totalFolderCount').text(folderCount);
							$('.totalSize').text(totalSelectedSize);
						})
			});
</script>
