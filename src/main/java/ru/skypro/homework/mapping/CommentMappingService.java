package ru.skypro.homework.mapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CreateCommentDto;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;

import java.time.Instant;

/**
 * Класс - маппинг комментариев
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CommentMappingService {

    /**
     * Метод для маппинга сущности "Комментарии" в дто
     */
    public CommentDto mapToDto(Comment comment) {
        if (comment == null) {
            return null;
        }
        CommentDto dto = new CommentDto();
        dto.setAuthor(comment.getUser().getId());
        dto.setAuthorFirstName(comment.getUser().getFirstName());
        dto.setImageUser(comment.getUser().getImage());
        dto.setCreatedAt(comment.getCreatedAt().toEpochMilli());
        dto.setIdCom(comment.getId());
        dto.setText(comment.getText());

        return dto;
    }

    /**
     * Метод для маппинга дто "Комментарии" в сущность
     */
    public Comment mapToEntity(CreateCommentDto dto, User author, Ads ad) {

        Comment entity = new Comment();
        entity.setText(dto.getText());
        entity.setAds(ad);
        entity.setUser(author);
        entity.setCreatedAt(Instant.now());

        return entity;
    }
}
