package ru.skypro.homework.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CreateAdsDto {

    /**
     * Описание объявления
     */
    @NotBlank
    @Size(max = 100)
    private String description;

    /**
     * Цена товара
     */
    @NotNull
    @Check(constraints = "price >= 0")
    private Integer price;

    /**
     * Заголовок объявления
     */
    @NotBlank
    @Size(max = 50)
    private String title;

}
