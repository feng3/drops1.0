package bike.dns.drops.test;

import org.junit.Test;

import bike.dns.drops.utils.Mail;

public class TestMail {

	@Test
	public void testSendMail() {
		String smtp = "smtp.exmail.qq.com";
		String user = "service@ziliudi.wiki";
		String password = "";
		String subject = "密码重置邮件";
		String content = "您的新密码为123456";
		String from = user;
		String to = "service@ziliudi.wiki";
		Mail.sendMail(smtp, user, password, subject, content, from, to);
	}

}
