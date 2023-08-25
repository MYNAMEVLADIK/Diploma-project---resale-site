package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.PictureService;

import java.security.Principal;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController {


    private final AdsService adsService;
    private final PictureService pictureService;

    @GetMapping
    public ResponseEntity<TotalNumberAds> getAllAds() {

        try {
            TotalNumberAds ads = adsService.getAllAds();
            return ResponseEntity.ok(ads);

        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<AdsDto> addAds(@RequestPart CreateAdsDto properties,
                                        @RequestPart(name = "image") MultipartFile image,
                                        Principal principal) {

        try {
            AdsDto adsDto = adsService.addAd(properties, image, principal.getName());
            return ResponseEntity.ok(adsDto);

        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<FullAdsDto> getAds(@PathVariable Integer id) {

        try {
            FullAdsDto dto = adsService.getFullAdsById(id);
            return ResponseEntity.ok().body(dto);

        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeAds(@PathVariable Integer id,
                                      Principal principal) {

        try {
            return ResponseEntity.ok().body(adsService.deleteAdById(id, principal.getName()));

        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AdsDto> updateAds(@PathVariable Integer id,
                                            @RequestBody CreateAdsDto ads,
                                            Principal principal) {

        try {
            AdsDto adsDto = adsService.updateAdsById(id, ads, principal.getName());
            return ResponseEntity.ok(adsDto);

        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping("/me")
    public ResponseEntity<TotalNumberAds> getAdsMe(Principal principal) {

        try {
            TotalNumberAds dto = adsService.getAdsMe(principal.getName());
            return ResponseEntity.ok(dto);

        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PatchMapping(value = "/{id}/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> updateAdsImage(@PathVariable Integer id,
                                           @RequestPart MultipartFile image) {

        try {
            return ResponseEntity.ok().body(adsService.updateAdImage(id, image));
        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/found_ads")
    public ResponseEntity<TotalNumberAds> findByDescriptionAd(@RequestParam String name) {

        try {
            TotalNumberAds dto = adsService.findByDescriptionAds(name);
            return ResponseEntity.ok().body(dto);

        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value = "/image/{id}", produces = {
            MediaType.IMAGE_PNG_VALUE,
            MediaType.IMAGE_JPEG_VALUE,
            MediaType.APPLICATION_OCTET_STREAM_VALUE
    })
    @Operation(
            summary = "Получить картинку объявления",
            tags = "Объявления",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content())
            })

    public ResponseEntity<byte[]> getImage(@PathVariable("id") String id) {

        try {
            return ResponseEntity.ok(pictureService.loadImageFail(id));

        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
