package idv.controller;

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
		System.out.println(renameItem);
		response.sendRedirect(request.getContextPath() + "/Commander");
	}

}
