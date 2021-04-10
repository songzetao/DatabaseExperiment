package filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.ResponseBuilder;
import util.ResponseCode;

/**
 * Servlet Filter implementation class ValidFilter
 */
@WebFilter("")
public class ValidFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ValidFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		//过滤器可以不止一个，过滤器可以组成链
		// pass the request along the filter chain
		//传递到servlet要写在这行代码上面
//		HttpServletRequest httpRequest = (HttpServletRequest) request;
//		HttpServletResponse httpResponse = (HttpServletResponse) response;
//		System.err.println(new Date() + " ValidateFilter " + httpRequest.getRequestURI());
//
//		if (httpRequest.getSession().getAttribute("USERNAME") != null) {
//			chain.doFilter(request, response);
//			System.out.println("通过过滤器");
//		} else {
//			httpResponse.getWriter().append(ResponseBuilder.createJson(ResponseCode.INVALID_USER));
//			System.out.println("未通过过滤器");
//		}
		//回来的返回值，对他们进行操作，代码要写在这行代码下面
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
