package com.etoak.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.etoak.bean.Email;
@Service
public class EmailService {
	

	//邮件消息对象
	@Autowired
	SimpleMailMessage mailMessage;
		//发送邮件消息
	@Autowired
	JavaMailSenderImpl mailSender;
		//线程池
	@Autowired
	ThreadPoolTaskExecutor executor;

	public void send(Email email) {
		mailMessage.setTo(email.getReceiver());//收件人
		mailMessage.setSubject(email.getSubject());//邮件主题
		mailMessage.setText(email.getContent());//邮件内容
		//使用线程池异步发送消息
		executor.execute(()->{
			mailSender.send(mailMessage);
			System.out.println("邮件下发结束");	
		});
	}
}
