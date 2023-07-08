package com.example.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

import static com.example.rabbitmq.Common.SAC_QUEUE_NAME;

public class Send {



    public static void main(String[] args)  throws Exception{

        final ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (final Connection connection = factory.newConnection();
             final Channel channel = connection.createChannel()) {

            Common.declareQueue(channel);

            for (int i = 0; i <20; i++) {
                String message = "index: " + i;
                channel.basicPublish("", SAC_QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
                System.out.println(" [x] Sent '" + message + "'");
            }
        }
    }
}
