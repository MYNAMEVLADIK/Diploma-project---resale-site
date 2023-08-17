package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.RoleDto;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.mapping.UserMappingService;

import javax.validation.ValidationException;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder encoder;
    private final UserMappingService userMappingService;
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final UserDetailsManager userDetailsManager;

//    @Override
//    public boolean login(String userName, String password) {
//
//        User user = userRepository.findByUsername(userName);
//
//        if (user == null
//                || !user.getUsername().equals(userName)
//                && !user.getPassword().equals(encoder.encode(password))) {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
//        }
//
////        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
//        return encoder.matches(password, user.getPassword());
//    }

    @Override
    public boolean login(String userName, String password) {

        ru.skypro.homework.entity.User user = userRepository.findByUsername(userName);
        if (user == null) {
            return false;
        }

        if (!userDetailsManager.userExists(userName)) {
            userDetailsManager.createUser(
                    User.builder()
                            .password(user.getPassword())
                            .username(user.getUsername())
                            .roles(user.getRole().name())
                            .build());
        }

        UserDetails userDetails = userDetailsManager.loadUserByUsername(userName);
        String encryptedPassword = userDetails.getPassword();

        return encoder.matches(password, encryptedPassword);
    }

    @Override
    public boolean register(RegisterDto registerDto, RoleDto roleDto) {

        if (userDetailsManager.userExists(registerDto.getUsername())) {
            return false;
        }

        userDetailsManager.createUser(
                User.builder()
                        .passwordEncoder(encoder::encode)
                        .password(registerDto.getPassword())
                        .username(registerDto.getUsername())
                        .roles(RoleDto.USER.name())
                        .build());

        ru.skypro.homework.entity.User user = userMappingService.mapToEntity(registerDto);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);

        return true;
    }

//    @Override
//    public boolean register(RegisterDto registerDto, RoleDto roleDto) {
//
//        User user = userRepository.findByUsername(registerDto.getUsername());
//        if (user != null) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//        }
//
//        if (!validationService.validate(registerDto)) {
//            throw new ValidationException(registerDto.toString());
//        }
//
//        try {
//            registerDto.setRole(roleDto);
//            registerDto.setPassword(encoder.encode(registerDto.getPassword()));
//            User newUser = userMappingService.mapToEntity(registerDto);
//            userRepository.save(newUser);
//            return true;
//        } catch (RuntimeException e) {
//            e.getStackTrace();
//            return false;
//        }
//    }
}