package ru.vcrop.horoscopetelegrambot.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vcrop.horoscopetelegrambot.models.Chat;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface ChatRepository extends CrudRepository<Chat,Long> {

    Stream<Chat> streamAllBy();

    Optional<Chat> findChatByChatId(Long chatId);
}
