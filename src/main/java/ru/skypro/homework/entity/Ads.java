package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ads")
public class Ads {

    /**
     * Id объявления
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * Автор объявления
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Название объявления
     */
    @Column(name = "description", length = 50)
    @NotBlank
    private String name;

    /**
     * Цена объявления
     */
    @Column(name = "price")
    @NotNull
    private Integer price;

    /**
     * Описание объявления
     */
    @Column(name = "title", length = 100)
    @NotBlank
    private String title;

    /**
     * Картинка объявления
     */
    @Column(name = "image")
    private String image;

    /**
     * Комментарии объявления
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ads"
            , cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Comment> comments;
}
