package ru.diasoft.demo.event;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ConsumerChannels {

    String DIRECTED = "demo";

    @Input(DIRECTED)
    SubscribableChannel demo();
}
