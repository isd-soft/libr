package com.isd.libr.web.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class BookAction {
    @Id
    Long id;
    //relation missing, research many to one
    Person person;
    //relation missing, research many to one
    Book book;
    //research one to many bidirectional approach
    LocalDateTime actionDate;
    Status status;

    //equals and haschode missing investigate on vlad mihalcea

}
