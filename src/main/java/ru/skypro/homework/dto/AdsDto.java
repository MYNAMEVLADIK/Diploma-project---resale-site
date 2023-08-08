package ru.skypro.homework.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdsDto {

    /**
     * id автора объявления
     */
    private Integer author;

    /**
     * Cсылка на картинку объявления
     */
    private String imageAd;

    /**
     * id объявления
     */
    private Integer idAd;

    /**
     * Стоимость товара объявления
     */
    private Integer price;

    /**
     * Заголовок объявления
     */
    private String title;

}
