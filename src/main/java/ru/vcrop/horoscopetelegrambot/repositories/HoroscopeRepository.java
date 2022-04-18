package ru.vcrop.horoscopetelegrambot.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vcrop.horoscopetelegrambot.models.Horoscope;

import java.util.stream.Stream;

@Repository
public interface HoroscopeRepository extends CrudRepository<Horoscope,Long> {
    Stream<Horoscope> streamAllBy();
    Stream<Horoscope> streamHoroscopeByPageId(Long pageId);
}
