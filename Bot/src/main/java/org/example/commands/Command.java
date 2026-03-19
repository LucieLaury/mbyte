package org.example.commands;

import discord4j.core.event.domain.message.MessageCreateEvent;
import reactor.core.publisher.Mono;

public interface Command {

    String name(); // ex: "ping", "export"

    Mono<Boolean> execute(MessageCreateEvent event);
}
