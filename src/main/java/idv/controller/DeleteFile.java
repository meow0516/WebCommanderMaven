package idv.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteFile
 */
public class DeleteFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteFile() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String currentFolder = (String) request.getSession().getAttribute("currentFolder");
		String deleteFileName = request.getParameter("deleteItem");
		String[] protectFile = { "META-INF", "WEB-INF", "index.jsp", "login.jsp", "abc.txt" };

		if (deleteFileName.contains(",")) {
			PrintWriter out = response.getWriter();
			out.print("<script>alert('Please select only one file/folder.');</script>");
			response.sendRedirect(request.getContextPath() + "/Commander");
			return;
		}

		if (!Arrays.asList(protectFile).contains(deleteFileName)) {
			try {
				File f = new File(currentFolder + deleteFileName);
				if (f.delete()) {
					System.out.println(f.getName() + " deleted");
				} else {
					System.out.println("failed");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			PrintWriter out = response.getWriter();
			System.out.println("cannot delete");
			out.println("<script>alert('Cannot delete this file');</script>");
		}
		response.sendRedirect(request.getContextPath() + "/Commander");
	}

}
