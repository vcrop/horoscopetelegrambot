package ru.vcrop.horoscopetelegrambot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import ru.vcrop.horoscopetelegrambot.models.Horoscope;
import ru.vcrop.horoscopetelegrambot.models.Zodiacs;
import ru.vcrop.horoscopetelegrambot.repositories.HoroscopeRepository;

import java.util.stream.Stream;

@EnableAsync
@SpringBootApplication
public class HoroscopetelegrambotApplication {

    public static void main(String[] args) {
        SpringApplication.run(HoroscopetelegrambotApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(HoroscopeRepository horoscopeRepository) {
        return args -> {
            if (horoscopeRepository.count() == 0)
                Stream.of(Zodiacs.values()).map(Horoscope::new).forEach(horoscopeRepository::save);
        };
    }

}
