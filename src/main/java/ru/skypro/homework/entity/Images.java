package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

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
    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] data;
}
