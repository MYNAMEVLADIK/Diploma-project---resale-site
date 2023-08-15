package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CreateCommentDto;
import ru.skypro.homework.dto.TotalNumberComment;

public interface CommentService {

    /**
     * Создание комментария
     */
    CommentDto saveComment(Integer id, CreateCommentDto dto, String userDetails);

    /**
     * Удалить комментарий
     */
    boolean deleteComment(Integer adId, Integer commentId, String userDetails);

    /**
     * Изменить комментарий
     */
    CommentDto updateComment(Integer adId, Integer commentId, CommentDto dto, String userDetails);

    /**
     * Получить комментарий
     */
    TotalNumberComment getComments(Integer id);
}
