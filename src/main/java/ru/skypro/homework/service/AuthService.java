package ru.skypro.homework.service;

import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.RoleDto;

/**
 * Интерфейс, по работе с авторизацией и регистрацией
 */
public interface AuthService {

    /**
     * Авторизация пользователя
     */
    boolean login(String userName, String password);

    /**
     * Регистрация пользователя
     */
    boolean register(RegisterDto register, RoleDto roleDto);
}
