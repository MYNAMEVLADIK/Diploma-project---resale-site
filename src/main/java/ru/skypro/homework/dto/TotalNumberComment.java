package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TotalNumberComment {

    /**
     * Всего комментариев
     */
    private Integer count;

    /**
     * Все найденые комментарии
     */
    private List<CommentDto> results;
}
