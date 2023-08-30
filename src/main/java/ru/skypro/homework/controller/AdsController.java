package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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

/**
 * Контроллер по работе с объявлениями
 */
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
@Tag(name = "Класс по работе с объявлениями", description = "CRUD-операции для работы с объявлениями")
public class AdsController {

    private final AdsService adsService;
    private final PictureService pictureService;

    @GetMapping
    @Operation(
            summary = "Получить все объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = TotalNumberAds.class)
                            )
                    )
            },
            tags = "Объявления"
    )
    public ResponseEntity<TotalNumberAds> getAllAds() {
        TotalNumberAds ads = adsService.getAllAds();
        if (ads == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(ads);
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(
            summary = "Добавить объявление",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    )
            },
            tags = "Объявления"
    )
    public ResponseEntity<AdsDto> addAds(@RequestPart CreateAdsDto properties,
                                         @RequestPart(name = "image") MultipartFile image,
                                         Principal principal) {
        AdsDto adsDto = adsService.addAd(properties, image, principal.getName());
        return ResponseEntity.ok(adsDto);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получить информацию об объявлении",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = FullAdsDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    )
            },
            tags = "Объявления"
    )
    public ResponseEntity<FullAdsDto> getAds(@PathVariable Integer id) {
        FullAdsDto dto = adsService.getFullAdsById(id);
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удалить объявление",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "No Content"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden"
                    )
            },
            tags = "Объявления"
    )
    public ResponseEntity<?> removeAds(@PathVariable Integer id,
                                       Principal principal) {
        return ResponseEntity.ok().body(adsService.deleteAdById(id, principal.getName()));
    }

    @PatchMapping("/{id}")
    @Operation(
            summary = "Обновить информацию об объявлении",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden"
                    )
            },
            tags = "Объявления"
    )
    public ResponseEntity<AdsDto> updateAds(@PathVariable Integer id,
                                            @RequestBody CreateAdsDto ads,
                                            Principal principal) {
        AdsDto adsDto = adsService.updateAdsById(id, ads, principal.getName());
        return ResponseEntity.ok(adsDto);
    }

    @GetMapping("/me")
    @Operation(
            summary = "Получить объявления авторизованного пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = TotalNumberAds.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    )
            },
            tags = "Объявления"
    )
    public ResponseEntity<TotalNumberAds> getAdsMe(Principal principal) {
        TotalNumberAds dto = adsService.getAdsMe(principal.getName());
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(dto);
    }

    @PatchMapping(value = "/{id}/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(
            summary = "Обновить картинку объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE,
                                    schema = @Schema(implementation = String[].class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden"
                    )
            },
            tags = "Объявления"
    )
    public ResponseEntity<?> updateAdsImage(@PathVariable Integer id,
                                            @RequestPart MultipartFile image) {
        return ResponseEntity.ok().body(adsService.updateAdImage(id, image));
    }

    @GetMapping("/found_ads")
    @Operation(
            summary = "Поиск объявлений по названию",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = TotalNumberAds.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    )
            },
            tags = "Объявления"
    )
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
