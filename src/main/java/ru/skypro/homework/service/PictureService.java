package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;

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
