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
    @CollectionTable(name = "authors", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "author")
    private List<String> authors = new ArrayList<>();
    private String publisher;
    private String publishedDate;
    private String description;
    @ElementCollection
    @CollectionTable(name = "industry_identifiers", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "industry_identifier")
    private List<String> industryIdentifiers = new ArrayList<>();
    private Integer pageCount;
    @ElementCollection
    @CollectionTable(name = "categories", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "category")
    private List<String> categories;
    private Integer averageRating;
    private Integer ratingCount;
    private String maturityRating;
    @ElementCollection
    @CollectionTable(name = "image_links", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "image_link")
    private List<String> imageLinks;
    private String language;
    private String previewLink;

    @OneToMany(mappedBy = "book")
    private List<BookAction> actions = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Book))
            return false;

        Book other = (Book) o;

        return id != null &&
                id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}

