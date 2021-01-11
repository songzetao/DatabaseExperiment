package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import model.Comment;
import model.Image;
import service.DetailService;
import util.ResponseBuilder;
import util.ResponseCode;

/**
 * Servlet implementation class detailServlet
 */
@WebServlet("/api/detail")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DetailService detailService ;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailServlet() {
        super();
        detailService=new DetailService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");

		/*
		 * 从数据库查询image数据表记录（分页查询、基于ID查询）
		 */
		Comment comment = new Comment();
		if (session.getAttribute("USERNAME") != null) {
			if (request.getParameter("id") != null && request.getParameter("comment") != null) { 
				try {
					//System.out.println(Integer.parseInt(request.getParameter("id")));
					comment.setImageID(Integer.parseInt(request.getParameter("id")));
					comment.setUsername((String)session.getAttribute("USERNAME"));
					comment.setComment(request.getParameter("comment"));
					
//					JSONObject jsonObject = JSONObject.parseObject(request.getParameter("comment"));
//					String str = jsonObject.getString("data");		//括号里面的是key值
//					
//					System.out.println(str);
					detailService.addComment(comment);
//					String result=ResponseBuilder.createJson(request.getParameter("comment"));
//					System.out.println(result);
					System.out.println(ResponseBuilder.createJson(comment));
					response.getWriter().append(ResponseBuilder.createJson(comment));
				} catch (NumberFormatException | SQLException e) {
					e.printStackTrace();
					//System.out.println("tag");
					response.getWriter().append(ResponseBuilder.createJson(ResponseCode.SERVICE_ERROR));
				}
//					imageList = imageService.getImagesPaged(Integer.valueOf(request.getParameter("pno")),
//							Integer.valueOf(request.getParameter("count")));
					//System.out.println(session.getAttribute("USERNAME")+request.getParameter("id")+request.getParameter("comment"));
					//response.getWriter().append(ResponseBuilder.createJson(commentList));
				
			}else if(request.getParameter("id") != null && request.getParameter("comment") == null)
				System.out.println("拿取评论");
				try {
					//System.out.println(Integer.parseInt(request.getParameter("id")));
					List<Comment> comments=null;
					comments=detailService.getCommentsById(Integer.parseInt(request.getParameter("id")));
					//System.out.println(comments.get(0).getComment());
					response.getWriter().append(ResponseBuilder.createJson(comments));
					//response.sendRedirect("./detail.html?id="+request.getParameter("id"));
				} catch (NumberFormatException | SQLException e) {
					e.printStackTrace();
					response.getWriter().append(ResponseBuilder.createJson(ResponseCode.SERVICE_ERROR));
				}
			
			} else {
				System.out.println("error!");
				response.getWriter().append(ResponseBuilder.createJson(ResponseCode.INVALID_USER));
			}
			
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
