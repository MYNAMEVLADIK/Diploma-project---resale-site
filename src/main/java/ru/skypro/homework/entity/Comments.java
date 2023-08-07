package ru.skypro.homework.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "comments")
public class Comments {

    /**
     * Id комментария
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Автор комментария
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Текст комментария
     */
    @Column(name = "text")
    private String text;

    /**
     * Объявление комментария
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "ads_id")
    private Ads ads;

    /**
     * Дата и время комментария
     */
    @Column(name = "create_at")
    private Instant createdAt;
}
