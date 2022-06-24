package idv.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RenameFile
 */
public class RenameFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RenameFile() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currentFolder = (String) request.getSession().getAttribute("currentFolder");
		String renameItem = request.getParameter("renameItem");
		String newFileName = request.getParameter("newFileName");
		File file = new File(currentFolder + renameItem);
		File rename = new File(currentFolder + newFileName);

		boolean flag = file.renameTo(rename);
		if (flag == true) {
			System.out.println("File Successfully Rename");
		} else {
			System.out.println("Operation Failed");
		}
		
		response.sendRedirect(request.getContextPath() + "/Commander");
	}

}
