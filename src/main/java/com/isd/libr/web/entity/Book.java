package com.isd.libr.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "book")
@Getter
@Setter
@Builder
@AllArgsConstructor
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
//    @Lob
    private String description;
    @ElementCollection
    @CollectionTable(name = "industry_identifiers", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "industry_identifier")
    private List<HashMap<String, String>> industryIdentifiers;
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
    private Map<String, String> imageLinks = new HashMap<>();
    private String language;
    private String previewLink;


    @OneToMany(mappedBy = "book")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    private List<BookAction> actions = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    private List<Vote> votes = new ArrayList<>();

    protected Book() {}

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

    public int getSumOfVotes() {
        return getVotes().stream().map(Vote::getVote).mapToInt(Integer::intValue).sum();
    }
}

