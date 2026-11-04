package iki.novabank_user_account_service.kafka.service;

import iki.novabank_user_account_service.kafka.dto.UserRegistrationEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AccountEventPublisher {

    private final KafkaTemplate<String,Object> kafkaTemplate;

    public AccountEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    //topic
    private static final String USER_TOPIC="user-registered-events";

    public void publishedUserRegistrationEvent(UserRegistrationEvent event){
        try {
            kafkaTemplate.send(USER_TOPIC,event.getEmail(),event);
            System.out.println("Event Sent out "+event.getEmail());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
