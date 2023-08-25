package ru.skypro.homework.service;


import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.User;

public interface AdsService {

    /**
     * Получить все объявления
     */
    TotalNumberAds getAllAds();

    /**
     * Добавить объявление
     */
    AdsDto addAd(CreateAdsDto dto, MultipartFile image, String userDetails);

    /**
     * Получить информацию об объявлении по id
     */
    FullAdsDto getFullAdsById(Integer id);

    /**
     * Удалить объявление по id
     */
    boolean deleteAdById(Integer id, String userDetails);

    /**
     * Обновить информацию об объявлении по id
     */
    AdsDto updateAdsById(Integer id, CreateAdsDto dto, String userDetails);

    /**
     * Получить объявления авторизованного пользователя
     */
   TotalNumberAds getAdsMe(String userDetails);

    /**
     * Поиск объявлений по названию
     */
   TotalNumberAds findByDescriptionAds(String name);

    /**
     * Изменить картинку объявления по его идентификатору
     */
    boolean updateAdImage(Integer id, MultipartFile image);
}
