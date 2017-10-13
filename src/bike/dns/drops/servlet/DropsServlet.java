package bike.dns.drops.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bike.dns.drops.entity.Article;
import bike.dns.drops.service.ArticleService;
import bike.dns.drops.web.CriteriaArticle;
import bike.dns.drops.web.Page;
import bike.dns.drops.web.SearchArticle;

//处理文章相关请求
@WebServlet("/DropsServlet")
public class DropsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String viewBaseDir = "/WEB-INF/views/drops/";
	
	private ArticleService articleService = new ArticleService();
       
    public DropsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String methodName = request.getParameter("method");
		if (null == methodName | "".equals(methodName)) {
			methodName = "getDrops";
		}
		
		try {
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			method.setAccessible(true);
			method.invoke(this, request, response);
		} catch (Exception e) {
			request.getRequestDispatcher("/error.html");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	//获取文章列表, 有 类别、 作者、 来源 三个维度
	protected void getDrops(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String category = null;
		String author = null;
		String sourceSite = null;
		String pageNoStr = null;
		int pageNo = 1;
		
		category = request.getParameter("category");
		author = request.getParameter("author");
		sourceSite = request.getParameter("sourceSite");
		pageNoStr = request.getParameter("pageNo");
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		CriteriaArticle criteriaArticle = new CriteriaArticle(category, null, author, null, null, sourceSite, pageNo);
		
		Page<Article> page = articleService.getPage(criteriaArticle);
		request.setAttribute("page", page);
		request.getRequestDispatcher(viewBaseDir + "drops.jsp").forward(request, response);
	}
	
	//根据 id 来获取单一文章
	protected void getDrop(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		int id = -1;
		
		Article article = null;
		
		try {
			id = Integer.parseInt(idStr);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if (id > 0) {
			article = articleService.getArticle(id);
		}
		
		if (article == null) {
			response.sendRedirect(request.getContextPath() + "/error.html");
			return;
		}
		
		request.setAttribute("article", article);
		
		request.getRequestDispatcher(viewBaseDir + "singleDrop.jsp").forward(request, response);
	}
	
	//搜索 文章， 在全文中进行搜索
	protected void searchDrops(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchContent = null;
		String pageNoStr = null;
		int pageNo = 1;
		
		searchContent = request.getParameter("keyword");
		pageNoStr = request.getParameter("pageNo");
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {
		}
		
		SearchArticle searchArticle = new SearchArticle(searchContent, pageNo);
		Page<Article> page = articleService.getSearchPage(searchArticle);
		request.setAttribute("page", page);
		request.getRequestDispatcher(viewBaseDir + "searchDrops.jsp").forward(request, response);
	}

}
