package idv.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import idv.service.UserInfoService;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserInfoService userInfoService;

	public Login() {
		super();
		this.userInfoService = new UserInfoService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object isLogin = session.getAttribute("isLogin");
		if (isLogin!=null) {
			response.sendRedirect(request.getContextPath() + "/Commander");
		}
		else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String loginInfo = request.getParameter("loginInfo");
		userInfoService.setUserInfo(loginInfo);

		session.setAttribute("isLogin", userInfoService.getUserInfo().isLogin());
		response.sendRedirect(request.getContextPath() + "/Commander");
	}

}
