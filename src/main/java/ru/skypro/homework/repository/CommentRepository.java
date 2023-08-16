package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

//    /**
//     * Изменить комментарий
//     */
//    @Modifying
//    @Query("UPDATE Comment c SET " +
//            "c.author.id = :authorId, " +
//            "c.author.image = :authorImage, " +
//            "c.author.firstName = :firstName, " +
//            "c.createdAt = :create_at, " +
//            "c.text = :text " +
//            "WHERE c.id = :comment_id")
//    Comment updateCommentById(
//            @Param("create_at") Integer createdAt,
//            @Param("firstName") String firstName,
//            @Param("authorImage") String authorImage,
//            @Param("authorId") Integer authorId,
//            @Param("text") String text,
//            @Param("comment_id") Integer commentId);
//
//    /**
//     * Поиск комментария по id объявления
//     */
//    List<Comment> findByAds_Id(Integer id);
}
