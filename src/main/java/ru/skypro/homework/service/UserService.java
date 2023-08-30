package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;

/**
 * Интерфейс, по работе с пользователями
 */
public interface UserService {

    /**
     * Обновление пароля
     */
    boolean setPassword(NewPasswordDto password, String username);

    /**
     * Получить информацию об авторизованном пользователе
     */
    UserDto getUser(String username);

    /**
     * Обновить информацию об авторизованном пользователе
     */
    UserDto updateUser(UserDto user, String username);

    /**
     * Обновить аватар авторизованного пользователя
     */
    boolean updateUserImage(String username, MultipartFile image);

}
