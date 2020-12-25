package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Image;
import service.ImageService;
import util.ResponseBuilder;
import util.ResponseCode;

@WebServlet("/images")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ImageService imageService = new ImageService();

	public ImageServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");

		/*
		 * 从数据库查询image数据表记录（分页查询、基于ID查询）
		 */
		List<Image> imageList = null;
		Image image = null;
		if (session.getAttribute("USERNAME") != null) {
			if (request.getParameter("pno") != null && request.getParameter("count") != null
					&& request.getParameter("id") == null) { // 分页查询
				try {
					imageList = imageService.getImagesPaged(Integer.valueOf(request.getParameter("pno")),
							Integer.valueOf(request.getParameter("count")));

					response.getWriter().append(ResponseBuilder.createJson(imageList));
				} catch (NumberFormatException | SQLException e) {
					e.printStackTrace();
					response.getWriter().append(ResponseBuilder.createJson(ResponseCode.SERVICE_ERROR));
				}
			} else if (request.getParameter("id") != null && request.getParameter("pno") == null
					&& request.getParameter("count") == null) { // 根据ID查询
				try {
					image = imageService.getImageById(Integer.valueOf(request.getParameter("id")));
					response.getWriter().append(ResponseBuilder.createJson(image));
				} catch (NumberFormatException | SQLException e) {
					e.printStackTrace();
					response.getWriter().append(ResponseBuilder.createJson(ResponseCode.SERVICE_ERROR));
				}
			} else {
				response.getWriter().append(ResponseBuilder.createJson(ResponseCode.QUERY_FAILED));
			}
		} else {
			response.getWriter().append(ResponseBuilder.createJson(ResponseCode.INVALID_USER));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
