package idv.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import idv.service.*;

/**
 * Servlet implementation class Commander
 */
public class Commander extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private FileInfoService fileInfoService;
	private DiskInfoService diskInfoService;
	String webpath;

	public Commander() {
		super();
		this.fileInfoService = new FileInfoService();
		this.diskInfoService = new DiskInfoService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object navPath=(String) request.getSession().getAttribute("navPath");
		if(navPath !=null) {
			webpath=(String) navPath;
		}
		else {
			webpath = request.getRealPath("/");					
		}
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		fileInfoService.setFileInfos(webpath);
		diskInfoService.setDiskInfo(webpath);

		request.setAttribute("usableSpace", diskInfoService.getUsableSpace());
		request.setAttribute("totalSpace", diskInfoService.getTotalSpace());
		request.setAttribute("folderList", fileInfoService.getFolderList());
		request.setAttribute("fileList", fileInfoService.getFileList());
		request.setAttribute("totalSize", fileInfoService.getTotalSize());
		request.setAttribute("fileCount", fileInfoService.getFileCount());
		request.setAttribute("folderCount", fileInfoService.getFolderCount());
		
		request.getSession().setAttribute("currentFolder", webpath);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

}
