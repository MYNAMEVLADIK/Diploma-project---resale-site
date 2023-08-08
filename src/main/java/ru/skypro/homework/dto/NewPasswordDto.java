package ru.skypro.homework.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewPasswordDto {

    /**
     * Текущий пароль
     */
    @NotNull
    private String currentPassword;

    /**
     * Новый пароль
     */
    @NotNull
    private String newPassword;
}
