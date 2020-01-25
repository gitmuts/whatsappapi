package com.gitmuts.whatsappapi.controller;

import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Body;
import com.twilio.twiml.messaging.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
@Slf4j
public class ReceiveMessageController {

    @PostMapping(value = "/sms", produces = "application/xml")
    public String receiveMessage(HttpServletRequest request, HttpServletResponse response) {
        try{
            String message =  request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            log.info(message);

            Body body = new Body
                    .Builder("The Robots are coming! Head for the hills!")
                    .build();
            Message sms = new Message
                    .Builder()
                    .body(body)
                    .build();
            MessagingResponse twiml = new MessagingResponse
                    .Builder()
                    .message(sms)
                    .build();
            return twiml.toXml();
        }catch(Exception e){
            log.error(e.getMessage(), e);
            return "";
        }
    }

}
