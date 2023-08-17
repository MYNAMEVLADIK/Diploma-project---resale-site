package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewPasswordDto {

    /**
     * Текущий пароль
     */
    @NotBlank
    private String currentPassword;

    /**
     * Новый пароль
     */
    @NotBlank
    private String newPassword;
}
