package ru.skypro.homework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FullAdsDto {

    /**
     * Id объявления
     */
    @JsonProperty("pk")
    private Integer idAds;

    /**
     * Имя автора объявления
     */
    private String authorFirstName;

    /**
     * Фамилия автора объявления
     */
    private String authorLastName;

    /**
     * Описание объявления
     */
    private String description;

    /**
     * Логин автора объявления
     */
    private String email;

    /**
     * Ссылка на картинку объявления
     */
    @JsonProperty("image")
    private String imageAds;

    /**
     * Телефон автора объявления
     */
    private String phone;

    /**
     * Цена объявления
     */
    private Integer price;

    /**
     * Заголовок объявления
     */
    private String title;
}
