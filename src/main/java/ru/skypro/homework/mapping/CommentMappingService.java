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
import java.time.LocalDateTime;
import java.util.TimeZone;

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
    public CommentDto mapToDto(Comment entity) {

        TimeZone tz = TimeZone.getDefault();
        LocalDateTime ldt = LocalDateTime.ofInstant(entity.getCreatedAt(), tz.toZoneId());

        CommentDto dto = new CommentDto();
        dto.setAuthor(entity.getUser().getId());
        dto.setAuthorFirstName(entity.getUser().getFirstName());
        dto.setIdCom(entity.getId());

        if (entity.getUser().getImage() != null) {
            dto.setImageUser(String.format("/ads/image/%s", entity.getUser().getImage()));
        } else {
            dto.setImageUser(null);
        }

        dto.setCreatedAt(ldt);
        dto.setText(entity.getText());

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
