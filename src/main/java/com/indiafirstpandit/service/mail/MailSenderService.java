package com.indiafirstpandit.service.mail;

import com.indiafirstpandit.dto.mailDto.SimpleMail;
import com.indiafirstpandit.model.User;
import com.indiafirstpandit.service.UserAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserAdminService userAdminService;

    public void sendEmail(SimpleMail simpleMail)
    {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(simpleMail.getSender());
        mailMessage.setTo(simpleMail.getRecipient());
        mailMessage.setText(simpleMail.getBody());
        mailMessage.setSubject(simpleMail.getSubject());
        System.out.println(mailMessage);
        mailSender.send(mailMessage);
        System.out.println(mailMessage);

    }

    public void sendMailToAll(SimpleMail simpleMail)
    {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        List<String> emailAllUsers = userAdminService.getAllUsers().stream().map(User::getEmail).toList();

        if (!emailAllUsers.isEmpty()) {
            mailMessage.setFrom(simpleMail.getSender());
            mailMessage.setBcc(emailAllUsers.toArray(new String[0])); // Set all users in BCC
            mailMessage.setText(simpleMail.getBody());
            mailMessage.setSubject(simpleMail.getSubject());
            mailSender.send(mailMessage);}
    }

}
