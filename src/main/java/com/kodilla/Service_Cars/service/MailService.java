package com.kodilla.Service_Cars.service;

import com.kodilla.Service_Cars.domain.AppEvent;
import com.kodilla.Service_Cars.domain.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private AppEventService appEventService;

    public void send(Mail mail) {
        try {
            mailSender.send(createSimpleMailMessage(mail));
            appEventService.saveEvent(
                    new AppEvent(
                            AppEvent.EventType.MAIL_SEND,
                            "Mail to:"+mail.getMailTo()+" ,subject: "+mail.getSubject()));
        }
        catch (MailException e){
            System.out.println("Error"+e.getMessage());
        }
    }

    public SimpleMailMessage createSimpleMailMessage(final Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail.getMailTo());
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());
        return message;
    }
}
