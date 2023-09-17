package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Интерфейс, по работе с картинками
 */
public interface PictureService {
    /**
     * Загружаем новое изображение
     */
    String addImage(MultipartFile image);

    /**
     * Загружает изображение по названию изображения
     */
    byte[] loadImage(String fileName);

    byte[] loadImageFail(String fileName);
}
