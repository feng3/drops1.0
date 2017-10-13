package bike.dns.drops.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//判断登录
@WebFilter("/LoginFilter")
public class LoginFilter implements Filter {

	public LoginFilter() {
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
	
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		//获取请求的uri
		String[] uris = req.getRequestURI().split("/");
		String servlet = "";
		try {
			servlet = uris[uris.length - 1].trim();
		} catch (Exception e) {
		}
		//如果是drops.do和jobs.do则要求登录
		if ("drops.do".equals(servlet) | "jobs.do".equals(servlet)) {
			if (req.getSession().getAttribute("user") == null) {
				resp.sendRedirect(req.getContextPath() + "/user.do");
				return;
			}
		}

		chain.doFilter(request, response);
	}


}
