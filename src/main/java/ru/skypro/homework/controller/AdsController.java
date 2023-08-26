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
            TotalNumberAds ads = adsService.getAllAds();
            if (ads == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            return ResponseEntity.ok(ads);
        }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<AdsDto> addAds(@RequestPart CreateAdsDto properties,
                                        @RequestPart(name = "image") MultipartFile image,
                                        Principal principal) {
            AdsDto adsDto = adsService.addAd(properties, image, principal.getName());
            return ResponseEntity.ok(adsDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FullAdsDto> getAds(@PathVariable Integer id) {
            FullAdsDto dto = adsService.getFullAdsById(id);
            if (dto == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeAds(@PathVariable Integer id,
                                      Principal principal) {
            return ResponseEntity.ok().body(adsService.deleteAdById(id, principal.getName()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AdsDto> updateAds(@PathVariable Integer id,
                                            @RequestBody CreateAdsDto ads,
                                            Principal principal) {
            AdsDto adsDto = adsService.updateAdsById(id, ads, principal.getName());
            return ResponseEntity.ok(adsDto);
    }

    @GetMapping("/me")
    public ResponseEntity<TotalNumberAds> getAdsMe(Principal principal) {
            TotalNumberAds dto = adsService.getAdsMe(principal.getName());
            if (dto == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            return ResponseEntity.ok(dto);
    }

    @PatchMapping(value = "/{id}/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> updateAdsImage(@PathVariable Integer id,
                                           @RequestPart MultipartFile image) {
            return ResponseEntity.ok().body(adsService.updateAdImage(id, image));
    }

    @GetMapping("/found_ads")
    public ResponseEntity<TotalNumberAds> findByDescriptionAds(@RequestParam String name) {
            TotalNumberAds dto = adsService.findByDescriptionAds(name);
            if (dto == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            return ResponseEntity.ok().body(dto);
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
            return ResponseEntity.ok(pictureService.loadImageFail(id));
    }
}
