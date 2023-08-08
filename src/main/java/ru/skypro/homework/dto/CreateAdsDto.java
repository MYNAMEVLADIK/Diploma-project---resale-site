package ru.skypro.homework.dto;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateAdsDto {

    /**
     * Описание объявления
     */
    @NotNull
    private String description;

    /**
     * Цена товара
     */
    @NotNull
    private Integer price;

    /**
     * Заголовок объявления
     */
    @NotNull
    private String title;

    /**
     * Id автора объявления
     */
    private Integer author;
}
