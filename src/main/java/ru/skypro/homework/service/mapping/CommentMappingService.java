package ru.skypro.homework.service.mapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.UserRepository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentMappingService {

    private final UserRepository userRepository;

    public Comment mapToEntity(CommentDto dto) {
        if (dto == null) {
            return null;
        }

        User user = userRepository.getReferenceById(dto.getAuthor());

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setId(dto.getIdCom());
        comment.setText(dto.getText());
        comment.setCreatedAt(Instant.ofEpochMilli(dto.getCreatedAt()));

        return comment;
    }

    public CommentDto mapToDto(Comment comment) {
        if (comment == null) {
            return null;
        }

        CommentDto dto = new CommentDto();
        dto.setAuthor(comment.getUser().getId());
        dto.setAuthorFirstName(comment.getUser().getFirstName());
        dto.setImageUser(comment.getUser().getImage());
        dto.setCreatedAt(comment.getCreatedAt().toEpochMilli());
        dto.setIdCom(comment.getId());
        dto.setText(comment.getText());

        return dto;
    }

    public List<CommentDto> mapToListDto(List<Comment> comments) {
        List<CommentDto> dto = new ArrayList<>();
        if (comments == null || comments.isEmpty()) {
            return dto;
        }

        for (Comment comment : comments) {
            dto.add(mapToDto(comment));
        }

        return dto;
    }
}
