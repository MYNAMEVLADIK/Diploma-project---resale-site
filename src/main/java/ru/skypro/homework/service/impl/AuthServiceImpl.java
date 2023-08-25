package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.RoleDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.mapping.UserMappingService;


@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder encoder;
    private final UserMappingService userMapperUtils;
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;

    @Override
    public boolean login(String userName, String password) {

        User user = userRepository.findByUsername(userName);

        if (user == null
                || !user.getUsername().equals(userName)
                && !user.getPassword().equals(encoder.encode(password))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(RegisterDto registerReq, RoleDto role) {

        User user = userRepository.findByUsername(registerReq.getUsername());
        if (user != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        try {
            registerReq.setRole(role);
            registerReq.setPassword(encoder.encode(registerReq.getPassword()));
            User newUser = userMapperUtils.mapToEntity(registerReq);
            userRepository.save(newUser);
            return true;
        } catch (RuntimeException e) {
            e.getStackTrace();
            return false;
        }
    }
}