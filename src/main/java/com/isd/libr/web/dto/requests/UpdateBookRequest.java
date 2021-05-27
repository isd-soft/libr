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
public class UpdateBookRequest {
    private String title;
    private List<String> authors;
    private String publisher;
    private String publishedDate;
    private String description;
    private List<HashMap<String, String>> industryIdentifiers;
    private Integer pageCount;
    private List<String> categories;
    private Integer averageRating;
    private Integer ratingCount;
    private String maturityRating;
    private Map<String, String> imageLinks = new HashMap<>();
    private String language;
    private String previewLink;

    @JsonCreator
    public UpdateBookRequest(
            @JsonProperty String title,
            @JsonProperty List<String> authors,
            @JsonProperty String publisher,
            @JsonProperty String publishedDate,
            @JsonProperty String description,
            @JsonProperty List<HashMap<String, String>> industryIdentifiers,
            @JsonProperty Integer pageCount,
            @JsonProperty List<String> categories,
            @JsonProperty Integer averageRating,
            @JsonProperty Integer ratingCount,
            @JsonProperty String maturityRating,
            @JsonProperty Map<String, String> imageLinks,
            @JsonProperty String language,
            @JsonProperty String previewLink) {
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.description = description;
        this.industryIdentifiers = industryIdentifiers;
        this.pageCount = pageCount;
        this.categories = categories;
        this.averageRating = averageRating;
        this.ratingCount = ratingCount;
        this.maturityRating = maturityRating;
        this.imageLinks = imageLinks;
        this.language = language;
        this.previewLink = previewLink;
    }
}
