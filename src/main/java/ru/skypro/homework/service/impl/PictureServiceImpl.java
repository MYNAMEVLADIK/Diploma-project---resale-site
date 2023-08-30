package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Images;
import ru.skypro.homework.service.PictureService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.util.UUID;

import static java.nio.file.Files.*;
import static java.nio.file.Files.readAllBytes;

/**
 * Класс - сервис, по работе с картинками
 */
@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class PictureServiceImpl implements PictureService {

    private final String desktopPath = System.getProperty("user.dir") + File.separator + "images";

    /**
     * Метод для добавления картинки
     */
    @Override
    public String addImage(MultipartFile image) {

        Images picture = new Images();
        try {
            String fileName = UUID.randomUUID() + type(image);
            picture.setId(fileName);
            createDirectories(Paths.get(desktopPath));
            image.transferTo(new File(desktopPath + File.separator + fileName));
            log.info("Image file created by  name: {}", fileName);
        } catch (IOException e) {
            log.error("Error while saving image file{}", picture.getId());
        }
        return picture.getId();
    }

    /**
     * Метод для загрузки картинки
     */
    @Override
    public byte[] loadImage(String fileName) {

        File image;
        byte[] outputFileBytes = null;
        try {
            image = new File(desktopPath, fileName);
            if (exists(image.toPath())) {
                outputFileBytes = readAllBytes(image.toPath());
                log.info("loadImage: File loaded successfully");
            } else {
                try (InputStream in = new URL("").openStream()) {
                    outputFileBytes = in.readAllBytes();
                    log.info("loadImage: File loaded default successfully");
                }
            }
        } catch (IOException e) {
            log.error("file load error");
        }
        return outputFileBytes;
    }

    /**
     * Метод для загрузки картинки
     */
    @Override
    public byte[] loadImageFail(String fileName) {

        File image;
        byte[] outputFileBytes = null;
        try {
            image = new File(desktopPath, fileName);
            outputFileBytes = readAllBytes(image.toPath());
            log.info("loadImageFail: File loaded successfully");
        } catch (IOException e) {
            log.error("loadImageFail: Error while loading file {}", fileName);
        }
        return outputFileBytes;
    }

    /**
     * Метод для определения типа картинки
     */
    private String type(MultipartFile image) {

        String type = image.getContentType();
        assert type != null;
        type = type.replace("image/", ".");

        return type;
    }
}
