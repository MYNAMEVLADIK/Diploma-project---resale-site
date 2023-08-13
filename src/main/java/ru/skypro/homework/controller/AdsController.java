package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
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
    @GetMapping
    public ResponseEntity<TotalNumberAds> getAllAds() {

        return ResponseEntity.ok(adsService.getAllAds());
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<AdsDto> addAd(@RequestPart("properties") CreateAdsDto ad,
                                        @RequestPart MultipartFile image) {

        if (userService.getAuthorizedUser() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(adsService.addAd(ad, image, ""));
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

    @GetMapping("/{id}")
    public ResponseEntity<FullAdsDto> getAdsById(@PathVariable Integer id) {
        if (userService.getAuthorizedUser() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        FullAdsDto dto = adsService.getFullAdsById(id);
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAd(@PathVariable Integer id) {
        if (userService.getAuthorizedUser() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (!adsService.deleteAdById(id, "")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AdsDto> updateAd(@PathVariable Integer id,
                                           @RequestBody CreateAdsDto dto) {

        if (userService.getAuthorizedUser() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        AdsDto adsDto = adsService.updateAdsById(id, dto, "");
        if (adsDto == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(adsDto);
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<TotalNumberComment> getComments(@PathVariable Integer id) {
        if (userService.getAuthorizedUser() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(adsService.getAllComments(id));
    }
}
