package com.isd.libr.web.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
@Getter
@Setter
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    @ElementCollection
    private List<String> authors;
    private String publisher;
    private String publishedDate;
    private String description;
    @ElementCollection
    private List<String> industryIdentifiers;
    private Integer pageCount;
    @ElementCollection
    private List<String> categories;
    private Integer averageRating;
    private Integer ratingCount;
    private String maturityRating;
    @ElementCollection
    private List<String> imageLinks;
    private String language;
    private String previewLink;

//    private List<BookAction> actions = new ArrayList<>();

    //equals and haschode missing investigate on vlad mihalcea

}

