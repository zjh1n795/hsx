package common.javaMail;

public class Main {

	public static void main(String[] args) {
		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.163.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("han2000lei@163.com");
		mailInfo.setPassword("**********");// 您的邮箱密码
		mailInfo.setFromAddress("han2000lei@163.com");
		mailInfo.setToAddress("han2000lei@163.com");
		mailInfo.setSubject("设置邮箱标题");
		mailInfo.setContent("设置邮箱内容");
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		sms.sendTextMail(mailInfo);// 发送文体格式
		sms.sendHtmlMail(mailInfo);// 发送html格式
	}
}
