package ru.skypro.homework.dto;

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
