package cn.xaohii.api.utils.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**
 * 功能之一：用来完成邮件发送的功能
 * */
@Service
public class MailServiceImpl {
	protected static final Logger LOG = LoggerFactory.getLogger(MailServiceImpl.class);

	private String sendEmail(String email, String content) {
		Message message = getMessage();
		if (!Objects.isNull(message)) {
			try {
				message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
				message.setSubject("EChat 帐号注册验证");
				message.setContent(content, "text/html;charset=UTF-8");
				Transport.send(message);
				return "邮件发送成功";
			} catch (Exception e) {
				LOG.info("Error Message: " + e.getMessage());
				return "邮件发送失败";
			}
		} else {
			return "message init failed";
		}
	}

	public String sendSignCodeMail(String acceptMail) {
		int volid = (int) ((Math.random() * 9 + 1) * 100000);
		String content = "系统收到此邮箱帐号在Echat的注册申请,为确认账户的真实性,须完成账户验证。本次验证码为 <b>" + volid + "</b>,有效时间30分钟。如果不是本账户持有人操作,请忽略!";
		return sendEmail(acceptMail, content);
	}

	private Message getMessage() {
		Properties mailProperties = readProperties();
		try {
			assert mailProperties != null;
			String mail = mailProperties.getProperty("mail.username");
			String mailPasswd = mailProperties.getProperty("mail.password");
			Properties properties = new Properties();
			properties.put("mail.smtp.host", "smtp.ym.163.com");
			properties.put("mail.smtp.port", "25");
			properties.put("mail.smtp.auth", "true");
			Session session = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(mail, mailPasswd);
				}
			});
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(mail));
			return message;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 读取配置文件
	private Properties readProperties() {
		String propertiesFilePath = "properties/mail.properties";
		Properties properties = new Properties();
		try (InputStream inputStream = MailServiceImpl.class.getClassLoader().getResourceAsStream(propertiesFilePath)) {
			properties.load(inputStream);
			return properties;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
