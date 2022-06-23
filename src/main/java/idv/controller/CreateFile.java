package idv.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateFile
 */
public class CreateFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateFile() {
		super();
	}

	public boolean checkExtension(String fileName) {
		boolean fe = false;
		char ch;
		int len;
		if (fileName == null || (len = fileName.length()) == 0 || (ch = fileName.charAt(len - 1)) == '/' || ch == '\\'
				|| ch == '.') {
			fe = false;
		}
		int dotInd = fileName.lastIndexOf('.'),
				sepInd = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));
		if (dotInd <= sepInd) {
			fe = false;
		} else {
			fe = true;
		}
		return fe;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String createFileName = request.getParameter("createItem");
		String currentFolder = (String) request.getSession().getAttribute("currentFolder");

		boolean isFile = checkExtension(createFileName);
		if (isFile) {
			File file = new File(currentFolder + createFileName);
			boolean result;
			try {
				result = file.createNewFile();
				if (result) {
					System.out.println("file created " + file.getCanonicalPath());
				} else {
					System.out.println("File already exist at location: " + file.getCanonicalPath());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			File folder = new File(currentFolder + createFileName);
			Boolean isCreated = folder.mkdir();
			if (isCreated) {
				System.out.println("Folder created");
			} else {
				System.out.println("Fail to create folder");
			}
		}
		response.sendRedirect(request.getContextPath() + "/Commander");
	}

}
