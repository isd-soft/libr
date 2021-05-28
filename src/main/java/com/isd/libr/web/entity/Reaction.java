package com.isd.libr.web.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@Table(name = "reaction")
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @Enumerated(EnumType.STRING)
    private ReactionType reactionType;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinColumn(name = "users_id", nullable = false, updatable = false)
    private User user;
    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinColumn(name = "book_id", nullable = false, updatable = false)
    private Book book;

    protected Reaction() {}
}
