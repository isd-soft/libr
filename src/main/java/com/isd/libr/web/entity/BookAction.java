package com.isd.libr.web.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "book_action")
@Getter
@Setter
@NoArgsConstructor
public class BookAction {
    @Id
    @GeneratedValue
    private Long id;
    //relation missing, research many to one
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false, updatable = false)
    private User user;
    //relation missing, research many to one
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false, updatable = false)
    private Book book;
    //research one to many bidirectional approach
    private LocalDateTime actionDate;
    @Enumerated(EnumType.STRING)
    private Status status;

    public BookAction(User user, Book book, LocalDateTime actionDate, Status status) {
        this.user = user;
        this.book = book;
        this.actionDate = actionDate;
        this.status = status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof User))
            return false;

        User other = (User) o;

        return id != null &&
                id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
