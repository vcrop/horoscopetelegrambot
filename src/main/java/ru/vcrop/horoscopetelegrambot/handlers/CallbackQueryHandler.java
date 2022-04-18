package ru.vcrop.horoscopetelegrambot.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.vcrop.horoscopetelegrambot.models.Chat;
import ru.vcrop.horoscopetelegrambot.models.Horoscope;
import ru.vcrop.horoscopetelegrambot.models.Zodiacs;
import ru.vcrop.horoscopetelegrambot.repositories.ChatRepository;
import ru.vcrop.horoscopetelegrambot.repositories.HoroscopeRepository;
import ru.vcrop.horoscopetelegrambot.utils.SingleMessageSender;

@Component
public class CallbackQueryHandler {

    SingleMessageSender singleMessageSender;
    ChatRepository chatRepository;
    HoroscopeRepository horoscopeRepository;

    public CallbackQueryHandler(SingleMessageSender singleMessageSender, ChatRepository chatRepository, HoroscopeRepository horoscopeRepository) {
        this.singleMessageSender = singleMessageSender;
        this.chatRepository = chatRepository;
        this.horoscopeRepository = horoscopeRepository;
    }

    public void handle(Update update) {
        Chat chat = horoscopeRepository.findHoroscopeByZodiac(Zodiacs.valueOf(
                update.getCallbackQuery().getData()
        )).map( hor ->
            chatRepository.save(
                    chatRepository.findChatByChatId(update.getCallbackQuery().getMessage().getChatId())
                            .orElseThrow()
                            .setHoroscope(hor)
            )
        ).orElseThrow();


        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(Long.toString(update.getCallbackQuery().getMessage().getChatId()));
        sendMessage.setText(
                chat.getHoroscope().getZodiac().getValue() + "\n" + chat.getHoroscope().getDescription()
        );

        singleMessageSender.send(sendMessage);
    }

}
