package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exceptions.NotFoundEntityException;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.PictureService;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.mapping.UserMappingService;

/**
 * Класс - сервис, по работе с пользователями
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PictureService pictureService;
    private final UserRepository userRepository;
    private final UserMappingService userMapper;
    private final PasswordEncoder encoder;

    /**
     * Метод для обновления пароля пользователя
     */
    @Override
    @Transactional
    public boolean setPassword(NewPasswordDto password, String username) {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        if (encoder.matches(password.getCurrentPassword(), user.getPassword())) {
            user.setPassword(encoder.encode(password.getNewPassword()));
            userRepository.save(user);

            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Метод для получения пользователя
     */
    @Override
    public UserDto getUser(String username) {

        User user = userRepository.findByUsername(username);
        if (user != null) {

            return userMapper.mapToDto(user);
        } else {
            throw new NotFoundEntityException("Сущность не найдена!");
        }
    }

    /**
     * Метод для обновления данных о пользователе
     */
    @Override
    @Transactional
    public UserDto updateUser(UpdateUserDto user, String username) {

        User userDB = userRepository.findByUsername(username);
        userDB.setFirstName(user.getFirstName());
        userDB.setLastName(user.getLastName());
        userDB.setPhone(user.getPhone());

        userRepository.save(userDB);

        return userMapper.mapToDto(userDB);

    }


    /**
     * Метод для обновления аватара пользователя
     */
    @Override
    public boolean updateUserImage(String username, MultipartFile image) {

        String imageId = pictureService.addImage(image);
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        user.setImage(imageId);
        userRepository.save(user);

        return true;
    }
}
