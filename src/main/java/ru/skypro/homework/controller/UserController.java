package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.mapping.UserMappingService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMappingService userMappingService;

    /**
     * Возвращает информацию об авторизованном пользователе
     */
    @GetMapping("/me")
    public ResponseEntity<UserDto> getUser() {
        User user = userService.getAuthorizedUser();

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(userMappingService.mapToDto(user));
    }

    @PatchMapping("/me")
    public ResponseEntity<UpdateUserDto> updateUser(@RequestBody UpdateUserDto dto) {
        if (userService.getAuthorizedUser() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(userService.updateUser(dto));
    }
}
