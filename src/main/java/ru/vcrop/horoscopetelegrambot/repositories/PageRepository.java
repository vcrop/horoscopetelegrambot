package ru.vcrop.horoscopetelegrambot.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.vcrop.horoscopetelegrambot.models.Page;

import java.util.Optional;

public interface PageRepository extends CrudRepository<Page,Long> {

    Optional<Page> findPageByPageId(Long id);
}
