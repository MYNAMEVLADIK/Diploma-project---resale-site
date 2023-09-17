package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CreateCommentDto;
import ru.skypro.homework.dto.TotalNumberComment;
import ru.skypro.homework.service.CommentService;

import java.security.Principal;

/**
 * Контроллер по работе с комментариями
 */
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
@Tag(name = "Класс по работе с комментариями", description = "CRUD-операции для работы с комментариями")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{id}/comments")
    @Operation(
            summary = "Добавить комментарий к объявлению",
            description = "Нужно написать комментарий"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Комментарий добавлен"
    )
    @ApiResponse(
            responseCode = "401",
            description = "для того чтобы оставить комментарий необходимо авторизоваться"
    )
    public ResponseEntity<CommentDto> addComment(@PathVariable("id") Integer id,
                                                 @RequestBody CreateCommentDto comments,
                                                 Principal principal) {
        CommentDto comment = commentService.saveComment(id, comments, principal.getName());
        if (comment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(comment);
    }

    @GetMapping("/{id}/comments")
    @Operation(
            summary = "Получить комментарии объявления",
            description = "Нужно написать id автора "
    )
    @ApiResponse(
            responseCode = "200",
            description = "Комментарий найден"
    )
    @ApiResponse(
            responseCode = "401",
            description = "для того чтобы найти комментарий необходимо авторизоваться"
    )
    public ResponseEntity<TotalNumberComment> getComments(@PathVariable("id") Integer id) {
        TotalNumberComment commentDto = commentService.getComments(id);
        if (commentDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(commentDto);
    }

    @DeleteMapping("/{adsId}/comments/{commentId}")
    @Operation(
            summary = "Удаление комментарий",
            description = "нужно написать комментарий id и рекламу id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Удалось удалить комментарий"
    )
    @ApiResponse(
            responseCode = "401",
            description = "чтобы удалить комментарий необходимо авторизоваться"
    )
    @ApiResponse(
            responseCode = "403",
            description = "отсутствуют права доступа"
    )
    public ResponseEntity<?> deleteComment(@PathVariable Integer adId,
                                           @PathVariable Integer commentId,
                                           Principal principal) {
        return ResponseEntity.ok(commentService.deleteComment(adId, commentId, principal.getName()));
    }

    @PatchMapping("{adsId}/comments/{commentId}")
    @Operation(
            summary = "обновление комментария",
            description = "нужно написать комментарий id и рекламу id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Удалось обновить комментарий"
    )
    @ApiResponse(
            responseCode = "401",
            description = "чтобы обновить комментарий необходимо авторизоваться"
    )
    @ApiResponse(
            responseCode = "403",
            description = "отсутствуют права доступа"
    )
    public ResponseEntity<CommentDto> updateComment(@PathVariable Integer adsId,
                                                    @PathVariable Integer commentId,
                                                    @RequestBody CommentDto comment,
                                                    Principal principal) {
        CommentDto commentDto = commentService.updateComment(adsId, commentId, comment, principal.getName());
        if (commentDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(commentDto);
    }
}
