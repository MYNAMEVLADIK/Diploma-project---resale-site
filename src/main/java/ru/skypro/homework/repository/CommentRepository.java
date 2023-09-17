package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.Comment;

/**
 * Класс - репозиторий по работе с комментариями
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
}