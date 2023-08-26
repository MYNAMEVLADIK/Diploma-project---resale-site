package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CreateCommentDto;
import ru.skypro.homework.dto.RoleDto;
import ru.skypro.homework.dto.TotalNumberComment;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exceptions.NotFoundEntityException;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.mapping.CommentMappingService;
import ru.skypro.homework.service.mapping.CreateCommentMappingService;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AdsRepository adsRepository;
    private final CreateCommentMappingService createComments;
    private final CommentMappingService comments;
    private final UserRepository userRepository;

    @Override
    public CommentDto saveComment(Integer id, CreateCommentDto dto, String userDetails) {

        User user = userRepository.findByUsername(userDetails);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        Ads ad = adsRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("Сущность не найдена!"));

        Comment entity = createComments.mapToEntity(dto, user, ad);
        commentRepository.save(entity);

        return comments.mapToDto(entity);
    }

    @Override
    public CommentDto updateComment(Integer adId, Integer commentId, CommentDto dto, String userDetails) {

        User authorOrAdmin = userRepository.findByUsername(userDetails);
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundEntityException("Сущность не найдена!"));

        if (comment.getUser().getUsername().equals(userDetails)
                || authorOrAdmin.getRole() == (RoleDto.ADMIN)) {

            comment.setText(dto.getText());
            commentRepository.save(comment);

            CommentDto commentDto = comments.mapToDto(comment);

            return commentDto;
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @Override
    @Transactional
    public boolean deleteComment(Integer adId, Integer commentId, String userDetails) {

        User authorOrAdmin = userRepository.findByUsername(userDetails);
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundEntityException("Сущность не найдена!"));

        if (comment.getUser().getUsername().equals(userDetails)
                || authorOrAdmin.getRole() == (RoleDto.ADMIN)) {

            commentRepository.deleteById(commentId);

            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public TotalNumberComment getComments(Integer id) {

        Ads ads = adsRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("Сущность не найдена!"));

        List<Comment> comments = (List<Comment>) ads.getComments();
        if (comments == null || comments.isEmpty()) {
            return new TotalNumberComment(0, new ArrayList<>());
        }

        List<CommentDto> dtoList = new ArrayList<>();
        for (Comment comment : comments) {
            dtoList.add(this.comments.mapToDto(comment));
        }

        return new TotalNumberComment(dtoList.size(), dtoList);
    }
}
