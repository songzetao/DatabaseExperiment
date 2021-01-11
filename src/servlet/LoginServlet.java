package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.ResponseBuilder;
import util.ResponseCode;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("USERNAME") != null) {
			response.getWriter().append(ResponseBuilder.createJson(session.getAttribute("USERNAME")));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		HttpSession session = request.getSession();

		if ("admin".equals(username) && "admin".equals(password)) {
			session.setAttribute("USERNAME", username);
			// response.getWriter().append(ResponseBuilder.createJson(ResponseCode.USER_LOGIN_SUCCESS));
			// 此处用页面跳转的方式
			response.sendRedirect("index.html");
		} else {
			// response.getWriter().append(ResponseBuilder.createJson(ResponseCode.USER_LOGIN_FAILED));
			response.sendRedirect("login.html");
		}
	}

}
