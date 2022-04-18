package ru.vcrop.horoscopetelegrambot.utils;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.vcrop.horoscopetelegrambot.models.Zodiacs;

import java.util.HashMap;
import java.util.Map;

@Data
@Scope(scopeName = "prototype")
@Component
public class PageParserResponse {

    Long pageId;

    Map<Zodiacs,String> descriptions = new HashMap<>();

}
