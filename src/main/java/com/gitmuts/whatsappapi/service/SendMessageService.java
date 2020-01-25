package com.gitmuts.whatsappapi.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SendMessageService {

    @Value("${twillio.account_sid}")
    private String accountSID;

    @Value("${twillio.auth_token}")
    private String authToken;

    public Message send (String from, String to, String body) {
        try{
            Twilio.init(accountSID, authToken);
            Message message = Message.creator(
                    new com.twilio.type.PhoneNumber("whatsapp:"+to),
                    new com.twilio.type.PhoneNumber("whatsapp:"+from),
                    body)
                    .create();

            return message;
        }catch (Exception e){
            log.error("Error occurred while calling {} EX", "SendMessageService:send", e);
            return null;
        }
    }

}
