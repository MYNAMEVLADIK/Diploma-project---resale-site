package ru.skypro.homework.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.skypro.homework.dto.RoleDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Objects;

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
    @NotNull
    private String username;

    /**
     * Пароль пользователя
     */
    @Column(name = "password")
    @NotNull
    private String password;

    /**
     * Имя пользователя
     */
    @Column(name = "first_name")
    @NotNull
    private String firstName;

    /**
     * Фамилия пользователя
     */
    @Column(name = "last_name")
    @NotNull
    private String lastName;

    /**
     * Mail пользователя
     */
    @Column(name = "email", nullable = false, unique = true)
    @NotNull
    private String email;

    /**
     * Телефон пользователя
     */
    @Column(name = "phone", length = 12)
    @NotNull
    private String phone;

    /**
     * Роль пользователя
     */
    @Column(name = "user_role", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email) && Objects.equals(phone, user.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, firstName, lastName, email, phone);
    }
}

