package ru.vcrop.horoscopetelegrambot.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Zodiacs {
    Aries("Овен"), Taurus("Телец"), Gemini("Близнецы"),
    Cancer("Рак"), Leo("Лев"), Virgo("Дева"),
    Libra("Весы"), Scorpio("Скорпион"), Sagittarius("Стрелец"),
    Capricorn("Козерог"), Aquarius("Водолей"), Pisces("Рыбы");

    @Getter
    private final String value;

}
