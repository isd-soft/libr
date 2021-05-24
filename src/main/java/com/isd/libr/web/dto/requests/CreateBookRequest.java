package com.isd.libr.web.dto.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Data
public class CreateBookRequest {
    private String title;
    private List<String> authors;
    private String publisher;
    private String publishedDate;
    private String description;
    private List<HashMap<String, String>> industryIdentifiers;
    private Integer pageCount;
    private List<String> categories;
    private Integer averageRating;
    private Integer ratingsCount;
    private String maturityRating;
    private Map<String, String> imageLinks;
    private String language;
    private String previewLink;

    @JsonCreator
    public CreateBookRequest(@JsonProperty("title") String title,
                             @JsonProperty("authors") List<String> authors,
                             @JsonProperty("publisher") String publisher,
                             @JsonProperty("publishedDate") String publishedDate,
                             @JsonProperty("description") String description,
                             @JsonProperty("industryIdentifiers") List<HashMap<String, String>> industryIdentifiers,
                             @JsonProperty("pageCount") Integer pageCount,
                             @JsonProperty("categories") List<String> categories,
                             @JsonProperty("averageRating") Integer averageRating,
                             @JsonProperty("ratingCount") Integer ratingsCount,
                             @JsonProperty("maturityRating") String maturityRating,
                             @JsonProperty("imageLinks") Map<String, String> imageLinks,
                             @JsonProperty("language") String language,
                             @JsonProperty("previewLink") String previewLink) {
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.description = description;
        this.industryIdentifiers = industryIdentifiers;
        this.pageCount = pageCount;
        this.categories = categories;
        this.averageRating = averageRating;
        this.ratingsCount = ratingsCount;
        this.maturityRating = maturityRating;
        this.imageLinks = imageLinks;
        this.language = language;
        this.previewLink = previewLink;
    }
}
