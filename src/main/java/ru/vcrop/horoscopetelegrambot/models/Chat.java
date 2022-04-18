package ru.vcrop.horoscopetelegrambot.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Chat {

    @Id
    @GeneratedValue()
    Long id;

    Long chatId;

    Zodiacs zodiac;

    public Chat(Long chatId, Zodiacs zodiac) {
        this.chatId = chatId;
        this.zodiac = zodiac;
    }

}
