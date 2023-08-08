package ru.skypro.homework.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    /**
     * id автора комментария
     */
    private Integer author;

    /**
     * Имя оставившего комментарий
     */
    private String authorFirstName;

    /**
     * Cсылка на аватар пользователя
     */
    private String imageUser;

    /**
     * Дата и время создания комментария
     */
    private LocalDateTime createdAt;

    /**
     * id комментария
     */
    private Integer idCom;

    /**
     * Текст комментария
     */
    private String text;
}
