package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {

    /**
     * Логин пользователя
     */
    private String username;

    /**
     * Пароль пользователя
     */
    private String password;

    /**
     * Имя пользователя
     */
    private String firstName;

    /**
     * Фамилия пользователя
     */
    private String lastName;

    /**
     * Телефон пользователя
     */
    private String phone;

    /**
     * Роль пользователя
     */
    private RoleDto role;

}
