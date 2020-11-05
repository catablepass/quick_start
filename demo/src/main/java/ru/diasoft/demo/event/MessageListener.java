package ru.diasoft.demo.event;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Configuration;
import ru.diasoft.demo.dto.GreetingMessage;

@Configuration
public class MessageListener {

    private Logger logger = Logger.getLogger(this.getClass());

    @StreamListener(ConsumerChannels.DIRECTED)
    public void directed(GreetingMessage message) {
        logger.debug("Directed: " + message);
    }
}
