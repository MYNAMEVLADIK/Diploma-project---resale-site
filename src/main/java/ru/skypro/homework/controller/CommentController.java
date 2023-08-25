package ru.skypro.homework.controller;

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

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{id}/comments")
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
    public ResponseEntity<TotalNumberComment> getComments(@PathVariable("id") Integer id) {
        TotalNumberComment commentDto = commentService.getComments(id);
        if (commentDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(commentDto);
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer adId,
                                           @PathVariable Integer commentId,
                                           Principal principal) {
        return ResponseEntity.ok(commentService.deleteComment(adId, commentId, principal.getName()));
    }


    @PatchMapping("{adId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Integer adId,
                                                    @PathVariable Integer commentId,
                                                    @RequestBody CommentDto comment,
                                                    Principal principal) {
        CommentDto commentDto = commentService.updateComment(adId, commentId, comment, principal.getName());
        if (commentDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(commentDto);
    }
}
