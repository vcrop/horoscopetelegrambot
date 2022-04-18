package ru.vcrop.horoscopetelegrambot.services;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;
import ru.vcrop.horoscopetelegrambot.models.Zodiacs;
import ru.vcrop.horoscopetelegrambot.utils.PageParserResponse;

import java.util.Map;

@Service
public class PageParserService {

    @Lookup
    public PageParserResponse lookup(){
        return null;
    }

    public PageParserResponse response() {
        PageParserResponse pageParserResponse = lookup();

        return pageParserResponse;
    }

}
