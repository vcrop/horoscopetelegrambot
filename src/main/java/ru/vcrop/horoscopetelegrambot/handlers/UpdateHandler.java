package ru.vcrop.horoscopetelegrambot.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class UpdateHandler {

    StartAndUpdateCommandHandler startAndUpdateCommandHandler;
    CallbackQueryHandler callbackQueryHandler;

    public UpdateHandler(StartAndUpdateCommandHandler startAndUpdateCommandHandler, CallbackQueryHandler callbackQueryHandler) {
        this.startAndUpdateCommandHandler = startAndUpdateCommandHandler;
        this.callbackQueryHandler = callbackQueryHandler;
    }

    public void handle(Update update) {
        if (update.hasCallbackQuery())
            callbackQueryHandler.handle(update);
        else if (update.getMessage().isCommand())
            startAndUpdateCommandHandler.handle(update);
    }

}
