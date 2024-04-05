package com.chtrembl.petstore.order.service;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusSenderClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class QueueSenderService {

    static final Logger log = LoggerFactory.getLogger(QueueSenderService.class);

    @Value("${azure.servicebus.connectionString}")
    private String connectionString;

    @Value("${azure.servicebus.queueName}")
    private String queueName;

    private ServiceBusSenderClient senderClient;

    @PostConstruct
    private void initialize() {
        // Initialize the ServiceBusSenderClient
        this.senderClient = new ServiceBusClientBuilder()
                .connectionString(connectionString)
                .sender()
                .queueName(queueName)
                .buildClient();
    }

    public void sendMessage(String message) {
        senderClient.sendMessage(new com.azure.messaging.servicebus.ServiceBusMessage(message));
        log.info("Sent message: " + message);
    }

    @PreDestroy
    private void cleanup() {
        if (senderClient != null) {
            senderClient.close();
        }
    }
}
