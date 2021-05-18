package com.isd.libr.web.dto;

import com.isd.libr.web.entity.Book;
import com.isd.libr.web.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookDto {
    private String title;
    private List<String> authors;
    private String publishedDate;
    private String description;
    private int pageCount;
    private List<String> categories;
    private int averageRating;
    private String language;
    private Status status;

    public static BookDto from(Book book) {
        BookDto result = new BookDto();
        result.setTitle(book.getTitle());
        result.setAuthors(book.getAuthors());
        result.setPublishedDate(book.getPublishedDate());
        result.setDescription(book.getDescription());
        result.setPageCount(book.getPageCount());
        result.setCategories(book.getCategories());
        result.setAverageRating(book.getAverageRating());
        result.setLanguage(book.getLanguage());
        return result;
    }
}
