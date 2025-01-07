package com.indiafirstpandit.controller.mailController;

import com.indiafirstpandit.dto.mailDto.SimpleMail;
import com.indiafirstpandit.service.mail.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mail/sendMail")
public class SendMailController {

    @Autowired
    private MailSenderService mailSenderService;

    @GetMapping("/testMail/{email}")
    private ResponseEntity<String> sendTestMail(@PathVariable String email )
    {
        System.out.println(email);
        SimpleMail simpleMail = new SimpleMail();
        simpleMail.setBody("test");
        simpleMail.setRecipient(email);
        simpleMail.setSubject("From india first pandit");
//        simpleMail.setSender("shauryayamdagni0@gmail.com");
        mailSenderService.sendEmail(simpleMail);
        return ResponseEntity.ok("sent");
    }

    @PostMapping
    private ResponseEntity<String> sendMail(@RequestBody SimpleMail simpleMail )
    {
        simpleMail.setSender("IndiaFirstPandit");
        mailSenderService.sendEmail(simpleMail);
        return ResponseEntity.ok("sent");

    }

    @PostMapping("/all")
    private ResponseEntity<String> sendMailToAll(@RequestBody SimpleMail simpleMail )
    {
        simpleMail.setSender("IndiaFirstPandit");
        mailSenderService.sendMailToAll(simpleMail);
        return ResponseEntity.ok("sent");

    }



}
