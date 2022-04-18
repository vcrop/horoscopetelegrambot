package ru.vcrop.horoscopetelegrambot.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@Entity
public class Chat {

    @Id
    @GeneratedValue()
    Long id;

    @NaturalId
    Long chatId;

    @ManyToOne
    Horoscope horoscope;

    public Chat(Long chatId) {
        this.chatId = chatId;
    }

    public Chat setHoroscope(Horoscope horoscope) {
        this.horoscope = horoscope;
        return this;
    }
}
