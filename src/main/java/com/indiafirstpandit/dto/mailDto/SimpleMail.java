package com.indiafirstpandit.dto.mailDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SimpleMail {

    private String recipient;
    private String sender;
    private String subject;
    private String body;

}
