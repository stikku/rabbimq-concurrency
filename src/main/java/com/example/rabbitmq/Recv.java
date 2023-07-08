package com.example.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

import static com.example.rabbitmq.Common.SAC_QUEUE_NAME;


public class Recv {

    public static void main(String[] argv) throws Exception {
        final ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        final Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        channel.basicQos(1); // set prefetch to 1


        Common.declareQueue(channel);

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + message + "'");

            try {
                Thread.sleep(10000);
            } catch (final InterruptedException e) {throw new RuntimeException(e);}

            System.out.println(" [x] Processed '" + message + "'");

            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        };
        channel.basicConsume(SAC_QUEUE_NAME,
                false,
                deliverCallback,
                consumerTag -> System.out.println("Consumer Cancel Callback"));


    }
}
