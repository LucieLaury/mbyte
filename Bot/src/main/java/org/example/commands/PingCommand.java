package org.example.commands;

import discord4j.core.event.domain.message.MessageCreateEvent;
import reactor.core.publisher.Mono;

public class PingCommand implements Command, MessageObserver {
    @Override
    public String name() {
        return "ping";
    }

    @Override
    public Mono<Boolean> execute(MessageCreateEvent event) {
        return event.getMessage()
                .getChannel()
                .flatMap(channel -> channel.createMessage("Pong!"))
                .thenReturn(true)
                ;
    }

    @Override
    public Mono<Boolean> onMessage(MessageCreateEvent event) {
        String content = event.getMessage().getContent();

        if (!content.equals("!ping")) {
            return Mono.just(false);
        }

        return execute(event);
    }
}
