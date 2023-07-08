package com.example.rabbitmq;

import com.rabbitmq.client.Channel;

import java.util.Map;

public class Common {

    public final static String SAC_QUEUE_NAME = "sac_queue";

    public static void declareQueue(final Channel channel) throws Exception {
        final Map<String, Object> arguments = Map.of("x-single-active-consumer", true);

        channel.queueDeclare(SAC_QUEUE_NAME, true, false, false, arguments);
    }
}
