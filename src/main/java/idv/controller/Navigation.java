package idv.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Navigation
 */
public class Navigation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Navigation() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nav=request.getParameter("nav");
		String serverPath = request.getRealPath("/");
		String currentFolder=(String) request.getSession().getAttribute("currentFolder");
		String webpath;

		if(nav.equals("rootFolder")) {
			webpath=serverPath;
		}
		else if(nav.equals("prevFolder")) {
			int indexNumber = currentFolder.lastIndexOf("\\", currentFolder.lastIndexOf("\\") - 1);
			String prevFolder = currentFolder.substring(0, indexNumber + 1);
			webpath = prevFolder;
		}
		else {
			webpath=currentFolder+nav+"\\";
		}
		System.out.println(webpath);
		request.getSession().setAttribute("navPath", webpath);
		response.sendRedirect(request.getContextPath() + "/Commander");
	}

}
