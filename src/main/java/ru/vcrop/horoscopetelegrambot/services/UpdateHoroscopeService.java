package ru.vcrop.horoscopetelegrambot.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vcrop.horoscopetelegrambot.models.Horoscope;
import ru.vcrop.horoscopetelegrambot.models.Page;
import ru.vcrop.horoscopetelegrambot.models.Zodiacs;
import ru.vcrop.horoscopetelegrambot.repositories.HoroscopeRepository;
import ru.vcrop.horoscopetelegrambot.repositories.PageRepository;
import ru.vcrop.horoscopetelegrambot.utils.PageParserResponse;

import java.util.Arrays;

@Service
public class UpdateHoroscopeService {


    PageParserService pageParserService;
    HoroscopeRepository horoscopeRepository;
    PageRepository pageRepository;

    public UpdateHoroscopeService(PageParserService pageParserService, HoroscopeRepository horoscopeRepository, PageRepository pageRepository) {
        this.pageParserService = pageParserService;
        this.horoscopeRepository = horoscopeRepository;
        this.pageRepository = pageRepository;
    }


    public boolean isUpdated() {

        PageParserResponse pageParserResponse = pageParserService.response();

        if (pageRepository.findPageByPageId(pageParserResponse.getPageId()).isEmpty()) {
            pageRepository.save(
                    (pageRepository.count() == 0
                            ? new Page()
                            : pageRepository.findAll().iterator().next())
                            .setPageId(pageParserResponse.getPageId()));


            Arrays.stream(Zodiacs.values()).forEach(zod ->
                    horoscopeRepository.save(
                            horoscopeRepository.findHoroscopeByZodiac(zod)
                                    .orElse(new Horoscope(zod))
                                    .setDescription(pageParserResponse.getDescriptions().get(zod))
                    )
            );

            return true;
        }

        return false;
    }

}
