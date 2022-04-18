package ru.vcrop.horoscopetelegrambot.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

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

    @NaturalId
    Zodiacs zodiac;

    String description;

    public Horoscope(Zodiacs zodiac) {
        this.zodiac = zodiac;
    }

    public Horoscope setDescription(String description) {
        this.description = description;
        return this;
    }
}
