package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    /**
     * Логин пользователя
     */
    private String username;


    /**
     * Пароль пользователя
     */
    private String password;

}
