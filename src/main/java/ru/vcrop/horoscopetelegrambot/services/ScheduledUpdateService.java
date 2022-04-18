package ru.vcrop.horoscopetelegrambot.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.vcrop.horoscopetelegrambot.repositories.ChatRepository;
import ru.vcrop.horoscopetelegrambot.utils.SingleMessageSender;

import java.util.Objects;


@Service
public class ScheduledUpdateService {

    SingleMessageSender singleMessageSender;
    UpdateHoroscopeService updateHoroscopeService;
    ChatRepository chatRepository;

    public ScheduledUpdateService(SingleMessageSender singleMessageSender, UpdateHoroscopeService updateHoroscopeService, ChatRepository chatRepository) {
        this.singleMessageSender = singleMessageSender;
        this.updateHoroscopeService = updateHoroscopeService;
        this.chatRepository = chatRepository;
    }


    @Scheduled(initialDelay = 60L, fixedRate = 3_600_000)
    public void update() {
        if (updateHoroscopeService.isUpdated())
            chatRepository.streamAllBy().filter(ch -> Objects.nonNull(ch.getHoroscope()))
                    .forEach(ch -> {
                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setChatId(Long.toString(ch.getChatId()));
                        sendMessage.setText(
                                ch.getHoroscope().getZodiac().getValue() + "\n" + ch.getHoroscope().getDescription()
                        );
                        singleMessageSender.send(sendMessage);
                    });
    }
}
