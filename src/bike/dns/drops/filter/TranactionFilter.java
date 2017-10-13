package bike.dns.drops.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bike.dns.drops.db.JDBCUtils;
import bike.dns.drops.web.ConnectionContext;

//存储过程
@WebFilter("/TranactionFilter")
public class TranactionFilter implements Filter {

    public TranactionFilter() {
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }
    
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Connection connection = null;
		try {
			connection = JDBCUtils.getConnection();
			connection.setAutoCommit(false);
			ConnectionContext.getInstance().bind(connection);
			chain.doFilter(request, response);
			connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.sendRedirect(req.getContextPath() + "/error.html");
		} finally {
			ConnectionContext.getInstance().remove();
			JDBCUtils.release(connection);
		}
	}


}
