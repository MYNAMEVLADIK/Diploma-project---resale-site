package ru.skypro.homework.service.mapping;

import ru.skypro.homework.dto.CreateCommentDto;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;

import java.time.Instant;

public class CreateCommentMappingService {

    public Comment mapToEntity(CreateCommentDto dto, User author, Ads ad) {

        Comment entity = new Comment();
        entity.setText(dto.getText());
        entity.setAds(ad);
        entity.setUser(author);
        entity.setCreatedAt(Instant.now());

        return entity;
    }
}
