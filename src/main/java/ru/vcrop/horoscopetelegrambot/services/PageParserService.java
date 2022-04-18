package ru.vcrop.horoscopetelegrambot.services;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;
import ru.vcrop.horoscopetelegrambot.models.Zodiacs;
import ru.vcrop.horoscopetelegrambot.utils.PageParserResponse;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class PageParserService {

    @Lookup
    public PageParserResponse lookup(){
        return null;
    }

    @SneakyThrows
    public PageParserResponse response() {
        PageParserResponse pageParserResponse = lookup();

        Document doc = Jsoup.connect("https://nataassa.livejournal.com/").get();

        Elements elements_with_a_tag = doc.getElementsByTag("a");

        Element last_by_time = elements_with_a_tag.stream()
                .filter(el -> el.text().startsWith("Астрологический прогноз на"))
                .findFirst()
                .orElseThrow();

        String attr_last_by_time = last_by_time.attr("href");

        pageParserResponse.setPageId(Long.parseLong(attr_last_by_time.replaceAll("\\D+", "")));

        Document doc_forecast = Jsoup.connect(last_by_time.attr("href")).get();

        String[] parts = doc_forecast.html().split("<br>");

        Arrays.stream(Zodiacs.values()).forEach(zod -> {
            for (int i = 0; i < parts.length; i++)
                if (parts[i].startsWith(zod.getValue()))
                    pageParserResponse.getDescriptions().put(zod, parts[i + 1].replaceAll("<.*>", ""));
        });

        return pageParserResponse;
    }

}
