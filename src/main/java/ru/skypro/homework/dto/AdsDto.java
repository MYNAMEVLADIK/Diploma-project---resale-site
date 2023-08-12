package ru.skypro.homework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("image")
    private String imageAd;

    /**
     * id объявления
     */
    @JsonProperty("pk")
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
