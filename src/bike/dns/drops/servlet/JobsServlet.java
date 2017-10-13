package bike.dns.drops.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bike.dns.drops.entity.Article;
import bike.dns.drops.entity.Job;
import bike.dns.drops.entity.User;
import bike.dns.drops.service.ArticleService;
import bike.dns.drops.service.JobsService;
import bike.dns.drops.utils.Crawler;
import bike.dns.drops.web.CriteriaJob;
import bike.dns.drops.web.Page;

/**
 * 处理添加文章请求
 */
@WebServlet("/JobsServlet")
public class JobsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String viewBaseDir = "/WEB-INF/views/jobs/";
	private JobsService jobsService = new JobsService();
	private ArticleService articleService = new ArticleService();

	public JobsServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String methodName = request.getParameter("method");
		if (null == methodName | "".equals(methodName)) {
			methodName = "addJob";
		}
		try {
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class,
					HttpServletResponse.class);
			method.setAccessible(true);
			method.invoke(this, request, response);
		} catch (Exception e) {
			request.getRequestDispatcher("/error.html");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/*
	 * 添加爬取文章的界面
	 */
	protected void addJob(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(viewBaseDir + "addarticle.jsp").forward(request, response);
	}

	/*
	 * 处理添加爬取文章请求的逻辑
	 */
	protected void doAddJob(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = request.getParameter("title");
		String url = request.getParameter("url");
		String category = request.getParameter("category");

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String submitUserName = user.getUserName();

		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String submitTime = dataFormat.format(new Date());

		Job job = new Job(title, url, 0, category, submitUserName, submitTime, submitTime, 0, "");

		long num = articleService.getTotalArticleNumberByOriginUrl(url);
		if (num > 0) {
			request.setAttribute("message", "文章已收录，无需爬取，请直接搜索！");
		} else {
			long result = jobsService.createJob(job);
			if (result > 0) {
				request.setAttribute("message", "爬取文章申请已提交，请耐心等待管理员处理！");
			} else {
				request.setAttribute("message", "文章已收录，无需爬取，请直接搜索！");
			}
		}

		request.getRequestDispatcher(viewBaseDir + "addarticle.jsp").forward(request, response);
	}

	/*
	 * 显示用户添加的爬取请求及状态
	 */
	protected void getJobs(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		} catch (Exception e) {
			// TODO: handle exception
		}

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		CriteriaJob cj = new CriteriaJob(user, pageNo);

		Page<Job> page = jobsService.getPage(cj, 0);// 0 则只显示当前用户的爬取列表
		request.setAttribute("page", page);
		request.getRequestDispatcher(viewBaseDir + "jobs.jsp").forward(request, response);

	}

	/*
	 * 显示用户添加的爬取请求
	 */
	protected void showSingleJob(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		// 进行权限校验
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user.getIsAdmin() != 1) {
			response.sendRedirect("./error.html");
			return;
		}

		Job job = jobsService.getSingleJob(id);
		request.setAttribute("job", job);
		request.getRequestDispatcher(viewBaseDir + "singlejob.jsp").forward(request, response);

	}

	/*
	 * 显示用户添加的爬取请求及状态
	 */
	protected void manageJobs(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		} catch (Exception e) {
			// TODO: handle exception
		}

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user.getIsAdmin() != 1) {
			response.sendRedirect("./error.html");
			return;
		}

		CriteriaJob cj = new CriteriaJob(user, pageNo);

		Page<Job> page = jobsService.getPage(cj, 1);// 1 则显示所有用户的爬取列表
		request.setAttribute("page", page);
		request.getRequestDispatcher(viewBaseDir + "managejobs.jsp").forward(request, response);

	}

	protected void doManageJobs(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user.getIsAdmin() != 1) {
			response.sendRedirect("./error.html");
			return;
		}

		String title = "";
		String url = "";
		String category = "";
		String submitUserName = "";
		String author = "";
		String createTime = "";
		int jobId = 0;

		title = request.getParameter("title");
		url = request.getParameter("url");
		category = request.getParameter("category");
		submitUserName = request.getParameter("submitUserName");
		author = request.getParameter("author");
		createTime = request.getParameter("createTime");
		try {
			jobId = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
		}

		
		// craw
		Article article = null;
		article = Crawler.commonCraw(url, category, title, author, createTime);
		// jobsService.
		if (article != null) {
			long result = articleService.createArticle(article);
			if (result > 0) {
				SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
				String dealTime = dataFormat.format(new Date());
				Job job = new Job(null, url, 0, null, null, null, dealTime, 1, null);
				jobsService.updateJobStatus(job);
				request.setAttribute("message", "添加成功！");
				request.getRequestDispatcher(viewBaseDir + "singlejob.jsp").forward(request, response);
				return;
			}
		}
		request.setAttribute("message", "添加失败！");
		request.getRequestDispatcher(viewBaseDir + "singlejob.jsp").forward(request, response);
	}

}
