package bike.dns.drops.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Message;

public class Mail {
	/*
	 * 发送邮件
	 * @param smtp smtp服务器地址
	 * @param user 用户名
	 * @param password 密码
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 * @param from 发送方地址，需要与用户名保持一致
	 * @param to 收件方地址
	 * @return 成功返回true，失败返回false
	 */
	public static boolean sendMail(String smtp, final String user, final String password, String subject, String content,
			String from, String to) {

		try {
			Properties properties = new Properties();
			properties.put("mail.smtp.host", smtp);
			properties.put("mail.smtp.auth", "true");

			Session session = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user, password);
				}
			});

			MimeMessage message = new MimeMessage(session);
			InternetAddress fromAddress = new InternetAddress(from);
			message.setFrom(fromAddress);

			InternetAddress toAddress = new InternetAddress(to);
			message.addRecipient(Message.RecipientType.TO, toAddress);
			
			message.setSubject(subject);
			
			message.setText(content);
			
			message.setSentDate(new Date());
			
			Transport transport = session.getTransport("smtp");
			transport.connect(smtp, user, password);
			transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			transport.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("send mail error");
			return false;
		}
	}
	
	public static void main(String[] args) {
		sendMail("smtp.126.com", "zhongce888@126.com", "1qazxsw2", "密码重置邮件", "您的密码是123456", "zhongce999@126.com", "2280274936@qq.com");
	}
}
