package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;

import model.Image;

@WebServlet("/images")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ImageServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		if (session.getAttribute("USERNAME") != null) {
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json; charset=utf-8");

			Image im1 = new Image();
			im1.setId(1);
			im1.setTitle("Car");
			im1.setDescription("This is a car image.");
			im1.setPath("images/car.jpg");
			im1.setTimestamp(new Date());

			Image im2 = new Image();
			im2.setId(2);
			im2.setTitle("Trees");
			im2.setDescription("This is a trees image.");
			im2.setPath("images/trees.jpg");
			im2.setTimestamp(new Date());

			Image im3 = new Image();
			im3.setId(3);
			im3.setTitle("Girl");
			im3.setDescription("This is a girl image.");
			im3.setPath("images/girl.jpg");
			im3.setTimestamp(new Date());

			List<Image> imageList = new ArrayList<Image>();
			imageList.add(im1);
			imageList.add(im2);
			imageList.add(im3);

			List<Image> subImageList = new ArrayList<Image>();

			for (int i = 0; i < Integer.valueOf(request.getParameter("count")); i++) {
				subImageList.add(imageList.get(i));
			}

			String jsonOutput = JSON.toJSONString(subImageList);
			response.getWriter().append(jsonOutput);

		} else {
			response.getWriter().append("User Invalid.");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
