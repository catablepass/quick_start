package ru.diasoft.demo.event;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Configuration;
import ru.diasoft.demo.dto.GreetingMessage;

@Configuration
public class MessageListener {

    private static Logger logger = LogManager.getLogger();

    @StreamListener(ConsumerChannels.DIRECTED)
    public void demo(GreetingMessage message) {
        logger.debug("Directed: " + message);
    }
}
