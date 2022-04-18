package ru.vcrop.horoscopetelegrambot.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Page {

    @Id
    @GeneratedValue()
    Long id;

    Long pageId;

    public Page setPageId(Long pageId) {
        this.pageId = pageId;
        return this;
    }
}
