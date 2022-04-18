package ru.vcrop.horoscopetelegrambot.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vcrop.horoscopetelegrambot.models.Chat;

@Repository
public interface ChatRepository extends CrudRepository<Chat,Long> {
}
