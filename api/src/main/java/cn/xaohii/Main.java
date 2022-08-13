package cn.xaohii;

import cn.xaohii.utils.mail.MailServiceImpl;

public class Main {
	public static void main(String[] args) {
		MailServiceImpl mailService = new MailServiceImpl();
		System.out.println(mailService.sendSignCodeMail("xaoohii@gmail.com"));
	}
}
