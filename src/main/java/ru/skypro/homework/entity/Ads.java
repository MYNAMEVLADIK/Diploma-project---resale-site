package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @Column(name = "description")
    private String description;

    /**
     * Цена объявления
     */
    @Column(name = "price")
    private Integer price;

    /**
     * Описание объявления
     */
    @Column(name = "title")
    private String title;

    /**
     * Картинка объявления
     */
    @Column(name = "image_path")
    private String imagePath;

    /**
     * Комментарии объявления
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ads"
            , cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Comment> comments;
}
