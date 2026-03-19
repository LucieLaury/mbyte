package org.example.commands;

import discord4j.core.event.domain.message.MessageCreateEvent;
import reactor.core.publisher.Mono;

public interface MessageObserver {
    Mono<Boolean> onMessage(MessageCreateEvent event);
}