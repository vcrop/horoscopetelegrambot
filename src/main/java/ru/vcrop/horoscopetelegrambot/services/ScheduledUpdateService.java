package ru.vcrop.horoscopetelegrambot.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.vcrop.horoscopetelegrambot.repositories.ChatRepository;
import ru.vcrop.horoscopetelegrambot.utils.SingleMessageSender;

import java.util.Objects;

@Log4j2
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


    @Transactional
    @Scheduled(initialDelay = 60L, fixedRate = 3_600_000)
    public void update() {
        if (updateHoroscopeService.isUpdated()) {
            log.info("Update...");
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
}
