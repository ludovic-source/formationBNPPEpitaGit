package com.example.EpitaJMSspringConsumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @JmsListener(destination="test_jms_spring")
    public void receive(String message) {
        System.out.println(message);
    }
}
