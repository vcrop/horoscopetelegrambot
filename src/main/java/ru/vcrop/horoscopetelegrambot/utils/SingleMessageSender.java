package ru.vcrop.horoscopetelegrambot.utils;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.vcrop.horoscopetelegrambot.bot.Bot;

@Component
public class SingleMessageSender {

    Bot bot;

    public SingleMessageSender(@Lazy Bot bot) {
        this.bot = bot;
    }

    @Async
    @SneakyThrows
    public void send(SendMessage sendMessage) {
        bot.execute(sendMessage);
    }
}
