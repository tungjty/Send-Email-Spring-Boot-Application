package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.mail.MessagingException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@SpringBootApplication
public class SendEmailSpringBootApplication {

	private final EmailSenderService emailSenderService;

	@Autowired
	public SendEmailSpringBootApplication(EmailSenderService emailSenderService) {
		this.emailSenderService = emailSenderService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SendEmailSpringBootApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void triggerSendSimpleMail() throws MessagingException {
		emailSenderService.sendSimpleEmail(
				"jonny.dang180284@gmail.com",
				"This is simple email body...",
				"This is simple email subject");
	}

	@EventListener(ApplicationReadyEvent.class)
	public void triggerSendMailWithAttachment() throws MessagingException {
		List<String> fileResources = Arrays.asList(
				"/Users/tungjty_/Downloads/BaoCaoTonKho1813618418_14072021.xlsx",
				"//Users//tungjty_//Downloads//beats-studio-buds-01.jpg"
		);
		emailSenderService.sendEmailWithAttachment(
				"jonny.dang180284@gmail.com",
				"This is Email Body with Attachment...",
				"This email has attachment",
				fileResources);
//				"C:\\Users\\shabb\\Pictures\\c.gif");
	}
}
