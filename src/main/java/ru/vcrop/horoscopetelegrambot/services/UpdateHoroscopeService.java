package ru.vcrop.horoscopetelegrambot.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vcrop.horoscopetelegrambot.repositories.HoroscopeRepository;
import ru.vcrop.horoscopetelegrambot.utils.PageParserResponse;

@Service
public class UpdateHoroscopeService {

    PageParserService pageParserService;
    HoroscopeRepository horoscopeRepository;

    public UpdateHoroscopeService(PageParserService pageParserService, HoroscopeRepository horoscopeRepository) {
        this.pageParserService = pageParserService;
        this.horoscopeRepository = horoscopeRepository;
    }

    @Transactional
    public boolean nowUpdate() {

        PageParserResponse pageParserResponse = pageParserService.response();

        if (horoscopeRepository.streamHoroscopeByPageId(pageParserResponse.getPageId()).findAny().isEmpty()){
            horoscopeRepository.streamAllBy().forEach(hor -> {
                hor.setPageId(pageParserResponse.getPageId());
                hor.setDescription(pageParserResponse.getDescription().get(hor.getZodiac()));
            });
            return true;
        }

        return false;
    }

}
