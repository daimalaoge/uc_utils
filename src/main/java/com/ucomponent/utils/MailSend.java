package com.ucomponent.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * 2019/8/3
 * Author:代码老哥
 * NAME:
 * TODO:
 **/
@Component
public class MailSend {
	@Autowired
	private JavaMailSender javaMailSender;


	public void sendSimpleMail(String sender,String tomail,String title,String content){
		MimeMessage message = null;
		try {
			message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(sender);
			helper.setTo(tomail);
			helper.setSubject(title);

			StringBuffer sb = new StringBuffer();
			sb.append(content);
			helper.setText(sb.toString(), true);

			javaMailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
