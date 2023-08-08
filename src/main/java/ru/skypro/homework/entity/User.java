package ru.skypro.homework.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.skypro.homework.dto.RoleDto;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "users")
public class User {

    /**
     * Id пользователя
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * Логин пользователя
     */
    @Column(name = "username")
    private String username;

    /**
     * Пароль пользователя
     */
    @Column(name = "password")
    private String password;

    /**
     * Имя пользователя
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * Фамилия пользователя
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * Mail пользователя
     */
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    /**
     * Телефон пользователя
     */
    @Column(name = "phone", length = 11)
    private String phone;

    /**
     * Роль пользователя
     */
    @Column(name = "user_role", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleDto role;

    /**
     * Фото пользователя
     */
    @Column(name = "image")
    private String image;

    /**
     * Объявления пользователя
     */
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Ads> ads;
}

