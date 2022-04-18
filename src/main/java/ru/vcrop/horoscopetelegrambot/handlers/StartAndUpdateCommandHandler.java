package ru.vcrop.horoscopetelegrambot.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.vcrop.horoscopetelegrambot.models.Chat;
import ru.vcrop.horoscopetelegrambot.repositories.ChatRepository;
import ru.vcrop.horoscopetelegrambot.utils.InlineKeyboardMarkupComponent;
import ru.vcrop.horoscopetelegrambot.utils.SingleMessageSender;

@Component
public class StartAndUpdateCommandHandler{

    private final SingleMessageSender singleMessageSender;
    private final ChatRepository chatRepository;
    private final InlineKeyboardMarkupComponent inlineKeyboardMarkupComponent;

    public StartAndUpdateCommandHandler(SingleMessageSender singleMessageSender, ChatRepository chatRepository, InlineKeyboardMarkupComponent inlineKeyboardMarkupComponent) {
        this.singleMessageSender = singleMessageSender;
        this.chatRepository = chatRepository;
        this.inlineKeyboardMarkupComponent = inlineKeyboardMarkupComponent;
    }

    public void handle(Update update) {
        if (chatRepository.findChatByChatId(update.getMessage().getChatId()).isEmpty())
            chatRepository.save(new Chat(update.getMessage().getChatId()));

        singleMessageSender.send(inlineKeyboardMarkupComponent.sendInlineKeyBoardMessage(update.getMessage().getChatId()));
    }

}
