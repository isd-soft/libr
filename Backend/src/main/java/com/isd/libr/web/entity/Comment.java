package com.isd.libr.web.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.isd.libr.web.entity.ReactionType.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Lob
    private String comment;
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
    private LocalDateTime date = LocalDateTime.now();
    @OneToMany(mappedBy = "comment", orphanRemoval = true)
    private List<CommentReaction> commentReactions = new ArrayList<>();

    protected Comment() {
    }

    public Map<ReactionType, Long> getCommentReaction() {
        Map<ReactionType, Long> result = new HashMap<>();
        long laughCount = commentReactions.stream()
                .filter(r -> r.getReactionType() == ReactionType.LAUGH).count();
        long heartCount = commentReactions.stream()
                .filter(r -> r.getReactionType() == ReactionType.HEART).count();
        long sadCount = commentReactions.stream()
                .filter(r -> r.getReactionType() == SAD).count();
        result.put(SAD, sadCount);
        result.put(LAUGH, laughCount);
        result.put(HEART, heartCount);
        return result;
    }

}
