package ru.vcrop.horoscopetelegrambot.models;

import lombok.Data;
import lombok.NoArgsConstructor;

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

    Long chatId;

    @ManyToOne
    Horoscope horoscope;

}
