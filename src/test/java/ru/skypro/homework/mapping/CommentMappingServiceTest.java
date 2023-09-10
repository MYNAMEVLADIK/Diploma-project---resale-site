package ru.skypro.homework.mapping;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CreateCommentDto;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;

import java.time.Instant;

/**
 * Class CommentMappingServiceTest
 */
public class CommentMappingServiceTest {

    private CommentMappingService commentMappingService;
    private User user;
    private Ads ads;

    @BeforeEach
    void init () {
        user = new User();
        user.setId(1);
        user.setFirstName("Vlad");
        user.setImage(null);
        commentMappingService = new CommentMappingService();

        ads = new Ads();
        ads.setId(1);
        ads.setName("Подарок");
        ads.setPrice(990);
        ads.setTitle("Пирожные");
        ads.setImage(null);
        ads.setUser(user);
    }

    @Test
    void mapToDto() {

        Comment comment = new Comment();
        comment.setId(1);
        comment.setText("Хороший Подарок");
        comment.setCreatedAt(Instant.now());
        comment.setUser(user);

        CommentDto commentDto = commentMappingService.mapToDto(comment);

        Assertions.assertEquals(commentDto.getIdCom(), comment.getId());
        Assertions.assertEquals(commentDto.getText(), comment.getText());
        Assertions.assertEquals(commentDto.getAuthor(), comment.getUser().getId());
        Assertions.assertEquals(commentDto.getAuthorFirstName(), comment.getUser().getFirstName());
        Assertions.assertEquals(commentDto.getImageUser(), comment.getUser().getImage());
    }

    @Test
    void mapToEntity() {

        CreateCommentDto commentDto = new CreateCommentDto();
        commentDto.setText("Хороший подарок");

        Comment comment1 = commentMappingService.mapToEntity(commentDto, user, ads);

        Assertions.assertEquals(comment1.getText(), commentDto.getText());
    }
}
