package ru.vcrop.horoscopetelegrambot.utils;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.vcrop.horoscopetelegrambot.models.Zodiacs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
public class InlineKeyboardMarkupComponent {

    private final InlineKeyboardMarkup inlineKeyboardMarkup;

    public InlineKeyboardMarkupComponent() {
        this.inlineKeyboardMarkup = new InlineKeyboardMarkup();

        inlineKeyboardMarkup.setKeyboard(
                IntStream.range(0, Zodiacs.values().length / 2)
                        .mapToObj(n -> Stream.of(Zodiacs.values()[n], Zodiacs.values()[n + Zodiacs.values().length / 2])
                                .map(zod -> {
                                    InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
                                    inlineKeyboardButton.setText(zod.getValue());
                                    inlineKeyboardButton.setCallbackData(zod.name());
                                    return inlineKeyboardButton;
                                })
                                .toList())
                        .toList()
        );

    }

    public SendMessage sendInlineKeyBoardMessage(long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Выберите знак зодиака");
        sendMessage.setChatId(Long.toString(chatId));
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }

}
