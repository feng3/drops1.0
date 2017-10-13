package bike.dns.drops.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

//请求字符编码
@WebFilter("/EncodingFilter")
public class EncodingFilter implements Filter {
	private FilterConfig filterConfig = null;

    public EncodingFilter() {
    }

    public void init(FilterConfig fConfig) throws ServletException {
    	this.filterConfig = fConfig;
    }
    
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String encoding = filterConfig.getServletContext().getInitParameter("encoding");
		request.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
	}

}
