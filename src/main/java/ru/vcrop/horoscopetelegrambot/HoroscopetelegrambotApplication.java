package ru.vcrop.horoscopetelegrambot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.vcrop.horoscopetelegrambot.models.Horoscope;
import ru.vcrop.horoscopetelegrambot.models.Zodiacs;
import ru.vcrop.horoscopetelegrambot.repositories.HoroscopeRepository;
import ru.vcrop.horoscopetelegrambot.services.UpdateHoroscopeService;

import java.util.stream.Stream;

@EnableAsync
@EnableScheduling
@SpringBootApplication
public class HoroscopetelegrambotApplication {

    public static void main(String[] args) {
        SpringApplication.run(HoroscopetelegrambotApplication.class, args);
    }



}
