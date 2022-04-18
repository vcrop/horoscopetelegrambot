package ru.vcrop.horoscopetelegrambot.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.vcrop.horoscopetelegrambot.handlers.UpdateHandler;

@Component
public class Bot extends TelegramLongPollingBot {

    private final String botUsername;
    private final String botToken;

    private final UpdateHandler updateHandler;

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    public Bot(@Value("${bot.username}")String botUsername, @Value("${bot.token}") String botToken, UpdateHandler updateHandler) {
        this.botUsername = botUsername;
        this.botToken = botToken;
        this.updateHandler = updateHandler;
    }

    @Override
    public void onUpdateReceived(Update update) {
        updateHandler.handle(update);
    }

}
