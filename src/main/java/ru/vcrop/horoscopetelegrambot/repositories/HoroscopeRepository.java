package ru.vcrop.horoscopetelegrambot.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vcrop.horoscopetelegrambot.models.Horoscope;
import ru.vcrop.horoscopetelegrambot.models.Zodiacs;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface HoroscopeRepository extends CrudRepository<Horoscope,Long> {

    Optional<Horoscope> findHoroscopeByZodiac(Zodiacs zodiac);

}
