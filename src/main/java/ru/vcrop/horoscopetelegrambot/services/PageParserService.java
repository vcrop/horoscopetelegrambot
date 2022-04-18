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

        pageParserResponse.setPageId(Long.parseLong(attr_last_by_time.replaceAll("\\D+", "")) + 1L);

        Document doc_forecast = Jsoup.connect(last_by_time.attr("href")).get();

        System.out.println(
                doc_forecast.text()
        );

        pageParserResponse.getDescription().put(Zodiacs.Libra,
                doc_forecast.text().replaceAll(".*?(Скорпион )(.*?)(?=Стрелец).*", "$1\n$2")
        );

        return pageParserResponse;
    }

}
