package bike.dns.drops.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bike.dns.drops.entity.User;
import bike.dns.drops.service.UserService;
import bike.dns.drops.utils.MD5;
import bike.dns.drops.utils.Mail;
import bike.dns.drops.utils.PropertiesUtil;
import bike.dns.drops.utils.RandomString;

//用户逻辑
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();
	private String viewBaseDir = "/WEB-INF/views/user/";
       
    public UserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String methodName = request.getParameter("method");
		if (null == methodName | "".equals(methodName)) {
			methodName = "index";
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
	
	/*
	 * 首页，登录页面
	 */
	protected void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("user") != null) {
			response.sendRedirect(request.getContextPath() + "/drops.do");
			return;
		}
		request.getRequestDispatcher(viewBaseDir + "signin.jsp").forward(request, response);
	}
	
	/*
	 * 登录的逻辑
	 */
	protected void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String userName = null;
		String password = null;
		String verifyCode = "";
		//如果已经登录则直接转到drops.do页面
		if (request.getSession().getAttribute("user") != null) {
			response.sendRedirect(request.getContextPath() + "/drops.do");
			return;
		}
		
		userName = request.getParameter("username");
		password = request.getParameter("password");
		verifyCode = request.getParameter("verifyCode");
		
		//获取kaptcha生成存放在session中的验证码
		String kaptchaValue = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		//比较输入的验证码和实际生成的验证码是否相同
		if(kaptchaValue == null || kaptchaValue == ""||!verifyCode.equalsIgnoreCase(kaptchaValue)) {
			request.setAttribute("message", "验证码错误！");
			request.getRequestDispatcher(viewBaseDir + "signin.jsp").forward(request, response);
			request.getSession().invalidate();
			return;
		}
		
		User user = userService.getUser(userName);
		
		if (user != null && MD5.getMD5Hash(password + user.getSalt()).equals(user.getPassword())) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			//更新登录信息： 登录时间、IP
			SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String lastLoginTime = dataFormat.format(new Date());
			user.setLastLoginTime(lastLoginTime);
			String lastLoginIP = request.getRemoteAddr();
			user.setLastLoginIP(lastLoginIP);
			userService.updateLoginInfo(user);
			
			response.sendRedirect(request.getContextPath() + "/drops.do");
		}else {
			request.setAttribute("message", "用户名或密码错误！");
			request.getRequestDispatcher(viewBaseDir + "signin.jsp").forward(request, response);
			request.getSession().invalidate();
		}
	}
	
	/*
	 * 登出逻辑
	 */
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath() + "/user.do");
	}
	
	/*
	 * 注册页面
	 */
	protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(viewBaseDir + "signup.jsp").forward(request, response);
	}
	
	/*
	 * 注册逻辑
	 */
	protected void doRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = null;
		String password = null;
		String salt = null;
		String pass = null;
		String email = null;
		String phone = null;
		String createTime = null;
		String verifyCode = "";
		String lastLoginTime = null;
		String registerIP = null;
		String lastLoginIP = null;
		
		userName = request.getParameter("username");
		pass = request.getParameter("password");
		email = request.getParameter("email");
		phone = request.getParameter("phone");
		verifyCode = request.getParameter("verifyCode");
		
		//获取kaptcha生成存放在session中的验证码
		String kaptchaValue = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		//比较输入的验证码和实际生成的验证码是否相同
		if(kaptchaValue == null || kaptchaValue == ""||!verifyCode.equalsIgnoreCase(kaptchaValue)) {
			request.setAttribute("message", "验证码错误！");
			request.getRequestDispatcher(viewBaseDir + "signup.jsp").forward(request, response);
			request.getSession().invalidate();
			return;
		}
		
		String errorMessage = "";
		if (userName == null | "".equals(userName) | pass == null | "".equals(pass)
				| email == null | "".equals(email) | phone == null | "".equals(phone)) {
			errorMessage = "用户名、密码、邮箱、手机号码 不能为空";
			request.setAttribute("message", errorMessage);
			request.getRequestDispatcher(viewBaseDir + "signup.jsp").forward(request, response);
		}else {
			SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			salt = RandomString.getRandomString(6);
			password = MD5.getMD5Hash(pass + salt);
			createTime = dataFormat.format(new Date());
			lastLoginTime = createTime;
			registerIP = request.getRemoteAddr();
			lastLoginIP = registerIP;
			//注册普通用户
			User user = new User(userName, password, salt, email, phone, createTime, lastLoginTime, registerIP, lastLoginIP, 0);
			long result = userService.register(user);
			if (result > 0) {
				response.sendRedirect(request.getContextPath() + "/success.html");
			}else {
				errorMessage = "用户名已存在!";
				request.setAttribute("message", errorMessage);
				request.getRequestDispatcher(viewBaseDir + "signup.jsp").forward(request, response);
			}
		}
	}
	
	/*
	 * 找回密码页面
	 */
	protected void findPasswd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(viewBaseDir + "findpasswd.jsp").forward(request, response);
	}
	
	/*
	 * 找回密码逻辑处理
	 */
	protected void doFindPasswd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = "";
		String verifyCode = "";
		
		email = request.getParameter("email");
		verifyCode = request.getParameter("verifyCode");
		
		//获取kaptcha生成存放在session中的验证码
		String kaptchaValue = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		//比较输入的验证码和实际生成的验证码是否相同
		if(kaptchaValue == null || kaptchaValue == ""||!verifyCode.equalsIgnoreCase(kaptchaValue)) {
			request.setAttribute("message", "验证码错误！");
			request.getRequestDispatcher(viewBaseDir + "findpasswd.jsp").forward(request, response);
			request.getSession().invalidate();
			return;
		}
		
		//检查邮箱是否存在
		User user = userService.getUserByEmail(email);
		if (user == null) {
			request.setAttribute("message", "邮箱输入有误！");
			request.getRequestDispatcher(viewBaseDir + "findpasswd.jsp").forward(request, response);
			return;
		}
		
		//生成随机的密码，通过邮件发送给用户
		String newPass = RandomString.getRandomString(8);
		String salt = RandomString.getRandomString(6);
		String password = MD5.getMD5Hash(newPass + salt);
		User newUser = new User("", password, salt, email, "", "", "", "", "", 0);
		userService.updatePassword(newUser);
		String emailName = PropertiesUtil.getProperty("config.properties", "emailName");
		String emailPass = PropertiesUtil.getProperty("config.properties", "emailPass");
		String smtp = PropertiesUtil.getProperty("config.properties", "smtp");
		Mail.sendMail(smtp, emailName, emailPass, "密码重置邮件", "您的新密码是 " + newPass, emailName, email);
		request.setAttribute("message", "密码重置邮件已发送，请查收！");
		request.getRequestDispatcher(viewBaseDir + "findpasswd.jsp").forward(request, response);
		return;
		
	}

	/*
	 * 个人中心页面
	 */
	protected void userCenter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(viewBaseDir + "usercenter.jsp").forward(request, response);
	}
	
	/*
	 * 修改密码页面
	 */
	protected void changePasswd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(viewBaseDir + "changepasswd.jsp").forward(request, response);
	}
	
	/*
	 * 修改密码逻辑
	 */
	protected void doChangePasswd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String originPwd = "";
		String pwd1 = "";
		String pwd2 = "";
		
		originPwd = request.getParameter("originpwd");
		pwd1 = request.getParameter("pwd1");
		pwd2 = request.getParameter("pwd2");
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if (user == null || !MD5.getMD5Hash(originPwd + user.getSalt()).equals(user.getPassword())) {
			request.setAttribute("message", "原密码错误！");
		}else if (!pwd1.equals(pwd2)) {
			request.setAttribute("message", "两次密码输入不一致！");
		}else {
			user.setPassword(MD5.getMD5Hash(pwd1 + user.getSalt()));
			userService.updatePassword(user);
			request.setAttribute("message", "密码修改成功，请重新登录！");
			session.invalidate();
		}
		
		request.getRequestDispatcher(viewBaseDir + "changepasswd.jsp").forward(request, response);
		return;
	}
	
}
