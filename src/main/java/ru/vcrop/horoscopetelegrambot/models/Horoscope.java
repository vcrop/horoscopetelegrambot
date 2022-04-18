package ru.vcrop.horoscopetelegrambot.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Horoscope {

    @Id
    @GeneratedValue()
    Long id;

    Long pageId;

    Zodiacs zodiac;

    String description;

    public Horoscope(Zodiacs zodiac) {
        this.zodiac = zodiac;
    }
}
