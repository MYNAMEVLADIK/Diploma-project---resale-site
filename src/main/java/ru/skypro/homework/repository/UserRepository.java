package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.User;

import java.util.Optional;

/**
 * Класс - репозиторий по работе с пользоватеями
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Поиск пользователя по логину
     */
    User findByUsername(String username);

    /**
     * Проверка пользователя по email
     */
    boolean existsByEmail(String email);

    /**
     * Поиск пользователя по email
     */
    Optional<User> findByEmail(String email);
}
