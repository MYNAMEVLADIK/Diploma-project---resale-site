package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.TotalNumberAds;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.UserService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController {

    private final AdsService adsService;
    private final UserService userService;

    /**
     * Возвращает абсолютно все объявления от всех пользователей
     */
    @GetMapping("/")
    public ResponseEntity<TotalNumberAds> getAllAds() {

        return ResponseEntity.ok(adsService.getAllAds());
    }

    /**
     * Возвращает все объявления авторизованного пользователя
     */
    @GetMapping("/me")
    public ResponseEntity<TotalNumberAds> getMyAds() {
        User currentUser = userService.getAuthorizedUser();
        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(adsService.getAdsMe(currentUser));
    }
}
