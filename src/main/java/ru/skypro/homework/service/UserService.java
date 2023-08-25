package ru.skypro.homework.service;

import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.entity.User;

public interface UserService {
    User getAuthorizedUser();

    UpdateUserDto updateUser(UpdateUserDto dto);
}