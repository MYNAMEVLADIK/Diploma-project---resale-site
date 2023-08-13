package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    /**
     * Возвращает сущность авторизованного пользователя
     */
    @Override
    public User getAuthorizedUser() {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByUsername(currentUsername);
    }

    @Override
    public UpdateUserDto updateUser(UpdateUserDto dto) {
        if (dto == null) {
            return null;
        }

        User user = getAuthorizedUser();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhone(dto.getPhone());

        userRepository.save(user);

        return dto;
    }
}
