package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exceptions.NotFoundEntityException;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.PictureService;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.mapping.UserMappingService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PictureService pictureService;
    private final UserRepository userRepository;
    private final UserMappingService userMapper;
    private final PasswordEncoder encoder;

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

    @Override
    public UserDto getUser(String username) {

        User user = userRepository.findByUsername(username);
        if (user != null) {

            return userMapper.mapToDto(user);
        } else {
            throw new NotFoundEntityException("Сущность не найдена!");
        }
    }

    @Override
    @Transactional
    public UserDto updateUser(UserDto user, String username) {

        User userDB = userRepository.findByUsername(username);
        userDB.setId(user.getId());
        userDB.setFirstName(user.getFirstName());
        userDB.setLastName(user.getLastName());
        userDB.setPhone(user.getPhone());
        userDB.setImage(user.getImage());
        userDB.setEmail(user.getEmail());

        userRepository.save(userDB);

        return userMapper.mapToDto(userDB);

    }

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
