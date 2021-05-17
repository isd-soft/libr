package com.isd.libr.web.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "book_action")
public class BookAction {
    @Id
    private Long id;
    //relation missing, research many to one
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false, insertable = false, updatable = false)
    private Person person;
    //relation missing, research many to one
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false, insertable = false, updatable = false)
    private Book book;
    //research one to many bidirectional approach
    private LocalDateTime actionDate;
    private Status status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Person))
            return false;

        Person other = (Person) o;

        return id != null &&
                id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
