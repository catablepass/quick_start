package ru.diasoft.demo.event;

public interface ConsumerChannels {

    String DIRECTED = "directed";

    @Input(DIRECTED)
    SubscribableChannel directed();
}
