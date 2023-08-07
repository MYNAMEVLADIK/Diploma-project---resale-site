package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Images {

    /**
     * Id картинки
     */
    private String id;

    /**
     * Размер картинки в байтах
     */
    private byte[] data;
}
