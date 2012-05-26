package com.opgea.visitors.service.mail;

import java.io.File;


import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.opgea.visitors.domain.modal.MailModel;



@Service(value="mailService")
public class MailService {

	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private SimpleMailMessage alertMailMessage;
	
	
	
	public void sendMail(String from, String to, String subject, String body){
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		mailSender.send(message);
	}
	
	//public void sendMimeMail(String from, String to, String subject, String bodyText, String[] filePath){
	public void sendMimeMail(MailModel mailModel){
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
				helper = new MimeMessageHelper(message, true);
				helper.setTo(mailModel.getTo());
				helper.setFrom(mailModel.getFrom());
				helper.setSubject(mailModel.getSubject());
				helper.setText(mailModel.getBodyText(), true);
				String[] filePath = mailModel.getFilePath(); 
				for(String pathStr : filePath){
					FileSystemResource file = new FileSystemResource(new File(pathStr));
					helper.addAttachment(file.getFilename(), file);
				}
			} catch (javax.mail.MessagingException e) {
				e.printStackTrace();
			}
	
		mailSender.send(message);
	}	
	
	public void sendAlertMail(String alert){
		SimpleMailMessage mailMessage = new SimpleMailMessage(alertMailMessage);
		mailMessage.setText(alert);
		mailSender.send(mailMessage);
	}
}
