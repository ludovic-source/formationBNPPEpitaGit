package com.example.EpitaJMSspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerJms {

    @Autowired
    private JmsTemplate jmsTemplate;

    @GetMapping("/send")
    public void sendMessage() {
        jmsTemplate.convertAndSend("test_jms_spring", "voici le 1er message");
    }

}
